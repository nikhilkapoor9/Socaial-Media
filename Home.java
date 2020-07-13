package com.project.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.project.db.PostDBUtil;
import com.project.db.UserDBUtil;
import com.project.models.Post;
import com.project.models.User;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
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
		postdb  = new PostDBUtil(datasource);
		
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		
		System.out.println(user.getEmail()); //4
		
		user.DisplayAllPosts(postdb);
		
		session.setAttribute("user", user);	
		
		ArrayList<Integer> likedpostids = user.getLikedPostInSession();
		
		for(Integer id:likedpostids)
		{
			session.setAttribute("likedpost"+id, "like");
		}
		
		response.sendRedirect("Home.jsp");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
