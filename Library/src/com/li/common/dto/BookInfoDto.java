package com.li.common.dto;

import com.li.common.domain.BookType;
import com.li.common.domain.Bookrack;

public class BookInfoDto {
	private Integer id;
	private BookTypeDto bookTypeDto;
	private boolean bookUnuse;
	private String collectInfo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BookTypeDto getBookTypeDto() {
		return bookTypeDto;
	}
	public void setBookTypeDto(BookTypeDto bookTypeDto) {
		this.bookTypeDto = bookTypeDto;
	}

	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public boolean isBookUnuse() {
		return bookUnuse;
	}
	public void setBookUnuse(boolean bookUnuse) {
		this.bookUnuse = bookUnuse;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookPress() {
		return bookPress;
	}
	public void setBookPress(String bookPress) {
		this.bookPress = bookPress;
	}
	public Integer getBookrackNumber() {
		return bookrackNumber;
	}
	public void setBookrackNumber(Integer bookrackNumber) {
		this.bookrackNumber = bookrackNumber;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getCollectInfo() {
		return collectInfo;
	}
	public void setCollectInfo(String collectInfo) {
		this.collectInfo = collectInfo;
	}
	public Boolean getBookIsBorrow() {
		return bookIsBorrow;
	}
	public void setBookIsBorrow(Boolean bookIsBorrow) {
		this.bookIsBorrow = bookIsBorrow;
	}
	public String getBookIntroduce() {
		return bookIntroduce;
	}
	public void setBookIntroduce(String bookIntroduce) {
		this.bookIntroduce = bookIntroduce;
	}
	public Boolean getBookIsDelete() {
		return bookIsDelete;
	}
	public void setBookIsDelete(Boolean bookIsDelete) {
		this.bookIsDelete = bookIsDelete;
	}
	private String bookId;
	private String bookName;
	private String bookPress;
	private Integer bookrackNumber;
	private String bookWriter;
	private Boolean bookIsBorrow;
	private String bookIntroduce;
	private Boolean bookIsDelete;
}
