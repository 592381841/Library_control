package com.li.common.dto;

import java.sql.Timestamp;

public class BorrowInfoDto {

	private String id;
	private BookInfoDto bookInfoDto;
	private ReaderDto readerDto;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Boolean boorowIsRenew;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BookInfoDto getBookInfoDto() {
		return bookInfoDto;
	}
	public void setBookInfoDto(BookInfoDto bookInfoDto) {
		this.bookInfoDto = bookInfoDto;
	}
	public ReaderDto getReaderDto() {
		return readerDto;
	}
	public void setReaderDto(ReaderDto readerDto) {
		this.readerDto = readerDto;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Boolean getBoorowIsRenew() {
		return boorowIsRenew;
	}
	public void setBoorowIsRenew(Boolean boorowIsRenew) {
		this.boorowIsRenew = boorowIsRenew;
	}
	public Integer getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(Integer borrowStatus) {
		this.borrowStatus = borrowStatus;
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
	private Integer borrowStatus;
	private Timestamp operationTime;
	private Boolean operationType;
}
