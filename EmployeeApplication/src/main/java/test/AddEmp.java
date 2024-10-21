package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addemployee")
public class AddEmp extends HttpServlet {
Connection con=null;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8","root","sql123");
		} catch (ClassNotFoundException e) {
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("empname");
		double salary=Double.parseDouble(req.getParameter("empsalary"));
		String dob=req.getParameter("empdob");
		String joining=req.getParameter("empjoining");
		
		
		
//		int joinyear =Integer.parseInt(joining.substring(0, 4));
//		int birthyear =Integer.parseInt(dob.substring(0, 4));
//		if((joinyear-birthyear)<18) {
//			pw.print("<h1 your age is less than 18 year</h1>");
//		}
		
		
		
		PreparedStatement pstmt=null;
		String query="insert into employee values(?,?,?,?,?);";
		
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setInt(1,0);
			pstmt.setString(2,name);
			pstmt.setDouble(3,salary);
			pstmt.setString(4,dob);
			pstmt.setString(5,joining);
			int count=pstmt.executeUpdate();
			
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+ "RECORD INSERTED</h1>");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		

}
}
