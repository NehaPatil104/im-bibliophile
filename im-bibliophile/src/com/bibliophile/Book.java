package com.bibliophile;

public class Book 
{
	private String bookname;
	private String author;
	private String review;
	private String date;
	private String username;
	
	public Book(String username, String bookname, String author, String date, String review) {
		super();
		this.bookname = bookname;
		this.author = author;
		this.review = review;
		this.date = date;
		this.username = username;
	}
	
	
	public Book(String bookname, String author, String date, String review) {
		super();
		this.bookname = bookname;
		this.author = author;
		this.review = review;
		this.date = date;
	}
	

	public Book(String username) {
		super();
		this.username = username;
	}

	public Book(String username, String bookname) {
		super();
		this.username = username;
		this.bookname = bookname;
	}


	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;

	}
	@Override
	public String toString() {
		return "Book [bookname=" + bookname + ", author=" + author + ", review=" + review + ", date=" + date
				+ ", username=" + username + "]";
	}	
}
