package com.bibliophile;

public class Wishbook
{
	private String wishusername;
	private String bookname;
	private String description;
	private String progress;
	private String date;
	public Wishbook(String wishusername, String bookname, String description, String progress, String date) {
		super();
		this.wishusername = wishusername;
		this.bookname = bookname;
		this.description = description;
		this.progress = progress;
		this.date = date;
	}
	public String getWishusername() {
		return wishusername;
	}
	public void setWishusername(String wishusername) {
		this.wishusername = wishusername;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Wishbook [wishusername=" + wishusername + ", bookname=" + bookname + ", description=" + description
				+ ", progress=" + progress + ", date=" + date + "]";
	}
	
	
}
