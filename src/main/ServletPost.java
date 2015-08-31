package main;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBPost;
import model.Bulluser;
import model.Post;

/**
 * Servlet implementation class ServletPost
 */
@WebServlet("/Post")
public class ServletPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String post_content = request.getParameter("post_content");
		
		System.out.println(post_content);
		
		Date date = new Date();
		
		HttpSession session = request.getSession();
		try
		{
			int user_id = Integer.parseInt(session.getAttribute("loginId").toString());
			Post post = new Post();
			Bulluser user = new Bulluser();
			user.setUserId(user_id);
			
			post.setBulluser(user);
			post.setPostContent(post_content);
			post.setPostDate(date);
			DBPost.insert(post);
			getServletContext().getRequestDispatcher("/AllPosts").forward(request, response);
		}
		
		catch(Exception e)
		{
			getServletContext().getRequestDispatcher("/LoginForm.jsp").forward(request, response);
		}
		
		

	}

}
