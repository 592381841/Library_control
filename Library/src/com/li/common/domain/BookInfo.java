package com.li.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BookInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "book_info", catalog = "librarysys")
public class BookInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Bookrack bookrack;
	private BookType bookType;
	private String bookId;
	private String bookName;
	private String bookPress;
	private Integer bookrackNumber;
	private String bookWriter;
	private Boolean bookIsBorrow;
	private String bookIntroduce;
	private Boolean bookIsDelete;

	// Constructors

	/** default constructor */
	public BookInfo() {
	}

	/** minimal constructor */
	public BookInfo(Bookrack bookrack, BookType bookType, String bookId,
			String bookName, String bookPress, Integer bookrackNumber,
			Boolean bookIsBorrow, Boolean bookIsDelete) {
		this.bookrack = bookrack;
		this.bookType = bookType;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPress = bookPress;
		this.bookrackNumber = bookrackNumber;
		this.bookIsBorrow = bookIsBorrow;
		this.bookIsDelete = bookIsDelete;
	}

	/** full constructor */
	public BookInfo(Bookrack bookrack, BookType bookType, String bookId,
			String bookName, String bookPress, Integer bookrackNumber,
			String bookWriter, Boolean bookIsBorrow, String bookIntroduce,
			Boolean bookIsDelete) {
		this.bookrack = bookrack;
		this.bookType = bookType;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookPress = bookPress;
		this.bookrackNumber = bookrackNumber;
		this.bookWriter = bookWriter;
		this.bookIsBorrow = bookIsBorrow;
		this.bookIntroduce = bookIntroduce;
		this.bookIsDelete = bookIsDelete;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookrack_id", nullable = false)
	public Bookrack getBookrack() {
		return this.bookrack;
	}

	public void setBookrack(Bookrack bookrack) {
		this.bookrack = bookrack;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_type_id", nullable = false)
	public BookType getBookType() {
		return this.bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	@Column(name = "book_id", nullable = false , length = 45)
	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@Column(name = "book_name", nullable = false, length = 45)
	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name = "book_press", nullable = false, length = 100)
	public String getBookPress() {
		return this.bookPress;
	}

	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}

	@Column(name = "bookrack_number", nullable = false)
	public Integer getBookrackNumber() {
		return this.bookrackNumber;
	}

	public void setBookrackNumber(Integer bookrackNumber) {
		this.bookrackNumber = bookrackNumber;
	}

	@Column(name = "book_writer", length = 45)
	public String getBookWriter() {
		return this.bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	@Column(name = "book_is_borrow", nullable = false)
	public Boolean getBookIsBorrow() {
		return this.bookIsBorrow;
	}

	public void setBookIsBorrow(Boolean bookIsBorrow) {
		this.bookIsBorrow = bookIsBorrow;
	}

	@Column(name = "book_introduce", length = 2000)
	public String getBookIntroduce() {
		return this.bookIntroduce;
	}

	public void setBookIntroduce(String bookIntroduce) {
		this.bookIntroduce = bookIntroduce;
	}

	@Column(name = "book_is_delete", nullable = false)
	public Boolean getBookIsDelete() {
		return this.bookIsDelete;
	}

	public void setBookIsDelete(Boolean bookIsDelete) {
		this.bookIsDelete = bookIsDelete;
	}

}