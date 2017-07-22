package com.li.common.dto;

public class ReaderTypeDto {
	private Integer id;
	private String typeName;
	private Integer borrow;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getBorrow() {
		return borrow;
	}
	public void setBorrow(Integer borrow) {
		this.borrow = borrow;
	}
}
