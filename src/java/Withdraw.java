import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Withdraw extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        PreparedStatement ps = null;
        
        response.setContentType("text/HTML");
        PrintWriter pw=response.getWriter();
        
        String cmoney =request.getParameter("money1");
        int money= Integer.parseInt(cmoney);
        String ClientName;
        int balance;
        
        try
        {
            // creating mysql database connection.
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/bank";
            con = DriverManager.getConnection(myUrl,"root","");
            
            
            ps=con.prepareStatement("select * from current order by sno desc limit 1;");
            rs1=ps.executeQuery();
            
            if(rs1.next())
            {
            ClientName = rs1.getString("username");
            
            ps=con.prepareStatement("select * from balance where username= '" + ClientName + "'; ");
            rs2=ps.executeQuery();
            
            if(rs2.next())
            {
                balance = rs2.getInt("money");
            
                if(balance>=money)
                {

                    stmt = con.createStatement();
                    String query1 = "update balance set money=money - " + money + " where username='" + ClientName + "'; ";
                    stmt.executeUpdate(query1);
            
            
                    pw.println("<script>alert(\"Successful! \");</script>");
                    RequestDispatcher rd=request.getRequestDispatcher("Client.html");
                    rd.include(request,response);
            }
                else
                {
                    pw.println("<script>alert(\"Not Enough Balance To Withdraw! \");</script>");
                    RequestDispatcher rd=request.getRequestDispatcher("Client.html");
                    rd.include(request,response);
                }
            }
            }
            
        }catch(Exception e)
        {
            pw.println(e);
            pw.println("<script>alert(\"Try Again! \");</script>");
            RequestDispatcher rd=request.getRequestDispatcher("Deposit.html");
            rd.include(request,response);
        }
    }
}