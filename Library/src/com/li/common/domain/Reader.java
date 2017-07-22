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
 * Reader entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "reader", catalog = "librarysys")
public class Reader implements java.io.Serializable {

	// Fields

	private Integer id;
	private ReaderType readerType;
	private String idCard;
	private String pic;
	private Boolean risStatus;
	private String account;
	private byte[] passwd;
	private String usersName;

	// Constructors

	/** default constructor */
	public Reader() {
	}

	/** minimal constructor */
	public Reader(ReaderType readerType, String idCard, Boolean risStatus,
			String account, byte[] passwd, String usersName) {
		this.readerType = readerType;
		this.idCard = idCard;
		this.risStatus = risStatus;
		this.account = account;
		this.passwd = passwd;
		this.usersName = usersName;
	}

	/** full constructor */
	public Reader(ReaderType readerType, String idCard, String pic,
			Boolean risStatus, String account, byte[] passwd, String usersName) {
		this.readerType = readerType;
		this.idCard = idCard;
		this.pic = pic;
		this.risStatus = risStatus;
		this.account = account;
		this.passwd = passwd;
		this.usersName = usersName;
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
	@JoinColumn(name = "type", nullable = false)
	public ReaderType getReaderType() {
		return this.readerType;
	}

	public void setReaderType(ReaderType readerType) {
		this.readerType = readerType;
	}

	@Column(name = "id_card", nullable = false, length = 18)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "pic", length = 200)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "ris_status", nullable = false)
	public Boolean getRisStatus() {
		return this.risStatus;
	}

	public void setRisStatus(Boolean risStatus) {
		this.risStatus = risStatus;
	}

	@Column(name = "account", nullable = false, length = 11)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "passwd", nullable = false)
	public byte[] getPasswd() {
		return this.passwd;
	}

	public void setPasswd(byte[] passwd) {
		this.passwd = passwd;
	}

	@Column(name = "users_name", nullable = false, length = 45)
	public String getUsersName() {
		return this.usersName;
	}

	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

}