package com.li.common.dto;

import java.sql.Timestamp;

import com.li.common.domain.BorrowInfo;

public class BorrowOperationDto {
	private String id;
	private BorrowInfoDto borrowInfoDto;
	private Timestamp operationTime;
	private Boolean operationType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BorrowInfoDto getBorrowInfoDto() {
		return borrowInfoDto;
	}
	public void setBorrowInfoDto(BorrowInfoDto borrowInfoDto) {
		this.borrowInfoDto = borrowInfoDto;
	}
	public Timestamp getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}
	public Boolean getOperationType() {
		return operationType;
	}
	public void setOperationType(Boolean operationType) {
		this.operationType = operationType;
	}
	

}
