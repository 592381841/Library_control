package com.li.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LibraryAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "library_admin", catalog = "librarysys")
public class LibraryAdmin implements java.io.Serializable {

	// Fields

	private String account;
	private String idCard;
	private String pic;
	private byte[] passwd;
	private String adminName;
	private Boolean aisRoot;
	private Boolean asrJu;
	private Boolean libJu;
	private Boolean readTypeJu;
	private Boolean readerJu;
	private Boolean aisStatus;

	// Constructors

	/** default constructor */
	public LibraryAdmin() {
	}

	/** minimal constructor */
	public LibraryAdmin(String account, String idCard, byte[] passwd,
			String adminName, Boolean aisRoot, Boolean asrJu, Boolean libJu,
			 Boolean readTypeJu, Boolean readerJu,
			Boolean aisStatus) {
		this.account = account;
		this.idCard = idCard;
		this.passwd = passwd;
		this.adminName = adminName;
		this.aisRoot = aisRoot;
		this.asrJu = asrJu;
		this.libJu = libJu;
		this.readTypeJu = readTypeJu;
		this.readerJu = readerJu;
		this.aisStatus = aisStatus;
	}

	/** full constructor */
	public LibraryAdmin(String account, String idCard, String pic,
			byte[] passwd, String adminName, Boolean aisRoot, Boolean asrJu,
			Boolean libJu, Boolean readTypeJu,
			Boolean readerJu, Boolean aisStatus) {
		this.account = account;
		this.idCard = idCard;
		this.pic = pic;
		this.passwd = passwd;
		this.adminName = adminName;
		this.aisRoot = aisRoot;
		this.asrJu = asrJu;
		this.libJu = libJu;
		this.readTypeJu = readTypeJu;
		this.readerJu = readerJu;
		this.aisStatus = aisStatus;
	}

	// Property accessors
	@Id
	@Column(name = "account", unique = true, nullable = false, length = 11)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	@Column(name = "passwd", nullable = false)
	public byte[] getPasswd() {
		return this.passwd;
	}

	public void setPasswd(byte[] passwd) {
		this.passwd = passwd;
	}

	@Column(name = "admin_name", nullable = false, length = 45)
	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "ais_root", nullable = false)
	public Boolean getAisRoot() {
		return this.aisRoot;
	}

	public void setAisRoot(Boolean aisRoot) {
		this.aisRoot = aisRoot;
	}

	@Column(name = "asr_ju", nullable = false)
	public Boolean getAsrJu() {
		return this.asrJu;
	}

	public void setAsrJu(Boolean asrJu) {
		this.asrJu = asrJu;
	}

	@Column(name = "lib_ju", nullable = false)
	public Boolean getLibJu() {
		return this.libJu;
	}

	public void setLibJu(Boolean libJu) {
		this.libJu = libJu;
	}



	@Column(name = "read_type_ju", nullable = false)
	public Boolean getReadTypeJu() {
		return this.readTypeJu;
	}

	public void setReadTypeJu(Boolean readTypeJu) {
		this.readTypeJu = readTypeJu;
	}

	@Column(name = "reader_ju", nullable = false)
	public Boolean getReaderJu() {
		return this.readerJu;
	}

	public void setReaderJu(Boolean readerJu) {
		this.readerJu = readerJu;
	}

	@Column(name = "ais_status", nullable = false)
	public Boolean getAisStatus() {
		return this.aisStatus;
	}

	public void setAisStatus(Boolean aisStatus) {
		this.aisStatus = aisStatus;
	}

}