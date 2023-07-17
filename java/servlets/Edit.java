package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modal.Member;
import service.memberService;
import util.conn;

@WebServlet("/Edit")
public class Edit extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private memberService member;
	private Connection con;
	private Member m;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		System.out.print("Session Started\n");
		 try {
				con=conn.Connect();
			}
		    catch (ClassNotFoundException e) {
				
				e.printStackTrace();
		    }
	       
			try {
					member = new memberService(con);
			        m = member.getuser(id);
			        System.out.println(m.toString());
			        con.close();
			} 
			catch (SQLException e) {
				System.out.println("error");
				e.printStackTrace();     
			}
			if(m!=null)
			{
				session.setAttribute("m", m);
				response.sendRedirect("edit-student.jsp");
			}
		
	}

}
