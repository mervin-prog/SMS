package service;

import modal.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.RegisterDao;

public class memberService 
{
	private RegisterDao registerdao;
     public memberService(Connection con) {
        registerdao=new RegisterDao(con);
    }
    public boolean register(String firstname,String lastname,String dob,String gender,String email,String phone,String dept) 
    {
    	Member member = new Member();
    	member.setFirstname(firstname);
    	member.setLastname(lastname);
    	member.setDob(dob);
    	member.setGender(gender);
    	member.setEmail(email);
    	member.setPhone(phone);
    	member.setDept(dept);
    	return registerdao.add(member);
    }
    public List<Member> getUsers() throws SQLException {
		return registerdao.getUser();
    }
    public Member getuser(String id) throws NumberFormatException, SQLException {
		System.out.println(id);
		return registerdao.editUser(Integer.parseInt(id));
	}
    public boolean updateUser(String id,String firstname,String lastname,String dob,String gender,String email,String phone,String dept) throws SQLException {
		Member m = new Member();
		m.setId(Integer.parseInt(id));
        m.setFirstname(firstname);
        m.setLastname(lastname);
        m.setDob(dob);
        m.setGender(gender);
        m.setEmail(email);
        m.setPhone(phone);
        m.setDept(dept);
        
        System.out.println(m.toString());
		return registerdao.updateUser(m);
	}
    public boolean removeUser(String id) 
    {
		return registerdao.removeUser(Integer.parseInt(id));
	}
}
