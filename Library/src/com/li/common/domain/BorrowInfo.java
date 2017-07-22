package com.li.common.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * BorrowInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "borrow_info", catalog = "librarysys")
public class BorrowInfo implements java.io.Serializable {

	// Fields

	private String id;
	private Integer bookId;
	private String readerId;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Boolean boorowIsRenew;
	private Integer borrowStatus;
	private Set<BorrowOperation> borrowOperations = new HashSet<BorrowOperation>(
			0);

	// Constructors

	/** default constructor */
	public BorrowInfo() {
	}

	/** minimal constructor */
	public BorrowInfo(String id, Integer bookId, String readerId,
			Timestamp beginTime, Timestamp endTime, Boolean boorowIsRenew,
			Integer borrowStatus) {
		this.id = id;
		this.bookId = bookId;
		this.readerId = readerId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.boorowIsRenew = boorowIsRenew;
		this.borrowStatus = borrowStatus;
	}

	/** full constructor */
	public BorrowInfo(String id, Integer bookId, String readerId,
			Timestamp beginTime, Timestamp endTime, Boolean boorowIsRenew,
			Integer borrowStatus, Set<BorrowOperation> borrowOperations) {
		this.id = id;
		this.bookId = bookId;
		this.readerId = readerId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.boorowIsRenew = boorowIsRenew;
		this.borrowStatus = borrowStatus;
		this.borrowOperations = borrowOperations;
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

	@Column(name = "book_id", nullable = false)
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name = "reader_id", nullable = false, length = 45)
	public String getReaderId() {
		return this.readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	@Column(name = "begin_time", nullable = false, length = 19)
	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", nullable = false, length = 19)
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "boorow_is_renew", nullable = false)
	public Boolean getBoorowIsRenew() {
		return this.boorowIsRenew;
	}

	public void setBoorowIsRenew(Boolean boorowIsRenew) {
		this.boorowIsRenew = boorowIsRenew;
	}

	@Column(name = "borrow_status", nullable = false)
	public Integer getBorrowStatus() {
		return this.borrowStatus;
	}

	public void setBorrowStatus(Integer borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "borrowInfo")
	public Set<BorrowOperation> getBorrowOperations() {
		return this.borrowOperations;
	}

	public void setBorrowOperations(Set<BorrowOperation> borrowOperations) {
		this.borrowOperations = borrowOperations;
	}

}