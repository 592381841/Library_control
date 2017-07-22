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
 * StackRoom entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "stack_room", catalog = "librarysys")
public class StackRoom implements java.io.Serializable {

	// Fields

	private Integer id;
	private String srName;
	private String srAddress;
	private Set<Bookrack> bookracks = new HashSet<Bookrack>(0);

	// Constructors

	/** default constructor */
	public StackRoom() {
	}

	/** minimal constructor */
	public StackRoom(String srName, String srAddress) {
		this.srName = srName;
		this.srAddress = srAddress;
	}

	/** full constructor */
	public StackRoom(String srName, String srAddress, Set<Bookrack> bookracks) {
		this.srName = srName;
		this.srAddress = srAddress;
		this.bookracks = bookracks;
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

	@Column(name = "sr_name", nullable = false, length = 20)
	public String getSrName() {
		return this.srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}

	@Column(name = "sr_address", nullable = false, length = 200)
	public String getSrAddress() {
		return this.srAddress;
	}

	public void setSrAddress(String srAddress) {
		this.srAddress = srAddress;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stackRoom")
	public Set<Bookrack> getBookracks() {
		return this.bookracks;
	}

	public void setBookracks(Set<Bookrack> bookracks) {
		this.bookracks = bookracks;
	}

}