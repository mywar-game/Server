package com.adminTool.bo;

/**
 * reload  常量类
 * @author Administrator
 *
 */
public class RefreshClass {

	private Integer id;
	private String className;
	private String classDesc;
	public RefreshClass() {
		
	}
	public RefreshClass(Integer id, String className, String classDesc) {
		super();
		this.id = id;
		this.className = className;
		this.classDesc = classDesc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

}
