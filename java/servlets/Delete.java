package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import modal.Member;
import service.memberService;
import util.conn;

@WebServlet("/Delete")
public class Delete extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private memberService member;
	private Connection con;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.print("Deletion \n");
		
		 try {
				con=conn.Connect();
		} 
		 catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
	       System.out.println(con);
	       boolean success = false;
			try {
					member = new memberService(con);
			        success = member.removeUser(id);
			        con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();     
			}
			if(success)
			{
				System.out.println("Deletion Successful");
				response.sendRedirect("students.jsp");
			}
	}

}
