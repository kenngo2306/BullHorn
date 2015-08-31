package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bulluser;
import model.Post;
import db.DBBulluser;

/**
 * Servlet implementation class ServletProfile
 */
@WebServlet("/Profile")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userIdStr = request.getParameter("userId");
		int userId = 0;
		
		HttpSession session = request.getSession();
		if(userIdStr == null)
		{
			if(session.getAttribute("loginId") == null)
			{
				getServletContext().getRequestDispatcher("/LoginForm.jsp").forward(request, response);
			}
			else
			{
				userId = Integer.parseInt(session.getAttribute("loginId").toString());
			}
		}
		else
		{
			userId = Integer.parseInt(userIdStr);
		}

		System.out.println("userId = " + userIdStr);
		
		 
		
		Bulluser user = DBBulluser.getUser(userId);
		
		String userData = "<div class=' title col-sm-8 col-sm-offset-2' >";
		userData += "<h2>" + user.getFullName() + "</h2>";
		userData += "<h3> Email: " + user.getEmail() + "</h3>";
		userData += "<h3> Motto: " + user.getMotto() + "</h3>";
		userData += "<h3> Join Date: " + user.getJoinDate() + "</h3>";
		
		userData += "</div>";
		request.setAttribute("userData", userData);
		request.setAttribute("userId", userId);
		List<Post> posts = user.getPosts();
		
		String postData2 = "<table class='table table-bordered table-striped'>";

		
		for(Post post : posts)
		{
			postData2 += "<div class = ' well well-large col-sm-8 col-sm-offset-2'>";
			postData2 += "<h4>";
			postData2 += "<a href='/BullhornAssignment/Profile?userId="+ post.getBulluser().getUserId() +"'>" +post.getBulluser().getFullName() + "</a>";
			postData2 += "</h4>";
			postData2 += post.getPostDate();;
			postData2 += "<div class='alert'>";
			postData2 += post.getPostContent();
			postData2 += "</div>";
			postData2 += "</div>";
		}
		request.setAttribute("postData", postData2);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
