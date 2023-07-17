package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.memberService;
import util.conn;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con=null;
	private memberService serve;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String firstname = request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String dept=request.getParameter("dept");
		try {
			con=conn.Connect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		serve=new memberService(con);
		boolean success=false;
		try {
			System.out.println();
			success = serve.updateUser(id,firstname, lastname, dob, gender, email, phone, dept);
			con.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();     
		}
		if(success)
		{
			response.sendRedirect("students.jsp");
			return;
		}
	}
}


