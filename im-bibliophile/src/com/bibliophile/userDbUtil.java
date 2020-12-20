package com.bibliophile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class userDbUtil
{
	private DataSource dataSource;

	public userDbUtil(DataSource thedataSource) {
		dataSource= thedataSource;
	}

	public boolean check(String uname, String upass) throws SQLException
	{
		String sql="select * from user where username=? and password=?";
		Connection con= dataSource.getConnection();
		PreparedStatement Stmt = null;
		ResultSet rs = null;
		try
		{
			Stmt = con.prepareStatement(sql);
			
			Stmt.setString(1, uname);
			Stmt.setString(2, upass);
			
			rs = Stmt.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			close(con , Stmt ,rs);
		}
		
		return false;
	}

	public void registeruser(Register register) throws SQLException 
	{
		Connection con = dataSource.getConnection();
		String sql ="insert into user "
				+ "(username, password)"
				+ " values(?, ?)";
		PreparedStatement Stmt = con.prepareStatement(sql);
		try
		{			
			Stmt.setString(1, register.getUsername());
			Stmt.setString(2, register.getPassword());
			
			 Stmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			close(con , Stmt ,null);
		}
		
	}
	
	public List<Book> listbook(Book tempbooks) throws Exception
	{		
	
		List<Book> books = new ArrayList<>();
		Connection con = null;
		PreparedStatement Stmt = null;
		ResultSet rs = null;
		try
		{
			String sql= "select * from bookshelf where username=?";
			con = dataSource.getConnection();
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, tempbooks.getUsername());
			rs = Stmt.executeQuery();
			
			while(rs.next())
			{
				String username = rs.getString("username");
				String bookname = rs.getString("book_name");
				String author = rs.getString("author");
				String date = rs.getString("date");
				String review = rs.getString("review");
				
				Book tempbook = new Book(username, bookname, author, date, review);
				books.add(tempbook);
			}
			
			return books;
		}
		finally
		{
			close(con , Stmt ,rs);
		}
	}
	
	private static void close(java.sql.Connection con, java.sql.Statement Stmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(Stmt != null) {
				Stmt.close();
			}
			
			if(con != null) {
				con.close();
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addbookdb(Book book) throws SQLException 
	{
		Connection con =  dataSource.getConnection();
		String sql = "insert into bookshelf (username, book_name, author, date, review) values(?, ?, ?, ?, ?)";
		PreparedStatement Stmt = null;
		try 
		{	
			Stmt = con.prepareStatement(sql);	
			Stmt.setString(1, book.getUsername());
			Stmt.setString(2, book.getBookname());
			Stmt.setString(3, book.getAuthor());
			Stmt.setString(4, book.getDate());
			Stmt.setString(5, book.getReview());
			
			Stmt.execute();
		} 
		catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		finally
		{
			close(con , Stmt ,null);
		}
		
		
	}

	public Book getbooks(String username, String bookname) throws Exception 
	{
		String busername=username;
		String bbookname=bookname;
		
		Connection con = null ;
		PreparedStatement Stmt = null;
		ResultSet rs = null;
		Book tempbook = null;
		
		
		try
		{
			con = dataSource.getConnection();			
			String sql="select *from bookshelf where username=? and book_name=?";
		
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, busername);
			Stmt.setString(2, bbookname);
		
			rs = Stmt.executeQuery();
		
			if(rs.next())
			{
				String book_name = rs.getString("book_name");
				String author = rs.getString("author");
				String date = rs.getString("date");
				String review = rs.getString("review");
				
			 tempbook = new Book(book_name, author, date, review);
				
			}
			else
			{
				throw new Exception("Could not find Book name.");
			}
			return tempbook;
		}
		finally
		{
			close(con,Stmt,rs);
		}
	
	}

	public void updatebookdb(Book ubook)
	{
		Connection con = null;
		PreparedStatement Stmt = null;
		
		try
		{
			con = dataSource.getConnection();
			String sql = "update bookshelf set author=?, date=?, review=? where username=? and book_name=?";
			
			Stmt = con.prepareStatement(sql);
			
			Stmt.setString(1, ubook.getAuthor());
			Stmt.setString(2, ubook.getDate());
			Stmt.setString(3, ubook.getReview());
			Stmt.setString(4, ubook.getUsername());
			Stmt.setString(5, ubook.getBookname());
			
			Stmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			close(con,Stmt,null);
		}
	}

	public void deletebookdb(Book ubook)
	{
			
		Connection con = null;
		PreparedStatement Stmt = null;
		
		try 
		{
			con = dataSource.getConnection();
			String sql="delete from bookshelf where username=? and book_name=?";
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, ubook.getUsername());
			Stmt.setString(2, ubook.getBookname());
			
			Stmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			close(con, Stmt, null);
		}
	}

	public List<Book> searchbookdb(String theusername, String theSearchBook) throws SQLException 
	{
		List<Book> book = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement Stmt = null;
		ResultSet rs = null;
		
		String busername = theusername;
		String theSearchName = theSearchBook;
		
		try
		{
			con = dataSource.getConnection();

			if(theSearchName != null && theSearchName.trim().length()>0)
			{
				String sql = "select * from bookshelf where (lower(book_name) like ? or lower(author) like ?) and (username= ?)";
				Stmt = con.prepareStatement(sql);
				String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
				Stmt.setString(1, theSearchNameLike);
				Stmt.setString(2, theSearchNameLike);
				Stmt.setString(3, busername);
			}
			else
			{
				String sql= "select * from bookshelf where username=?";
				Stmt = con.prepareStatement(sql);
				Stmt.setString(1, busername);
			}
			
			rs= Stmt.executeQuery();
			
			while(rs.next())
			{
				
				String username = rs.getString("username");
				String bookname = rs.getString("book_name");
				String author = rs.getString("author");
				String date = rs.getString("date");
				String review = rs.getString("review");
				
			 Book thebook = new Book(username, bookname, author, date, review);
			 book.add(thebook);
			}
		return book;			
		}
		finally
		{
			close(con, Stmt, rs);
		}
	}

	public List<Wishbook> listwishbookdb(String busername) throws SQLException
	{
		
		List<Wishbook> book = new ArrayList<>();
		
		Connection con =null;
		PreparedStatement Stmt = null;
		ResultSet rs= null;
		String sql = "select * from wishlist where wishusername=?";
		
		try
		{
			con = dataSource.getConnection();
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, busername);
			
			rs = Stmt.executeQuery();
			
			while(rs.next())
			{
				String username = rs.getString("wishusername");
				String bookname = rs.getString("bookname");
				String description = rs.getString("description");
				String progress = rs.getString("progress");
				String date = rs.getString("date");
				
				Wishbook tempbook = new Wishbook(username, bookname, description, progress, date);
				book.add(tempbook);
			}
			return book;
		}
		finally 
		{
			close(con , Stmt, rs);
		}
	}

	public void addwishbookdb(Wishbook book) throws SQLException 
	{
		Connection con =null;
		PreparedStatement Stmt = null;
	
		String sql = "insert into wishlist (wishusername, bookname, description, progress, date) values(?, ?, ?, ?, ?)";
		
		try
		{
			con = dataSource.getConnection();
			
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, book.getWishusername());
			Stmt.setString(2, book.getBookname());
			Stmt.setString(3, book.getDescription());
			Stmt.setString(4, book.getProgress());
			Stmt.setString(5, book.getDate());
			
			Stmt.execute();
		}
		finally
		{
			close(con, Stmt , null);
		}
	}

	public Wishbook loadwishbookdb(String wishusername, String wbookname) throws SQLException 
	{
		String nwishusername = wishusername;
		String bbookname = wbookname;
		
		Wishbook book = null;
	
		Connection con =null;
		PreparedStatement Stmt = null;
		ResultSet rs = null;
	
		String sql = "select * from wishlist where wishusername=? and bookname=?";
		
		try
		{
			con = dataSource.getConnection();
			
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, nwishusername);
			Stmt.setString(2, bbookname);
			
			rs = Stmt.executeQuery();
			
			if(rs.next())
			{
				String username = rs.getString("wishusername");
				String bookname = rs.getString("bookname");
				String description = rs.getString("description");
				String progress = rs.getString("progress");
				String date = rs.getString("date");
				
				book = new Wishbook(username, bookname, description, progress, date);
			}
			return book;			
		}
		finally
		{
			close(con, Stmt, rs);
		}
	}

	public void updatewishbookdb(Wishbook book) throws SQLException
	{
		Connection con =null;
		PreparedStatement Stmt = null;
	
		String sql = "update wishlist set description=?, progress=?, date=? where wishusername=? and bookname=?";
		
		try
		{
			con = dataSource.getConnection();
			
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, book.getDescription());
			Stmt.setString(2, book.getProgress());
			Stmt.setString(3, book.getDate());
			Stmt.setString(4, book.getWishusername());
			Stmt.setString(5, book.getBookname());
			
			Stmt.executeUpdate();
		}
		finally
		{
			close(con , Stmt, null);
		}
		
	}

	public void deletewishbookdb(String wishusername, String bookname) throws SQLException
	{
		String bwishusername = wishusername;
		String bbookname = bookname;
		
		Connection con =null;
		PreparedStatement Stmt = null;
	
		String sql = "delete from wishlist where wishusername=? and bookname=?";
		
		try
		{
			con = dataSource.getConnection();
			
			Stmt = con.prepareStatement(sql);
			Stmt.setString(1, bwishusername);
			Stmt.setString(2, bbookname);
		
			Stmt.executeUpdate();
		}
		finally
		{
			close(con , Stmt, null);
		}	
	}

	public void deleteaccount(String username, String password) throws SQLException
	{
		String busername = username;
		String bpassword = password;
		
		Connection con =null;
		CallableStatement Stmt = null;
	
		
		try
		{
			con = dataSource.getConnection();
			
			Stmt = con.prepareCall("{call delete_user_account(?,?)}");
			Stmt.setString(1, busername);
			Stmt.setString(2, bpassword);
		
			Stmt.execute();
		}
		finally
		{
			close(con , Stmt, null);
		}	
		
	}
}





