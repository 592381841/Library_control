package com.li.common.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Bookrack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bookrack", catalog = "librarysys")
public class Bookrack implements java.io.Serializable {

	// Fields

	private Integer bookrackId;
	private StackRoom stackRoom;
	private String bookrName;
	private Integer brAddBookNumber;
	private Set<BookInfo> bookInfos = new HashSet<BookInfo>(0);

	// Constructors

	/** default constructor */
	public Bookrack() {
	}

	/** minimal constructor */
	public Bookrack(StackRoom stackRoom, String bookrName,
			 Integer brAddBookNumber) {
		this.stackRoom = stackRoom;
		this.bookrName = bookrName;
		this.brAddBookNumber = brAddBookNumber;
	}

	/** full constructor */
	public Bookrack(StackRoom stackRoom, String bookrName,
			 Integer brAddBookNumber,
			Set<BookInfo> bookInfos) {
		this.stackRoom = stackRoom;
		this.bookrName = bookrName;
		this.brAddBookNumber = brAddBookNumber;
		this.bookInfos = bookInfos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bookrack_id", unique = true, nullable = false)
	public Integer getBookrackId() {
		return this.bookrackId;
	}

	public void setBookrackId(Integer bookrackId) {
		this.bookrackId = bookrackId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sr_id", nullable = false)
	public StackRoom getStackRoom() {
		return this.stackRoom;
	}

	public void setStackRoom(StackRoom stackRoom) {
		this.stackRoom = stackRoom;
	}

	@Column(name = "bookr_name", nullable = false, length = 45)
	public String getBookrName() {
		return this.bookrName;
	}

	public void setBookrName(String bookrName) {
		this.bookrName = bookrName;
	}



	@Column(name = "br_add_book_number", nullable = false)
	public Integer getBrAddBookNumber() {
		return this.brAddBookNumber;
	}

	public void setBrAddBookNumber(Integer brAddBookNumber) {
		this.brAddBookNumber = brAddBookNumber;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bookrack")
	public Set<BookInfo> getBookInfos() {
		return this.bookInfos;
	}

	public void setBookInfos(Set<BookInfo> bookInfos) {
		this.bookInfos = bookInfos;
	}

}