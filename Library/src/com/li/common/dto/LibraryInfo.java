package com.li.common.dto;

public class LibraryInfo {
	private String librarian_name;
	private String librarian_phone;
	private String librarian_address;
	private String library_name;
	private String modify_time;
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	@Override
	public String toString() {
		return "LibraryInfo [librarian_name=" + librarian_name + ", librarian_phone=" + librarian_phone
				+ ", librarian_address=" + librarian_address + ", library_name=" + library_name + ", modify_time="
				+ modify_time + ", librarian_email=" + librarian_email + ", library_intro=" + library_intro
				+ ", library_time=" + library_time + "]";
	}
	public String getLibrary_name() {
		return library_name;
	}
	public void setLibrary_name(String library_name) {
		this.library_name = library_name;
	}
	private String librarian_email;
	private String library_intro;
	private String library_time;
	public String getLibrarian_name() {
		return librarian_name;
	}
	public void setLibrarian_name(String librarian_name) {
		this.librarian_name = librarian_name;
	}
	public String getLibrarian_phone() {
		return librarian_phone;
	}
	public void setLibrarian_phone(String librarian_phone) {
		this.librarian_phone = librarian_phone;
	}
	public String getLibrarian_address() {
		return librarian_address;
	}
	public void setLibrarian_address(String librarian_address) {
		this.librarian_address = librarian_address;
	}
	public String getLibrarian_email() {
		return librarian_email;
	}
	public void setLibrarian_email(String librarian_email) {
		this.librarian_email = librarian_email;
	}
	public String getLibrary_intro() {
		return library_intro;
	}
	public void setLibrary_intro(String library_intro) {
		this.library_intro = library_intro;
	}
	public String getLibrary_time() {
		return library_time;
	}
	public void setLibrary_time(String library_time) {
		this.library_time = library_time;
	}

}
