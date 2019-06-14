package com.model;

public class Student extends Person {
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 班级号
	 */
	@SuppressWarnings(value = { "" })
	@Dic(typeCode = "classTpe")
	private String classId;

	public String adress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", classId=" + classId + "]";
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	private void method() {
		System.out.println("--------这是一个method");
	}

	public void method2() {
		System.out.println("--------这是一个method2");
	}

	public void method3(String str) {
		System.out.println("--------这是一个method3,参数值：" + str);
	}

	private Student(String str) {
		System.out.println("含参构造器" + str);
	}

	private Student(String str, Integer ints) {
		System.out.println("含参构造器" + str+ints);
	}

	public Student() {

	}
}
