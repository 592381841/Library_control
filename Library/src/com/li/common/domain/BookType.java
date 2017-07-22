package com.li.common.domain;

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
 * BookType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "book_type", catalog = "librarysys")
public class BookType implements java.io.Serializable {

	// Fields

	private String botyId;
	private String bookTypeName;
	private Set<BookInfo> bookInfos = new HashSet<BookInfo>(0);

	// Constructors

	/** default constructor */
	public BookType() {
	}

	/** minimal constructor */
	public BookType(String botyId, String bookTypeName) {
		this.botyId = botyId;
		this.bookTypeName = bookTypeName;
	}

	/** full constructor */
	public BookType(String botyId, String bookTypeName, Set<BookInfo> bookInfos) {
		this.botyId = botyId;
		this.bookTypeName = bookTypeName;
		this.bookInfos = bookInfos;
	}

	// Property accessors
	@Id
	@Column(name = "boty_id", unique = true, nullable = false, length = 45)
	public String getBotyId() {
		return this.botyId;
	}

	public void setBotyId(String botyId) {
		this.botyId = botyId;
	}

	@Column(name = "book_type_name", nullable = false, length = 45)
	public String getBookTypeName() {
		return this.bookTypeName;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookType")
	public Set<BookInfo> getBookInfos() {
		return this.bookInfos;
	}

	public void setBookInfos(Set<BookInfo> bookInfos) {
		this.bookInfos = bookInfos;
	}

}