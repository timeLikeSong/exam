package com.lx.exam.common.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.exam.common.service.itf.IBfService;
import com.lx.exam.common.vo.PageBean;
import com.lx.exam.util.ContextUtil;
import com.lx.exam.util.PropertiesUtil;
import com.lx.exam.util.StringUtil;

@Service
public class BfService<Po, Vo, Sm> implements IBfService<Po, Vo, Sm> {

	@PersistenceContext(unitName = "EMF")
	EntityManager em;

	/**
	 * 根据数据库类型处理查询时like中的特殊字符
	 * 
	 * @param dBType
	 *            数据库类型（oracle 或 sqlserver 或 mysql）
	 * @param likeValue
	 *            like值
	 * @param wz
	 *            Sql语句的like语法中"%"的位置， r 右，l 左，a 左右都有
	 * @return
	 */
	private String _dealSqlLike(String dBType, String likeValue, String wz) {
		String s = "";
		if (dBType.equalsIgnoreCase("sqlserver")) {// sqlserver like特殊字符转义
			s = likeValue.replaceAll("'", "''").replaceAll("\\[", "[[]").replaceAll("%", "[%]").replaceAll("_", "[_]");
		} else if (dBType.equalsIgnoreCase("mysql") || dBType.equalsIgnoreCase("oracle")) {
			s = likeValue.replaceAll("%", "*%").replaceAll("_", "*_");
		}
		String r = "%";// 右
		String l = "%";// 左
		if (wz.toUpperCase().equals("L")) {
			r = "";
		}
		if (wz.toUpperCase().equals("R")) {
			l = "";
		}
		return l + s + r;
	}

	/**
	 * 根据使用的数据库链接配置文件获取当前使用的数据库
	 * 
	 * @return
	 */
	private String _getDBType() {
//		String DBType = "";
//		String filename = ContextUtil2.getRealPath() + "/WEB-INF/classes/jdbc.properties";
//		String driverClass = PropertiesUtil.getKey(filename, "driverClass");
//		if (driverClass.toLowerCase().indexOf("sqlserver.") > -1) {
//			DBType = "sqlserver";
//		} else if (driverClass.toLowerCase().indexOf("oracle.") > -1) {
//			DBType = "oracle";
//		} else if (driverClass.indexOf("mysql.") > 1) {
//			DBType = "mysql";
//		}
//		return DBType;
		return  "mysql";
	}

	private String _getMetodName(String fieldname) {
		return fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
	}

	private String _getName(String name, String oldname, String newname) {
		name = name.replace(oldname, newname).replaceAll("_", ".");
		return name;
	}

	/**
	 * 拼凑Sql
	 * 
	 * @param field
	 *            反射字段
	 * @param prefix
	 *            字段前缀，例："sm_eq"，用户获取columnName
	 * @param option
	 *            操作，例: "=",">=","in"等
	 * @param value
	 *            传参字段要赋的值
	 * @param fields
	 *            HgField类的集合
	 * @param endFix
	 *            paramName的后缀
	 * @param useSqlfunc
	 *            是否使用sql语句的函数
	 * @param field_count
	 * @return
	 */
	private String _makeSql(Field field, String prefix, String option, Object value, Map<Integer, Object> fields,
			int field_count, boolean useSqlfunc) {
		StringBuffer sb = new StringBuffer();
		String columnName = this._getName(field.getName(), prefix, "");
		sb.append(" and " + (useSqlfunc ? "" : columnName) + " " + option + " ? ");
		fields.put(field_count, value);
		return sb.toString();
	}
	
	/**
	 * oracle 日期比较sql串
	 * 
	 * @param field
	 *            反射字段
	 * @param prefix
	 *            字段前缀，例："sm_eq"，用户获取columnName
	 * @param option
	 *            操作，例: "=",">=","in"等
	 * @param value
	 *            传参字段要赋的值
	 * @param fields
	 *            HgField类的集合
	 * @param field_count
	 * @return
	 */
	private String _makeSql_OrlDate(Field field, String prefix, String option, Object value, Map<Integer, Object> fields,
			int field_count) {
		StringBuffer sb = new StringBuffer();
		String columnName = this._getName(field.getName(), prefix, "");
		sb.append(" and " +  columnName + option + "to_date(" +" ? "+", 'YYYY-MM-DD hh24:mi:ss')");
		fields.put(field_count, value);
		return sb.toString();
	}

	@Override
	@Transactional("transactionManager")
	public Vo add(Class<Po> pc, Vo vo, String idName, String idType) throws Exception {
		try {
			Po p = pc.getDeclaredConstructor(vo.getClass()).newInstance(vo);
			em.persist(p);
			// 获取vo的id的set方法
			Method vdm = vo.getClass().getDeclaredMethod("set" + this._getMetodName(idName),
					Class.forName("java.lang." + idType));
			// 获取po的id的get方法
			Method pdm = p.getClass().getDeclaredMethod("get" + this._getMetodName(idName));
			// 设置id
			vdm.invoke(vo, pdm.invoke(p));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return vo;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional("transactionManager")
	public int delete(Class<Po> po, Class<Sm> smc, Sm sm) throws Exception {
		int count = 0;
		try {
			Map<String, Object> map = this.getConditions(sm, false);
			String hql = "delete from " + po.getName() + " where 1=1 " + map.get("hql").toString();
			TypedQuery<Po> q = (TypedQuery<Po>) this.em.createQuery(hql);
			this.setParamOfConditions(q, (Map<String, Object>) map.get("fields"));
			count = q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	@Transactional("transactionManager")
	public int delete(Class<Po> po, String ids, String idname) throws Exception {
		int i = 0;
		try {
			if (ids == null) {
				ids = "";
			}
			String query = "delete from " + po.getName() + " where " + idname + " in ("
					+ StringUtil.delAndNorepeat(ids, "", ",") + ")";
			i = em.createQuery(query).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional("transactionManager")
	public Vo edit(Class<Po> pc, Vo vo, String idName) throws Exception {
		// 获取vo的id的get方法
		Method vdm = vo.getClass().getDeclaredMethod("get" + this._getMetodName(idName));
		// 获取po
		Po po = (Po) em.find(pc, vdm.invoke(vo));
		try {
			po = (Po) pc.getDeclaredMethod("wrap", vo.getClass()).invoke(po, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.merge(po);
		return vo;
	}

	@Override
	@Transactional("transactionManager")
	public Po edit(Po po) throws Exception {
		em.merge(po);
		return po;
	}

	@Override
	@Transactional("transactionManager")
	public int execUpdate(String sql) throws Exception {
		int count = em.createQuery(sql).executeUpdate();
		return count;
	}

	@Override
	@Transactional("transactionManager")
	public int execUpdateNative(String sql) throws Exception {
		int count = em.createNativeQuery(sql).executeUpdate();
		return count;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Vo get(Class<Po> pc, Class<Vo> vc, Sm sm) throws Exception {
		String hql = "FROM " + pc.getName() + " t WHERE 1=1 ";
		Map<String, Object> map = this.getConditions(sm, false);
		hql += map.get("hql").toString();
		TypedQuery<Po> q = (TypedQuery<Po>) this.em.createQuery(hql);
		this.setParamOfConditions(q, (Map<String, Object>) map.get("fields"));
		List<Po> pos = (List<Po>) q.getResultList();
		Po po = null;
		if (pos.size() > 0) {
			po = pos.get(0);
		}
		Vo vo = null;
		if (po != null) {
			vo = vc.getDeclaredConstructor(pc).newInstance(po);
		}
		return vo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Po get(Class<Po> pc, Sm sm) throws Exception {
		String hql = "SELECT  t FROM " + pc.getName() + " t WHERE 1=1 ";
		Map<String, Object> map = this.getConditions(sm, false);
		hql += map.get("hql").toString();
		TypedQuery<Po> q = (TypedQuery<Po>) this.em.createQuery(hql);
		this.setParamOfConditions(q, (Map<String, Object>) map.get("fields"));
		List<Po> pos = (List<Po>) q.getResultList();
		Po po = null;
		if (pos.size() > 0) {
			po = pos.get(0);
		}
		return po;
	}

	@Override
	public Vo getById(Class<Po> pc, Class<Vo> vc, Object id) throws Exception {
		Po po = em.find(pc, id);
		Vo vo = null;
		if (po != null) {
			vo = vc.getDeclaredConstructor(pc).newInstance(po);
		}
		return vo;
	}

	@Override
	public Po getById(Class<Po> pc, Object id) throws Exception {
		Po po = em.find(pc, id);
		return po;
	}

	@Override
	public Map<String, Object> getConditions(Sm sm, Boolean needOrderBy) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		Map<Integer, Object> fields = new HashMap<Integer, Object>();
		if (sm != null) {
			Field field_orderby = null;
			Field field_or = null;
			int field_count = 0;
			for (Field field : sm.getClass().getDeclaredFields()) {
				field.setAccessible(true); // 设置访问权限，false只能访问public
				String fieldname = field.getName();
				Object value = field.get(sm);
				if (value != null && !value.equals("")) {
					/*
					 * order by
					 */
					if (fieldname.equals("sm_orderby")) {
						// 防止sm_orderby中加入“ or 1=1 ”等语句
						if (value.toString().trim().toLowerCase().startsWith("order by")) {
							field_orderby = field;
						}
					}

					/*
					 * or
					 */
					if (fieldname.equals("sm_or")) {
						field_or = field;
					}
					String dBType = this._getDBType();
					/*
					 * 模糊查询
					 */
					// 全包含
					if (fieldname.startsWith("sm_like_")) {
						sb.append(this._makeSql(field, "sm_like_", "like",
								_dealSqlLike(dBType, String.valueOf(value), "A"), fields, (++field_count), false));
					}
					// 右包含
					if (fieldname.startsWith("sm_r_like_")) {
						sb.append(this._makeSql(field, "sm_r_like_", "like",
								_dealSqlLike(dBType, String.valueOf(value), "R"), fields, (++field_count), false));
					}

					// 左包含
					if (fieldname.startsWith("sm_l_like_")) {
						sb.append(this._makeSql(field, "sm_l_like_", "like",
								_dealSqlLike(dBType, String.valueOf(value), "L"), fields, (++field_count), false));
					}

					/*
					 * in范围
					 */
					// in (1,2,....) 如过是字符，则要加引号，in ('a','b','c')
					if (fieldname.startsWith("sm_in_")) {
						String columnName = this._getName(field.getName(), "sm_in_", "");
						sb.append(" and " + columnName + " in ( ");
						String sb2 = "";

						for (String in_v : String.valueOf(value).split(",")) {
							if (in_v != null && in_v.length() > 0) {
								sb2 += ",?";
								fields.put(++field_count, in_v);
							}
						}
						sb2 = sb2.startsWith(",") ? sb2.substring(1, sb2.length()) : sb2;
						sb.append(sb2 + ")");
					}

					/*
					 * 字符串比较
					 */
					// 等于
					if (fieldname.startsWith("sm_eq_")) {
						sb.append(this._makeSql(field, "sm_eq_", "=", value, fields, (++field_count), false));
					}
					// 不等于
					if (fieldname.startsWith("sm_nq_")) {
						sb.append(this._makeSql(field, "sm_nq_", "<>", value, fields, (++field_count), false));
					}

					// 大于
					if (fieldname.startsWith("sm_gt_")) {
						sb.append(this._makeSql(field, "sm_gt_", ">", value, fields, (++field_count), false));
					}
					// 小于
					if (fieldname.startsWith("sm_lt_")) {
						sb.append(this._makeSql(field, "sm_lt_", "<", value, fields, (++field_count), false));
					}
					// 小于等于
					if (fieldname.startsWith("sm_le_")) {
						sb.append(this._makeSql(field, "sm_le_", "<=", value, fields, (++field_count), false));
					}
					// 大于等
					if (fieldname.startsWith("sm_ge_")) {
						sb.append(this._makeSql(field, "sm_ge_", ">=", value, fields, (++field_count), false));
					}

					/*
					 * 日期比较
					 */
					// 日期段
					if (dBType.equalsIgnoreCase("sqlserver")) {// sqlserver
																// like特殊字符转义
						if (fieldname.startsWith("sm_to1_")) {
							String columnName = this._getName(field.getName(), "sm_to1_", "");
							sb.append(this._makeSql(field, "sm_to1_", "convert(varchar(120)," + columnName + ",112) >=",
									value, fields, (++field_count), true));
						}
						if (fieldname.startsWith("sm_to2_")) {
							String columnName = this._getName(field.getName(), "sm_to2_", "");
							sb.append(this._makeSql(field, "sm_to2_", "convert(varchar(120)," + columnName + ",112) <=",
									value, fields, (++field_count), true));
						}
					} else if (dBType.equalsIgnoreCase("mysql")) {
						if (fieldname.startsWith("sm_to1_")) {
							String columnName = this._getName(field.getName(), "sm_to1_", "");
							sb.append(this._makeSql(field, "sm_to1_", "date_format(" + columnName + ",'%Y-%m-%d') >=",
									value, fields, (++field_count), true));
						}
						if (fieldname.startsWith("sm_to2_")) {
							String columnName = this._getName(field.getName(), "sm_to2_", "");
							sb.append(this._makeSql(field, "sm_to2_", "date_format(" + columnName + ",'%Y-%m-%d') <=",
									value, fields, (++field_count), true));
						}
					}else if (dBType.equalsIgnoreCase("oracle")) {
						if (fieldname.startsWith("sm_to1_")) {
							sb.append(this._makeSql_OrlDate(field, "sm_to1_", ">=",
									value, fields, (++field_count)));
						}
						if (fieldname.startsWith("sm_to2_")) {
							sb.append(this._makeSql_OrlDate(field, "sm_to2_", "<=",
									value, fields, (++field_count)));
						}
						if (fieldname.startsWith("sm_teq_")) { //oracle 时间相等比较用
							sb.append(this._makeSql_OrlDate(field, "sm_teq_", "=",
									value, fields, (++field_count)));
						}
					}
				}
			}
			// or条件特殊处理，直接写原sql语句
			if (field_or != null) {
				field_or.setAccessible(true);
				if (field_or.get(sm) != null) {
					sb.append(" and ( " + field_or.get(sm) + " ) ");
				}
			}

			// orderby条件特殊处理，直接写原sql语句
			if (field_orderby != null && needOrderBy) {
				field_orderby.setAccessible(true);
				if (field_orderby.get(sm) != null) {
					sb.append(" " + field_orderby.get(sm) + " ");
				}
			}
		}
		map.put("hql", sb);
		map.put("fields", fields);
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long getCount(Class<Po> pc, Sm sm) throws Exception {
		String hql = "select count(t) from " + pc.getName() + " t  WHERE 1=1 ";
		Map<String, Object> map = this.getConditions(sm, false);
		hql += map.get("hql").toString();
		TypedQuery<Po> q = (TypedQuery<Po>) this.em.createQuery(hql);
		this.setParamOfConditions(q, (Map<String, Object>) map.get("fields"));
		return (Long) q.getSingleResult();
	}

	@Override
	public Long getCountBySql(String sql) {
		Long count = (Long) em.createQuery(sql).getSingleResult();
		return count;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Vo> list(Class<Po> pc, Class<Vo> vc, Sm sm, PageBean pb) throws Exception {
		String hql = "FROM " + pc.getName() + " t WHERE  1=1 ";
		Map<String, Object> map = this.getConditions(sm, true);
		hql += map.get("hql").toString();
		TypedQuery<Po> q = (TypedQuery<Po>) this.em.createQuery(hql);
		this.setParamOfConditions(q, (Map<String, Object>) map.get("fields"));
		if (pb != null) {
			Integer page = pb.getPage();
			Integer rows = pb.getRows();
			if (page != null) {
				q.setFirstResult((page - 1) * rows);
				q.setMaxResults(rows);
			}
		}
		List<Vo> vs = new ArrayList<Vo>();
		for (Po p : q.getResultList()) {
			vs.add(vc.getDeclaredConstructor(pc).newInstance(p));
		}
		return vs;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Po> list(Class<Po> pc, Sm sm, PageBean pb) throws Exception {
		String hql = "SELECT  t FROM " + pc.getName() + " t WHERE  1=1 ";
		Map<String, Object> map = this.getConditions(sm, true);
		hql += map.get("hql").toString();
		TypedQuery<Po> q = (TypedQuery<Po>) this.em.createQuery(hql);
		this.setParamOfConditions(q, (Map<String, Object>) map.get("fields"));
		if (pb != null) {
			Integer page = pb.getPage();
			Integer rows = pb.getRows();
			if (page != null) {
				q.setFirstResult((page - 1) * rows);
				q.setMaxResults(rows);
			}
		}
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List listBySql(String sql) {
		List list = em.createNamedQuery(sql).getResultList();
		return list;
	}

	@Override
	public void setParamOfConditions(TypedQuery<Po> q, Map<String, Object> fields) throws Exception {
		if (fields != null) {
			for (int i = 1; i < fields.size() + 1; i++) {
				Object f = fields.get(i);
				try {
					q.setParameter(i, f);
				} catch (Exception e) {
					/*
					 * 各个条件字段都有类型，只有'sm_in_'的情况特殊，是字符串，这里做异常处理，
					 * 因为只存在Long和String两种可能。
					 */
					try {
						q.setParameter(i, Long.parseLong(String.valueOf(f)));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	// @SuppressWarnings("unchecked")
	@Override
	public String getNextCode(Class<Po> pc, String fieldname, String prefix, int at) throws Exception {
		// String pre_code = prefix + DateUtil.format(new Date(), "yyyyMM");
		// String hql = "SELECT t FROM " + pc.getName() + " t ORDER BY " +
		// fieldname + " desc ";
		// Query q = this.em.createQuery(hql, pc);
		// q.setFirstResult(0);
		// q.setMaxResults(1);
		// List<Po> res = q.getResultList();
		// String code = pre_code + StringUtil.int2StringWithZero(1, at);
		// if (res.size() > 0) {
		// Field field = pc.getDeclaredField(fieldname);
		// field.setAccessible(true); // 设置访问权限，false只能访问public
		// code = String.valueOf(field.get(res.get(0)));
		// try {
		// if (code == null || code.trim().length() < 1 ||
		// code.trim().toLowerCase().equals("null")) {
		// code = pre_code + StringUtil.int2StringWithZero(1, at);
		// } else {
		// code = code.replace(pre_code, "");// 如果code为2014100100004，此时得到00004
		// int num = Integer.valueOf(code.substring(0, at)) + 1;// 得到新编号5
		// code = pre_code + StringUtil.int2StringWithZero(num, at);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// return code;
		return null;
	}

	public static void main(String[] args) {
		String a = " 1 2 ";
		System.out.println(a.trim());
	}

}
