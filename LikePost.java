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
 * Servlet implementation class LikePost
 */
@WebServlet("/LikePost")
public class LikePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikePost() {
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
		
		String action = request.getParameter("likeaction");
		
		System.out.println(action);
		
		int postid=Integer.parseInt(request.getParameter("postid"));
		System.out.println(postid);
		
		HttpSession session=request.getSession(); 
		User userpost = (User) session.getAttribute("user");
		
		userpost.setPostId(postid);
		
		if(action.equals("like"))
		{
			session.setAttribute("likedpost"+postid,"like");
			System.out.println("like if");
			userpost.insertLike(postdb);
		}
		else
		{
			session.setAttribute("likedpost"+postid,"unlike");
			userpost.deleteLike(postdb);
		}
		
		//response.sendRedirect("Home.jsp");
		response.sendRedirect("Home");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
