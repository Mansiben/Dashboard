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
 * Servlet implementation class TimView
 */
@WebServlet("/TimView")
public class TimView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimView() {
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
		List<String> Types=new ArrayList<String>();
		List<String> Size=new ArrayList<String>();
		Connection con = null;
		Statement stmt = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
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
            		
            		String SQL1 = "select distinct coffeeTypes from Individual_Sales";
            		
            		stmt1 = con.createStatement();
            		rs1 = stmt.executeQuery(SQL1);
            
            		// Iterate through the data in the result set and display it.
            		while (rs1.next()) {
            			
            			Types.add(rs1.getString("coffeeTypes"));
            			System.out.println(rs1.getString("coffeeTypes"));
            			
            		}
            		String SQL2 = "  select distinct size from Individual_Sales";
            		
            		stmt2 = con.createStatement();
            		rs2 = stmt.executeQuery(SQL2);
            
            		// Iterate through the data in the result set and display it.
            		while (rs2.next()) {
            			
            			Size.add(rs2.getString("size"));
            			System.out.println(rs2.getString("size"));
            			
            		}
        			Pojo p =new Pojo();
        			p.setNames(Names);
        			Pojo p1 =new Pojo();
        			p1.setNames(Types);
        			Pojo p2 =new Pojo();
        			p2.setNames(Size);
        			request.setAttribute("data",p);
        			request.setAttribute("data1",p1);
        			request.setAttribute("data2",p2);
        			RequestDispatcher rd=getServletContext().getRequestDispatcher("/MyTim.jsp");
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
			//Statement stmt1 = null;
			//ResultSet rs1 = null;
			String SQL = null;

	    	try {
	    		// Establish the connection.
	    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        		con = DriverManager.getConnection(connectionUrl);
	        		String  Cust=request.getParameter("Customers:");
	        		String Types=request.getParameter("Coffee types:");
	        		String Size=request.getParameter("Coffee size:");
	        		System.out.println(Cust);
	        		System.out.println(Types);
	        		System.out.println(Size);
	        		if(!Cust.equals("CustomerName") && Types.equals("Coffee Types") &&  Size.equals("Coffee size"))
	        		{
	        			 SQL = "select cups_Coffee,DayId,coffeeTypes from Individual_Sales where customerName='"+Cust+"'";
	        		}
	        		if(Cust.equals("CustomerName") && !Types.equals("Coffee Types") &&  Size.equals("Coffee size"))
	        		{
	        			 SQL = "select cups_Coffee,DayId,coffeeTypes from Individual_Sales where coffeeTypes='"+Types+"'";
	        		}
	        		if(Cust.equals("CustomerName") && Types.equals("Coffee Types") &&  !Size.equals("Coffee size"))
	        		{
	        			 SQL = "select cups_Coffee,DayId,coffeeTypes from Individual_Sales where  size='"+Size+"'";
	        		}
	        		if(!Cust.equals("CustomerName") && !Types.equals("Coffee Types")&&  Size.equals("Coffee size"))
	        		{
	        			 SQL = "select cups_Coffee,DayId,coffeeTypes from Individual_Sales where customerName='"+Cust+"' and coffeeTypes='"+Types+"'";
	        		}
	        		if(!Cust.equals("CustomerName") && Types.equals("Coffee Types")&&  !Size.equals("Coffee size"))
	        		{
	        			 SQL = "select cups_Coffee,DayId,coffeeTypes from Individual_Sales where customerName='"+Cust+"' and size='"+Size+"'";
	        		}
	        		if(Cust.equals("CustomerName") && !Types.equals("Coffee Types")&&  !Size.equals("Coffee size"))
	        		{
	        			 SQL = "select cups_Coffee,DayId,coffeeTypes from Individual_Sales where coffeeTypes='"+Types+"'  and size='"+Size+"'";
	        		}
	        		if(!Cust.equals("CustomerName") && !Types.equals("Coffee Types")&&  !Size.equals("Coffee size") )
	        		{
	        			 SQL = "select cups_Coffee,DayId,coffeeTypes from Individual_Sales where customerName='"+Cust+"' and coffeeTypes='"+Types+"'  and size='"+Size+"'";
	        		}
	        		
	        		
	        		stmt = con.createStatement();
	        		rs = stmt.executeQuery(SQL);
	        		// Iterate through the data in the result set and display it.
	        		while (rs.next()) {
	        			Days[rs.getInt("DayId")-1] = rs.getInt("cups_Coffee");
	        		}
	        		
	        		for(int i =0 ;i<7;i++)
	        		{
	        			if(Days[i] == null)
	        				Days[i]= 0;
	        			Names.add(Days[i].toString());
	        			System.out.println(Days[i].toString());
	        		}
	    			request.setAttribute("info",Names);
	    			// String SQL2 = "select distinct customerName from Individual_Sales";
	    			//stmt1 = con.createStatement();
	            	//rs1 = stmt1.executeQuery(SQL2);
	            	//while (rs1.next()) {
            			//Names.add(rs1.getString("customerName"));
            		//}
            		
	            	//request.setAttribute("Customers:",Names);
	    			RequestDispatcher rd=getServletContext().getRequestDispatcher("/MyTim.jsp");
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
	    		//if (rs1 != null) try { rs1.close(); } catch(Exception e) {}
	    		//if (stmt1 != null) try { stmt1.close(); } catch(Exception e) {}
	    	if (con != null) try { con.close(); } catch(Exception e) {}
		}
		}
		

	}

}
