package com.li.common.dto;

public class LibraryAdminDto {
	

	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Boolean getAisRoot() {
		return aisRoot;
	}
	public void setAisRoot(Boolean aisRoot) {
		this.aisRoot = aisRoot;
	}
	public Boolean getAsrJu() {
		return asrJu;
	}
	public void setAsrJu(Boolean asrJu) {
		this.asrJu = asrJu;
	}
	public Boolean getLibJu() {
		return libJu;
	}
	public void setLibJu(Boolean libJu) {
		this.libJu = libJu;
	}
	public Boolean getReadTypeJu() {
		return readTypeJu;
	}
	public void setReadTypeJu(Boolean readTypeJu) {
		this.readTypeJu = readTypeJu;
	}
	public Boolean getReaderJu() {
		return readerJu;
	}
	public void setReaderJu(Boolean readerJu) {
		this.readerJu = readerJu;
	}
	public Boolean getAisStatus() {
		return aisStatus;
	}
	public void setAisStatus(Boolean aisStatus) {
		this.aisStatus = aisStatus;
	}
	private String account;
	private String idCard;
	private String pic;
	private String adminName;

	private Boolean aisRoot;//是否是root  //图书馆设置//工作人员管理
	private Boolean asrJu; //书库管理
	private Boolean libJu;  //借阅管理
	private Boolean readTypeJu;  //用户类型管理
	private Boolean readerJu;  //读者管理
	private Boolean aisStatus; //停用开启自己
}
