import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Deposit extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        response.setContentType("text/HTML");
        PrintWriter pw=response.getWriter();
        
        String cmoney =request.getParameter("money");
        int money= Integer.parseInt(cmoney);
        String ClientName;
        
        try
        {
            // creating mysql database connection.
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/bank";
            con = DriverManager.getConnection(myUrl,"root","");
            
            
            ps=con.prepareStatement("select * from current order by sno desc limit 1;");
            rs=ps.executeQuery();
            
            if(rs.next())
            {
            ClientName = rs.getString("username");
            

            stmt = con.createStatement();
            String query1 = "update balance set money=money + " + money + " where username='" + ClientName + "'; ";
            stmt.executeUpdate(query1);
            }
            
            pw.println("<script>alert(\"Successful! \");</script>");
            RequestDispatcher rd=request.getRequestDispatcher("Client.html");
            rd.include(request,response);
            
            
        }catch(Exception e)
        {
            pw.println(e);
            pw.println("<script>alert(\"Try Again! \");</script>");
            RequestDispatcher rd=request.getRequestDispatcher("Deposit.html");
            rd.include(request,response);
        }
    }
}