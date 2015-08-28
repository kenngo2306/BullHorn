package main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import db.DBPost;

/**
 * Servlet implementation class ServletSearch
 */
@WebServlet("/Search")
public class ServletSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doget search");
		String query = request.getParameter("search");
		System.out.println("query = " + query);
		
		List<Post> posts = DBPost.searchPosts(query);
		
		String postData2 = "<table class='table table-bordered table-striped'>";
		
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
		//forward it
		getServletContext().getRequestDispatcher("/AllPosts.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
