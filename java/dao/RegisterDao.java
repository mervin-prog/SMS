package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import modal.Member;
import util.conn;


public class RegisterDao 
{
	private Connection con;
	public RegisterDao(Connection con) {
		this.con=con;
	}
	public boolean add(Member member) {
		PreparedStatement ps=null;
		boolean res=false;
		try {
			String sql="Insert into students(firstname,lastname,dob,gender,email,phone,department) values(?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, member.getFirstname());
			ps.setString(2, member.getLastname());
			ps.setString(3, member.getDob());
			ps.setString(4, member.getGender());
			ps.setString(5, member.getEmail());
			ps.setString(6, member.getPhone());
			ps.setString(7, member.getDept());
			res=ps.execute();
			int c=ps.getUpdateCount();
			if(c==0) {
				System.out.println("Insertion Failed");
			}
			else {
				System.out.println("Insertion Successful");
				res=true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public List<Member> getUser() throws SQLException {
		 PreparedStatement ps = null;
	     List<Member> users = null;
	     try {
	    	String sql = "select * from students";
	    	ps = con.prepareStatement(sql);
	    	ResultSet rs =  ps.executeQuery();
	    	users = new ArrayList<>();
	    	while(rs.next())
	    	{
	    		Member member = new Member();
	    		member.setId(rs.getInt(1));
	    		member.setFirstname(rs.getString(2));
	    		member.setLastname(rs.getString(3));
	    		member.setDob(rs.getString(4));
	    		member.setGender(rs.getString(5));
	    		member.setEmail(rs.getString(6));
	    		member.setPhone(rs.getString(7));
	    		member.setDept(rs.getString(8));
	    		users.add(member);
	    	}
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	     return users;
		
	}
	public Member editUser(int id) throws SQLException {
		PreparedStatement ps = null;
		Member m = new Member();
	     try {
	    	String sql = "SELECT * FROM students WHERE id=?";
	    	ps = con.prepareStatement(sql);
	    	ps.setInt(1, id);
	    	ResultSet rs =  ps.executeQuery();
	    	while(rs.next())
	    	{
	    		m.setId(rs.getInt(1));
	    		m.setFirstname(rs.getString(2));
	    		m.setLastname(rs.getString(3));
	    		m.setDob(rs.getString(4));
	    		m.setGender(rs.getString(5));
	    		m.setEmail(rs.getString(6));
	    		m.setPhone(rs.getString(7));
	    		m.setDept(rs.getString(8));
	    		
	    	}
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	     return m;
	}
	public boolean updateUser(Member m) throws SQLException {
		System.out.println("up");
		    PreparedStatement ps = null;
	        boolean result=false;
	        try {
	            String sql = "UPDATE students SET firstname=?,lastname=?,dob=?,gender=?,email=?,phone=?,department=? WHERE id=?";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, m.getFirstname());
	            ps.setString(2, m.getLastname());
	            ps.setString(3, m.getDob());
	            ps.setString(4, m.getGender());
	            ps.setString(5, m.getEmail());
	            ps.setString(6, m.getPhone());
	            ps.setString(7, m.getDept());
	            ps.setInt(8,m.getId());
	            
	            int c  = ps.executeUpdate();
	            if (c == 0) {
	                System.out.println("Updation Failed");
	            } 
	            else {
	                System.out.println("Updation successful");
	                result=true;
	            }  
	        }
	        catch(Exception e) {
	        	e.printStackTrace();
	        }
	        return result;
	}
	public boolean removeUser(int id) 
	{
		PreparedStatement ps = null;
		boolean result = false;
	     try 
	     {
	    	String sql = "DELETE FROM students WHERE id=?";
	    	ps = con.prepareStatement(sql);
	    	ps.setInt(1, id);
	    	int updatecount =  ps.executeUpdate();
	    	if (updatecount == 0)
	    	{
                System.out.println("Deletion failed");
            } 
	    	else 
	    	{
	    		System.out.println("Deletion Successful");
                result=true;
            }  
	     }
	     catch(Exception e) {
	    	 e.printStackTrace();
	     }
		return result;
	}
}
