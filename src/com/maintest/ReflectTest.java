package com.maintest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.model.Student;
/**
 * 通过放射获取 对象的方法，调用方法和属性 属性值
 * @author Administrator
 *
 */
public class ReflectTest {
	public static void main(String[] args) throws Exception{
		//一：Class类型对象的创建
		//Class对象在jvm里面是唯一，只有一份
		//获取类的Class类型的对象有三种方式
		//方式1  通过类名
		Class c1=Student.class;
		System.out.println(c1);
		//方式2 通过对象
		Class c2=new Student().getClass();
		System.out.println(c2);
		//方式3 通过类的全名 抵用Class.forname方法  但是会抛异常  编译 异常
		try{
		Class c3=Class.forName("com.model.Student");
		System.out.println(c3);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Class对象是唯一的："+(c1==c2));
		System.out.println("---------------类加载器，类实现的接口---------------");
		System.out.println("类加载器:"+c1.getClassLoader());
		System.out.println("实现的借口："+c1.getInterfaces());
		System.out.println("父类"+c1.getSuperclass());
		System.out.println("--------------构造器 当前类public------------");
		Constructor[] constructors=c1.getConstructors();
		for(Constructor constr:constructors){
			System.out.println("构造方法不能使用Method的方式获取：当前public的构造方法--"+constr);
			System.out.println(constr.newInstance());
		}
		System.out.println("--------------构造器所有的构造方法 public priavet------------");
		Constructor[] constructors2=c1.getDeclaredConstructors();
		for(Constructor constr:constructors2){
			System.out.println("构造方法不能使用Method的方式获取："+constr);
//			constr.newInstance();
		}
		System.out.println("-----------通过参数类型 个数获取指定的构造器-----------");
		//
		Constructor contrByParamter=c1.getDeclaredConstructor(String.class,Integer.class);
		contrByParamter.setAccessible(true);
		System.out.println(contrByParamter.newInstance("通过参数类型 个数获取指定的构造器",123));
		//二：通过放射 获取目标类的属性名
		/*
		 * getDecaredField 只能获取当前类的属性  包括 私有，公用  不能获取父类及以上的属性
		 * */
		Field[] declaredField=c1.getDeclaredFields();
		for(Field f:declaredField){
//			f.setAccessible(false);
			System.out.println("declaredField：---"+f);
			System.out.println("字段名：---"+f.getName());
			
		}
		/**
		 * 只能获取 “当前类”  和 “父类 ” 中的public修饰的属性
		 */
		Field[] field=c1.getFields();
		for(Field f:field){
			f.setAccessible(true);
			System.out.println(f);
		}
		/**
		 * 通过字段名 获取字段 必须是public修饰的字段
		 */
		Field adress=c1.getField("adress");
		System.out.println(adress);
		//报错
		/*Field classId=c1.getField("classId");
		System.out.println(classId);*/
		/*通过指段名字 获取指段对应的值*/
		Student s=(Student)c1.newInstance();
		s.setAdress("重庆");
		Object adressValue=adress.get(s);
		System.out.println("指段("+adress.getName()+")对应的值:"+adressValue);
		
		System.out.println("方法：-------------------------------------------------------------");
		
		//获取该类的所有方法  private public 不包含构造方法
		Method[] methods=c1.getDeclaredMethods();
		for(Method method:methods){
			System.out.println("当前类的所有方法："+method.getName());
		}
		//获取当前类中所有的public 方法 包括父类
		Method[] methodPublic=c1.getMethods();
		for(Method method:methodPublic){
			System.out.println("当前类及父类中所有的public方法："+method.getName()+"-----参数");
			for(Class ccc:method.getParameterTypes()){
				System.out.println("参数类型"+ccc.getName());
			}
			for(Parameter ccc:method.getParameters()){
				System.out.println("参"+ccc.getName());
			}
		}
		//通过方法名  调用方法  方法名和参数的Class类型对象
		Method method=c1.getDeclaredMethod("method",new Class[]{});
		System.out.println("该方法以前的权限："+method.isAccessible());
		method.setAccessible(true);//需要开启对私用方法的访问权限 不然会报错
		System.out.println(method);
		//目标对象和参数
		method.invoke(c1.newInstance(), new Object[]{});
		
		
		//通过方法名  调用方法  方法名和参数的Class类型对象   有参数
				Method method2=c1.getDeclaredMethod("method3",new Class[]{String.class});
				System.out.println("该方法以前的权限："+method2.isAccessible());
				method2.setAccessible(true);//需要开启对私用方法的访问权限 不然会报错
				System.out.println(method2);
				//目标对象和参数    如果 是在spring框架中  反射实例化   autowried等属性注入失败 需要通过一般是通过实现ApplicationContextAware接口，通过beanId去获取
				method2.invoke(c1.newInstance(), new Object[]{"战三 "});
				
				
		
	}

}
