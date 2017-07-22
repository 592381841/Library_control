package com.li.common.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BorrowOperation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "borrow_operation", catalog = "librarysys")
public class BorrowOperation implements java.io.Serializable {

	// Fields

	private String id;
	private BorrowInfo borrowInfo;
	private Timestamp operationTime;
	private Boolean operationType;

	// Constructors

	/** default constructor */
	public BorrowOperation() {
	}

	/** full constructor */
	public BorrowOperation(String id, BorrowInfo borrowInfo,
			Timestamp operationTime, Boolean operationType) {
		this.id = id;
		this.borrowInfo = borrowInfo;
		this.operationTime = operationTime;
		this.operationType = operationType;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 45)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "borrow_id", nullable = false)
	public BorrowInfo getBorrowInfo() {
		return this.borrowInfo;
	}

	public void setBorrowInfo(BorrowInfo borrowInfo) {
		this.borrowInfo = borrowInfo;
	}

	@Column(name = "operation_time", nullable = false, length = 19)
	public Timestamp getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}

	@Column(name = "operation_type", nullable = false)
	public Boolean getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Boolean operationType) {
		this.operationType = operationType;
	}

}