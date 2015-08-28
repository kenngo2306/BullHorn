package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bulluser;
import model.Post;
import db.DBBulluser;
import db.DBPost;

/**
 * Servlet implementation class ServletSearchInProfile
 */
@WebServlet("/SearchInProfile")
public class ServletSearchInProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSearchInProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = request.getParameter("query");
		String userIdStr = request.getParameter("userId");
		System.out.println("userIdStr = " + userIdStr);
		long userId  = Integer.parseInt(userIdStr);
		
		
		Bulluser user = DBBulluser.getUser(userId);
		
		String userData = "<div class='alert alert-info'>";
		userData += "<p> Name: " + user.getFullName() + "</p>";
		userData += "<p> Email: " + user.getEmail() + "</p>";
		userData += "<p> Motto: " + user.getMotto() + "</p>";
		userData += "<p> Join Date: " + user.getJoinDate() + "</p>";
		
		userData += "</div>";
		request.setAttribute("userData", userData);
		
		List<Post> posts = DBPost.searchPostsInProfile(userId, query);
		System.out.println("posts size = " + posts.size());
		String postData2 = "";

		
		for(Post post : posts)
		{
			postData2 += "<div class = ' well well-large col-sm-8 col-sm-offset-2'>";
			postData2 += "<h4>";
			postData2 += "<a href='/BullhornAssignment/Profile?userId="+ post.getBulluser().getUserId() +"'>" +post.getBulluser().getFullName() + "</a>";
			postData2 += "</h4>";
			postData2 += post.getPostDate();;
			postData2 += "<div class='alert alert-info'>";
			postData2 += post.getPostContent();
			postData2 += "</div>";
			postData2 += "</div>";
		}
		
		request.setAttribute("postData", postData2);
		request.setAttribute("userId", userId);
		//forward it
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
