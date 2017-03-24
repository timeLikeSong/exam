package com.lx.exam.common.service.itf;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.lx.exam.common.vo.PageBean;

/**
 * 泛型Service，通过指定Po，Vo，自动匹配数据对象，不再需要单独写Dao层。
 * @version 2.0
 */
public interface IBfService<Po, Vo, Sm> {

	/**
	 * 添加
	 * @param pc po类型
	 * @param vo vo对象
	 * @return vo对象
	 * @param idName 主键名
	 * @param idClassName 主键类名
	 * @throws Exception
	 */
	Vo add(Class<Po> pc, Vo vo, String idName, String idClassName) throws Exception;

	/**
	 * 删除。
	 * @param po Po对象
	 * @param smc 过滤类
	 * @param sm 过滤对象
	 * @return 删除条数
	 * @throws Exception
	 */
	int delete(Class<Po> po, Class<Sm> smc, Sm sm) throws Exception;

	/**
	 * 删除。
	 * @param po
	 * @param ids
	 * @param idname
	 * @return 删除条数。
	 * @throws Exception
	 */
	int delete(Class<Po> po, String ids, String idname) throws Exception;

	/**
	 * 编辑。
	 * @param pc
	 * @param vo
	 * @param idName
	 * @return 返回编辑后的Vo对象
	 * @throws Exception
	 */
	Vo edit(Class<Po> pc, Vo vo, String idName) throws Exception;

	Po edit(Po po) throws Exception;

	int execUpdate(String sql) throws Exception;

	int execUpdateNative(String sql) throws Exception;

	/**
	 * @param pc
	 * @param vc
	 * @param sm
	 * @return 返回获取的Vo对象
	 * @throws Exception
	 */
	Vo get(Class<Po> pc, Class<Vo> vc, Sm sm) throws Exception;

	Po get(Class<Po> pc, Sm sm) throws Exception;

	/**
	 * 通过ID获取vo对象
	 * @param pc
	 * @param vc
	 * @param id
	 * @return 返回Vo对象
	 * @throws Exception
	 */
	Vo getById(Class<Po> pc, Class<Vo> vc, Object id) throws Exception;

	Po getById(Class<Po> pc, Object id) throws Exception;

	/**
	 * 由SeachModel类的属性，拼凑查询条件。
	 * @param sm SeachModel类
	 * <P>
	 * SeachModel类各条件规则：
	 * <P>
	 * 通用规则：sm_[express]_[field name]
	 * <p>
	 * 重要1、 注意该属性的类型与[field name]对应的字段(Po)的类型一致。
	 * <P>
	 * 重要2、一些需要用"null"和"空字符串"表示“全部”“无限制”的字段，必须定义为封装类。
	 * <p>
	 * 例： String sm_eq_username; Long sm_eq_userid;
	 * <p>
	 * sm，SearchModel简写，标记该属性是Search的条件字段；
	 * <P>
	 * [express]表达式，取值：eq 等于，nq 不等于，lt 小于，gt 大于，like 模糊匹配，in 包含 ，...
	 * <P>
	 * 例：sm_eq_username 表示查询username等于该属性的值的user，username是user对象的1个属性；
	 * <P>
	 * <P>
	 * [express]表达式的特殊 sm_or ，可以直接写包含 or语句的sql。技巧：也可以写其他无规则的sql，如is not null 等等。 
	 * <P>
	 * @param needOrderBy 是否需要排序。
	 * @return 返回Map，keySet:["hql","fields"]。
	 * <P>
	 * hql：String，拼凑结果；fields：Map<String,Object>，封装要赋值的字段名和字段数值的键值对。
	 * <p>
	 * 调用示例： map.get("hql") ,map.get("fields")。
	 * @throws Exception
	 */
	Map<String, Object> getConditions(Sm sm, Boolean needOrderBy) throws Exception;

	/**
	 * 获取用SearchModel过滤过以后的总条数
	 * @param pc po类型
	 * @param sm SearchModel对象
	 * @return 统计数量，Long类。
	 * @throws Exception
	 */
	Long getCount(Class<Po> pc, Sm sm) throws Exception;

	/**
	 * 执行普通sql查询语句。
	 * @param sql
	 * @return 统计结果，Long类。
	 */
	Long getCountBySql(String sql);

	/**
	 * 获取用SearchModel过滤过以后的集合
	 * @param pc po类型
	 * @param vc vo类型
	 * @param sm SearchModel对象
	 * @param pb 分页PageBean对象
	 * @return ss
	 * @throws Exception
	 */
	List<Vo> list(Class<Po> pc, Class<Vo> vc, Sm sm, PageBean pb) throws Exception;

	/**
	 * 返回Po的list
	 * @param pc
	 * @param sm
	 * @param pb
	 * @return ss
	 * @throws Exception
	 */
	List<Po> list(Class<Po> pc, Sm sm, PageBean pb) throws Exception;

	@SuppressWarnings("rawtypes")
	List listBySql(String sql);

	/**
	 * 设置getConditions返回的hql中的带“：”的参数的值。
	 * <P>
	 * @param q 被赋值的javax.persistence.TypedQuery<Po>对象
	 * @param fields 用于赋值的字段集合，详见com.denas.bfw.common.vo.HgField类
	 * @throws Exception
	 */
	void setParamOfConditions(TypedQuery<Po> q, Map<String, Object> fields) throws Exception;

	/**
	 * 获取自动编号，规则： 前缀+年月+自定义位数自增数字
	 * @param pc 对象实体类
	 * @param fieldname 存放编号的字段名
	 * @param prefix 前缀
	 * @param at 占几位数字，例： at=5 即 00000
	 * @return
	 * @throws Exception
	 */
	String getNextCode(Class<Po> pc, String fieldname, String prefix, int at) throws Exception;

}
