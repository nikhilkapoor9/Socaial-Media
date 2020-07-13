package com.project.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.project.db.PostDBUtil;
import com.project.models.User;

/**
 * Servlet implementation class EditPost
 */
@WebServlet("/EditPost")
public class EditPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPost() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Resource(name="jdbc/project")
    private DataSource datasource;
    private PostDBUtil postdb;
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		postdb = new PostDBUtil(datasource);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
				String editpost=request.getParameter("newpostcontent");
				int postid=Integer.parseInt(request.getParameter("postid"));
				HttpSession session=request.getSession(); 
				
				System.out.println(editpost);
				System.out.println(postid);
				
				User userpost = (User) session.getAttribute("user");
				System.out.println("Email");
				System.out.println(userpost.getEmail());
				
				userpost.setPost(editpost);
				userpost.setPostId(postid);
				userpost.Editpost(postdb);
				
				System.out.println("done everything");

				response.sendRedirect("PostOperations");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
