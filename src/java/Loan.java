import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Loan extends HttpServlet
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
        double balance;
        
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
                balance = rs2.getDouble("money");
                double loan1 = 0.1 * money;
                if((balance) > (loan1))
                {
                    String query3 = "insert into loan (username, amount)" + " values (?, ?);";
                    ps = con.prepareStatement(query3);            
                    ps.setString (1, ClientName);
                    ps.setInt (2, money);
            
                    ps.executeUpdate();
                    
                    pw.println("<script>alert(\"Your loan request has been sent for approval. \");</script>");
                    RequestDispatcher rd=request.getRequestDispatcher("Client.html");
                    rd.include(request,response);
                }
                else
                {
                    pw.println("<script>alert(\"Sorry! You must have atleast 10% of the amount you are requesting in your account. \");</script>");
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