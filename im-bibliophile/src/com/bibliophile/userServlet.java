package com.bibliophile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;


import java.sql.*;
import java.util.List;


@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private userDbUtil userUtil;
    @Resource(name="jdbc/bibliophile")
    private DataSource dataSource;
    
    @Override
	public void init() throws ServletException 
    {
			super.init();		
			
			try {				
				userUtil = new userDbUtil(dataSource);				
			}
			catch(Exception exc)
			{
				throw new ServletException(exc);
			}	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String theCommand = request.getParameter("command");
			
			switch(theCommand)
			{				
				case "LISTBOOKS" :
					
					listbooks(request, response);
					break;
					
				case "ADDBOOK" :
					addbook(request, response);
					break;
					
				case "LOAD" :
					getbooks(request, response);
					break;
					
				case "UPDATE" :
					updatebook(request, response);			
					break;
					
				case "DELETE" : 
					deletebook(request, response);
					break;
					
				case "SEARCH" :
					searchbook(request, response);
					break;	
					
				case "LISTWISHBOOK" :
					listwishbook(request, response);
					break;
					
				case "ADDWISHBOOK" :
					addwishbook(request, response);
					break;
					
				case "LOADWISHBOOK" : 
					loadwishbook(request, response);
					break;
					
				case "UPDATEWISHBOOK" :
					updatewishbook(request,response);
					break;
					
				case "DELETEWISHBOOK" :
					deletewishbook(request, response);
					break;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String theCommand = request.getParameter("command");
			
			switch(theCommand)
			{
				case "LOGIN" : 
					login(request, response);
					break;
					
				case "REGISTER" :
					Registeruser(request, response);
					break;
					
				case "SIGNOUT" :
					signout(request, response);
					break;
					
				case "DELETEACCOUNT" :
					deleteaccount(request, response);
					
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	//methods outside servlet class
	
	private void deleteaccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		String username = request.getParameter("uname");
		String password = request.getParameter("upass");
		
		userUtil.deleteaccount(username, password);
		
			/*PrintWriter out = response.getWriter();
		 out.println("<script type=\"text/javascript\">");
		 out.println("alert('Your account has been deleted');");
		 out.println("location='index.html';");
		 out.println("</script>");
		 */
		 RequestDispatcher rd=request.getRequestDispatcher("index.html");
		 rd.forward(request, response);
		
	}
	private void searchbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException 
	{
		String theusername = request.getParameter("username");
		String theSearchBook = request.getParameter("theSearchBook");
		
		List<Book> thebook = userUtil.searchbookdb(theusername, theSearchBook);
		
		request.setAttribute("USER_BOOKS", thebook);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/after-search.jsp");
		dispatcher.forward(request, response);
				
	}
	
	private void deletebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String bookname= request.getParameter("bookname");
		
		Book ubook = new Book(username, bookname);
		
		userUtil.deletebookdb(ubook);
				
		response.sendRedirect(request.getContextPath() + "/userServlet?command=LISTBOOKS&uname="+username);
	}
	
	private void updatebook(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		String review = request.getParameter("review");
		String date = request.getParameter("date");
		String username = request.getParameter("username");
		
		Book ubook = new Book(username, bookname, author, date, review);
				
		userUtil.updatebookdb(ubook);
		
		response.sendRedirect(request.getContextPath() + "/userServlet?command=LISTBOOKS&uname="+username);
	}
	private void getbooks(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String bookname= request.getParameter("bookname");
		String username= request.getParameter("username");
		
		Book books = userUtil.getbooks(username, bookname);
		
		request.setAttribute("USER_BOOKS", books);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-book-form.jsp");
		dispatcher.forward(request, response);
		
	}
	private void listbooks(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String username = request.getParameter("uname");	
		Book thebook= new Book(username);
		List<Book> books = userUtil.listbook(thebook);
		
		request.setAttribute("USER_BOOKS", books);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookshelf.jsp");
		dispatcher.forward(request, response);
		
	}
	private void signout(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		response.sendRedirect("index.html");		
	}
	
	private void addbook(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException 
	{
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		String review = request.getParameter("review");
		String date = request.getParameter("date");
		String username = request.getParameter("username");		
		
		
		Book book = new Book(username, bookname, author, date , review);
				
		userUtil.addbookdb(book);
		
		response.sendRedirect(request.getContextPath() + "/userServlet?command=LISTBOOKS&uname="+username);
	}
	private void Registeruser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException 
	{

		String uname =request.getParameter("uname");
		String upass = request.getParameter("upass");
		
		Register register = new Register(uname, upass);
		
		userUtil.registeruser(register);
		 
		response.sendRedirect(request.getContextPath() + "/Login.jsp");
		
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException 
	{
		String uname =request.getParameter("uname");
		String upass = request.getParameter("upass");
		
		if(userUtil.check(uname,upass))
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", uname);
			response.sendRedirect("main.jsp");
		}
		else
		{
			response.sendRedirect("Login.jsp");
		}
		
	}
	
	private void listwishbook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
	{
		String busername = request.getParameter("username");
		
		List<Wishbook> books = userUtil.listwishbookdb(busername);
		
		request.setAttribute("WISH_BOOKS", books);
		
		RequestDispatcher rd = request.getRequestDispatcher("/wishlist.jsp");
		
		rd.forward(request, response);
		
	}
	
	private void addwishbook(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException 
	{
		String wishusername= request.getParameter("wishusername");
		String bookname = request.getParameter("bookname");
		String description = request.getParameter("description");
		String progress = request.getParameter("progress");
		String date = request.getParameter("date");
		
		Wishbook book = new Wishbook(wishusername, bookname, description, progress, date);
		
		userUtil.addwishbookdb(book);
		
		response.sendRedirect(request.getContextPath() + "/userServlet?command=LISTWISHBOOK&username="+wishusername);
		
		
	}
	
	private void loadwishbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		String wishusername = request.getParameter("wishusername");
		String wbookname = request.getParameter("bookname");
		
		Wishbook book = userUtil.loadwishbookdb(wishusername, wbookname);
		
		request.setAttribute("WISH_BOOKS", book);
		
		RequestDispatcher rd = request.getRequestDispatcher("/update-wishlist-form.jsp");
		
		rd.forward(request, response);
	}
	
	private void updatewishbook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		String wishusername= request.getParameter("wishusername");
		String bookname = request.getParameter("bookname");
		String description = request.getParameter("description");
		String progress = request.getParameter("progress");
		String date = request.getParameter("date");
		
		Wishbook book = new Wishbook(wishusername, bookname, description, progress, date);
		
		userUtil.updatewishbookdb(book);
		
		response.sendRedirect(request.getContextPath() + "/userServlet?command=LISTWISHBOOK&username="+wishusername);		
	}
	
	private void deletewishbook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		String wishusername= request.getParameter("wishusername");
		String bookname = request.getParameter("bookname");
		
		userUtil.deletewishbookdb(wishusername, bookname);
		
		response.sendRedirect(request.getContextPath() + "/userServlet?command=LISTWISHBOOK&username="+wishusername);
		
	}
}












