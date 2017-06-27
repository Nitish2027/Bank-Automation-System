import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Employee extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {
        response.setContentType("text/HTML");
        PrintWriter pw=response.getWriter();
        
        String EmpName =request.getParameter("empuserid");
        String EmpPass =request.getParameter("emppassword");
        
        try
        {
            if (validate(EmpName, EmpPass))
            {
               	RequestDispatcher rd=request.getRequestDispatcher("Employee.html");
		rd.include(request,response); 
            }
            else
            {
                pw.println("<script>alert(\"Username/Password Not Found\");</script>");
              	RequestDispatcher rd=request.getRequestDispatcher("Login.html");
		rd.include(request,response);
            }
             
        }catch(Exception e)
        {
            pw.println(e);
        }
    }


public static boolean validate(String name,String pass){
        boolean status=false;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
try{
    
            // creating mysql database connection.
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/bank";
            con = DriverManager.getConnection(myUrl,"root","");
            

	ps=con.prepareStatement("select * from employee where username=? and password=?");
	ps.setString(1,name);
	ps.setString(2,pass);
	
	rs=ps.executeQuery();
	status=rs.next();
	
	
}catch(Exception e){System.out.println(e);}
return status;
}

}