package com.hp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMsgByAjax extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetMsgByAjax() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String jdbcdriver = "oracle.jdbc.driver.OracleDriver";
		Connection con=null;
		Object count=0;
		try {
			Class.forName(jdbcdriver);
            con = DriverManager.getConnection(url, "test", "test");
            String sql = "select count(*) from employee";
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			
			if(rs.next()){
				count=rs.getObject(1);
			}
			rs.close();
            pst.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
        }
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(count);
		System.out.println("count"+count);
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
