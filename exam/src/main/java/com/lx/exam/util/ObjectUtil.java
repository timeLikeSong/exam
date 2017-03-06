package com.lx.exam.util;

import java.lang.reflect.Field;
import java.util.Date;

public class ObjectUtil {

	/**
	 * 相同属性的对象赋值，如果vo的日期型字段不是长日期，请在vo中写截取方法
	 * @param vo 被赋值对象
	 * @param po 取值对象
	 */
	public static void o2o(Object seto, Object geto) {
		for (Field f1 : seto.getClass().getDeclaredFields()) {
			if (!f1.getName().equals("serialVersionUID")) {
				// 设置访问权限，false只能访问public
				f1.setAccessible(true);
				for (Field f2 : geto.getClass().getDeclaredFields()) {
					f2.setAccessible(true);
					String t1 = f1.getType().toString().toLowerCase();
					if (t1.contains(".")) {
						t1 = t1.substring(t1.lastIndexOf(".") + 1);
					}
					String t2 = f2.getType().toString().toLowerCase();
					if (t2.contains(".")) {
						t2 = t2.substring(t2.lastIndexOf(".") + 1);
					}
					// 完全相同的字段
					if (f1.getName().equals(f2.getName()) && t1.equals(t2)) {
						try {
							f1.set(seto, f2.get(geto));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					// seto日期，geto字符串
					if (f1.getName().equals(f2.getName()) && t1.equals("date") && t2.equals("string")) {
						try {
							System.out.println(f2.get(geto) + "");
							f1.set(seto, DateUtil.parseDate(f2.get(geto) + "", "yyyy-MM-dd HH:mm:ss"));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					// seto字符串，geto日期
					if (f1.getName().equals(f2.getName()) && t2.equals("date") && t1.equals("string")) {
						try {
							f1.set(seto, DateUtil.format((Date) (f2.get(geto)), "yyyy-MM-dd HH:mm:ss"));
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

}
