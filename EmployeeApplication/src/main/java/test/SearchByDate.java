package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/searchbydate")
public class SearchByDate extends HttpServlet {
	Connection con=null;

	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8","root","sql123");
		} catch (ClassNotFoundException e) {
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fromdate=req.getParameter("fromdate");
		String todate=req.getParameter("todate");


		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		String query="select * from employee where joining='"+ fromdate+"'and '"+todate+"'";
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th>ID</th>");
			pw.print("<th>EMPNAME</th>");
			pw.print("<th>EMPSALARY</th>");
			pw.print("<th>EMPDOB</th>");
			pw.print("<th>EMPJOINING</th>");
			pw.print("<tr>");
			
			while(rs.next()) {
				pw.print("<tr>");
				pw.print("<td>"+rs.getInt(1)+"</td>");
				pw.print("<td>"+rs.getString(2)+"</td>");
				pw.print("<td>"+rs.getDouble(3)+"</td>");
				pw.print("<td>"+rs.getString(4)+"</td>");
				pw.print("<td>"+rs.getString(5)+"</td>");
				pw.print("<tr>");	
			}
			pw.print("<table/>");		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

}
}


