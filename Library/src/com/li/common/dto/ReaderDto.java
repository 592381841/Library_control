package com.li.common.dto;

import com.li.common.domain.ReaderType;

public class ReaderDto {
	private int id;
	private String account;
	private ReaderTypeDto readerTypeDto;
	private String idCard;
	private String pic;
	private boolean risStatus;
	private String usersName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public ReaderTypeDto getReaderTypeDto() {
		return readerTypeDto;
	}
	public void setReaderTypeDto(ReaderTypeDto readerTypeDto) {
		this.readerTypeDto = readerTypeDto;
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
	
	public boolean isRisStatus() {
		return risStatus;
	}
	public void setRisStatus(boolean risStatus) {
		this.risStatus = risStatus;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}

}
