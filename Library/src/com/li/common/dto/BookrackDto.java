package com.li.common.dto;

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

import com.li.common.domain.StackRoom;

/**
 * Bookrack entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bookrack", catalog = "librarysys")
public class BookrackDto implements java.io.Serializable {

	// Fields

	private Integer bookrackId;

	private String bookrName;
	private Integer bookNumber;
	public Integer getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(Integer bookNumber) {
		this.bookNumber = bookNumber;
	}
	private Integer brAddBookNumber;
	public Integer getBookrackId() {
		return bookrackId;
	}
	public void setBookrackId(Integer bookrackId) {
		this.bookrackId = bookrackId;
	}
	public String getBookrName() {
		return bookrName;
	}
	public void setBookrName(String bookrName) {
		this.bookrName = bookrName;
	}
	
	public Integer getBrAddBookNumber() {
		return brAddBookNumber;
	}
	public void setBrAddBookNumber(Integer brAddBookNumber) {
		this.brAddBookNumber = brAddBookNumber;
	}

	
}