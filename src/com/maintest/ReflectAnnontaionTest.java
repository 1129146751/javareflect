package com.maintest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.model.Dic;
import com.model.Student;

/**
 * 反射和自定义注解
 * 
 * @author Administrator
 *
 */
public class ReflectAnnontaionTest {
	public static void main(String[] args) throws Exception{
		// 获取class类型的对象
		Class clazz = Student.class;
		Student s=new Student();
		s.setClassId("101");
		// 获取该类所有的属性
		Field[] fields=clazz.getDeclaredFields();
		//循环 判断 属性 是否添加注解
		for(Field field:fields){
			field.setAccessible(true);
			//判断属性上面是否加上 dic的注解 
			Boolean b=field.isAnnotationPresent(Dic.class);
			if(b){
				Dic a=field.getAnnotation(Dic.class);
				System.out.println("注解的值"+a.typeCode());
				System.out.println("这个指段上面有dic注解："+field.getName());
				System.out.println("指段值："+field.get(s));
			}
		}
	}
}
