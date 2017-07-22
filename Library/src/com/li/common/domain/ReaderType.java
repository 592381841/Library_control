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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ReaderType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reader_type", catalog = "librarysys")
public class ReaderType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typeName;
	private Integer borrow;
	private Set<Reader> readers = new HashSet<Reader>(0);

	// Constructors

	/** default constructor */
	public ReaderType() {
	}

	/** minimal constructor */
	public ReaderType(String typeName, Integer borrow) {
		this.typeName = typeName;
		this.borrow = borrow;
	}

	/** full constructor */
	public ReaderType(String typeName, Integer borrow, Set<Reader> readers) {
		this.typeName = typeName;
		this.borrow = borrow;
		this.readers = readers;
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

	@Column(name = "type_name", nullable = false, length = 45)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "borrow", nullable = false)
	public Integer getBorrow() {
		return this.borrow;
	}

	public void setBorrow(Integer borrow) {
		this.borrow = borrow;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "readerType")
	public Set<Reader> getReaders() {
		return this.readers;
	}

	public void setReaders(Set<Reader> readers) {
		this.readers = readers;
	}

}