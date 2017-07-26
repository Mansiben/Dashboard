package Mansi;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Salary
 */
@WebServlet("/Salary")
public class Salary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Salary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String connectionUrl = "jdbc:sqlserver://localhost:1433;database=Tim;user=sa;password=Conestoga1;";
		List<String> Names=new ArrayList<String>();
		List<String> Month=new ArrayList<String>();
		List<String> Year=new ArrayList<String>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		Statement stmt2 = null;
		ResultSet rs2 = null;
		
        	try {
        		// Establish the connection.
        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            		con = DriverManager.getConnection(connectionUrl);
            
            		// Create and execute an SQL statement that returns some data.
            		String SQL = "select distinct customerName from Individual_Sales";
            		
            		stmt = con.createStatement();
            		rs = stmt.executeQuery(SQL);
            
            		// Iterate through the data in the result set and display it.
            		while (rs.next()) {
            			
            			Names.add(rs.getString("customerName"));
            			System.out.println(rs.getString("customerName"));
            			
            		}
            		String SQL1 = " select distinct NameofMonth from Cash_Flow";
            		
            		stmt1 = con.createStatement();
            		rs1 = stmt.executeQuery(SQL1);
            
            		// Iterate through the data in the result set and display it.
            		while (rs1.next()) {
            			
            			Month.add(rs1.getString("NameofMonth"));
            			System.out.println(rs1.getString("NameofMonth"));
            			
            		}
            		
            		String SQL2 = " select distinct WhichYear from Expenses";
            		
            		stmt2 = con.createStatement();
            		rs2 = stmt.executeQuery(SQL2);
            
            		// Iterate through the data in the result set and display it.
            		while (rs2.next()) {
            			
            			Year.add(rs2.getString("WhichYear"));
            			System.out.println(rs2.getString("WhichYear"));
            			
            		}
            		Pojo p =new Pojo();
        			p.setNames(Names);
        			Pojo p1 =new Pojo();
        			p1.setNames(Month);
        			Pojo p2 =new Pojo();
        			p2.setNames(Year);
        			request.setAttribute("data",p);
        			request.setAttribute("data1",p1);
        			request.setAttribute("data2",p2);
        			RequestDispatcher rd=getServletContext().getRequestDispatcher("/Pie.jsp");
        			rd.forward(request, response);
        	}
        
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
	    		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	    		if (rs1 != null) try { rs1.close(); } catch(Exception e) {}
	    		if (stmt1 != null) try { stmt1.close(); } catch(Exception e) {}
	    		if (rs2 != null) try { rs2.close(); } catch(Exception e) {}
	    		if (stmt2 != null) try { stmt2.close(); } catch(Exception e) {}
	    		if (con != null) try { con.close(); } catch(Exception e) {}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("Submit")!=null)
		{
			String connectionUrl = "jdbc:sqlserver://localhost:1433;database=Tim;user=sa;password=Conestoga1;";
			List<String> Names=new ArrayList<String>();
			Number[] Days = new Number[7];
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			String SQL = null;
	    	try {
	    		// Establish the connection.
	    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        		con = DriverManager.getConnection(connectionUrl);
	        		String  Cust=request.getParameter("Customers:");
	        		String Month=request.getParameter("Months:");
	        		String year =request.getParameter("Years:");
	        		System.out.println(Cust);
	        		System.out.println(Month);
	        		System.out.println(year);
	        		if(!Cust.equals("CustomerName") && Month.equals("Month") &&  year.equals("Years"))
	        		{
	        			 SQL = " select DayId,salary_of_emplyoees from Expenses where customerName='"+Cust+"'";
	        		}
	        		if(Cust.equals("CustomerName") && !Month.equals("Month") &&  year.equals("Years"))
	        		{
	        			 SQL = " select DayId,salary_of_emplyoees from Expenses where NameofMonth='"+Month+"'";
	        		}
	        		if(Cust.equals("CustomerName") && Month.equals("Month") &&  !year.equals("Years"))
	        		{
	        			 SQL =" select DayId,salary_of_emplyoees from Expenses where WhichYear='"+year+"'";
	        		}
	        		 if(!Cust.equals("CustomerName") && !Month.equals("Month")&&  year.equals("Years"))
	        		 {
	        			 SQL = " select DayId,salary_of_emplyoees from Expenses where customerName='"+Cust+"' and NameofMonth='"+Month+"'";
	        		 }
	        		 if(!Cust.equals("CustomerName") && Month.equals("Month")&&  !year.equals("Years"))
	        		 {
	        			 SQL = "select DayId,salary_of_emplyoees from Expenses where customerName='"+Cust+"' and WhichYear='"+year+"'";
	        		 }
	        		 if(Cust.equals("CustomerName") && !Month.equals("Month")&&  !year.equals("Years"))
	        		 {
	        			 SQL =  "select DayId,salary_of_emplyoees from Expenses where  NameofMonth='"+Month+"' and WhichYear='"+year+"'";
	        		 }
	        		 if(!Cust.equals("CustomerName") && !Month.equals("Month")&&  !year.equals("Years") )
		        	 {
	        			 SQL = " select DayId,salary_of_emplyoees from Expenses where customerName='"+Cust+"' and NameofMonth='"+Month+"' and WhichYear='"+year+"'";
		        	 }
	        		// Create and execute an SQL statement that returns some data.
	        		stmt = con.createStatement();
	        		rs = stmt.executeQuery(SQL);
	        		// Iterate through the data in the result set and display it.
	        		while (rs.next()) {
	        			Days[rs.getInt("DayId")-1]= rs.getInt("salary_of_emplyoees");
	        		}
	        		
	        		for(int i =0 ;i<7;i++)
	        		{
	        			if(Days[i] == null)
	        				Days[i]= 0;
	        			Names.add(Days[i].toString());
	        			System.out.println(Days[i].toString());
	        		}
	    			request.setAttribute("info",Names);
	             	RequestDispatcher rd=getServletContext().getRequestDispatcher("/Pie.jsp");
	    			rd.forward(request, response);
	        
	               /* */
	    	}
	    
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (rs != null) try { rs.close(); } catch(Exception e) {}
	    		if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	    		if (con != null) try { con.close(); } catch(Exception e) {}
		}
	}
	}

}
