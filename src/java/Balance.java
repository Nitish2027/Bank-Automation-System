 import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Balance extends HttpServlet
{
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
    {   
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        
    
        final String url="jdbc:mysql://localhost:3306/bank";
        final String root="root";
        final String password="";
        final String driver="com.mysql.jdbc.Driver";
        String ClientName;
        PrintWriter pw=res.getWriter();
        try
        {
            Class.forName(driver);
            con=DriverManager.getConnection(url,root,password);
            stmt=con.createStatement();
            
            
            ps=con.prepareStatement("select * from current order by sno desc limit 1;");
            rs=ps.executeQuery();
            
            if(rs.next())
            {
            ClientName = rs.getString("username");
            

            ps=con.prepareStatement("select * from balance where username=?");
            ps.setString(1,ClientName);
	
            rs=ps.executeQuery();
            
                RequestDispatcher rd=req.getRequestDispatcher("CheckInfo.html");
		rd.include(req, res);
            }

            
            
            if(rs.isBeforeFirst())
            {
                pw.println("<div style=\" position:fixed; top:200px; left:600px; \">");
                pw.println("<h3 align=\"center\">Balance Info</h3>");
                pw.println("<br>");
                pw.println("<table cellspacing=\"5 \" style=\"color:white; border: 5px ridge #009E2D; background-color: #333333;   border-radius: 5px; padding:10px;\">");
                pw.println("<tr><th>Username</th><th>Balance</th></tr>");
            }

            
            if(rs.next())
            {
                 pw.println("<tr><td style=\"border: 3px groove #202020; padding:6px; spac\">" + rs.getString(1) + "</td><td style=\"border: 3px groove #202020; padding:6px;\">" + rs.getString(2) + "</td></tr>");
            }
            
            pw.println("</table>");
            pw.println("</div>");

        }
        
        
        catch(Exception ex)
        {
            pw.println(ex);
        }
    }
}