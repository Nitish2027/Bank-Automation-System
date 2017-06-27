import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class NewClient extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        response.setContentType("text/HTML");
        PrintWriter pw=response.getWriter();
        
        String cfname =request.getParameter("fname");
        String clname =request.getParameter("lname");
        String cusername =request.getParameter("username");
        String cemail =request.getParameter("email");
        String creemail =request.getParameter("reemail");
        String cpassword =request.getParameter("password");
        String cbmonth =request.getParameter("bmonth");
        String cbday =request.getParameter("bday");
        String cbyear =request.getParameter("byear");
        String cgender =request.getParameter("gender");
        
        String cnewpass= cryptWithMD5(cpassword);
        
        try
        {
            // creating mysql database connection.
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/bank";
            con = DriverManager.getConnection(myUrl,"root","");
            
            String query1 = "insert into newclient (firstname, lastname, username, email, reemail, password, bmonth, bday, byear, gender)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            ps = con.prepareStatement(query1);
            ps.setString (1, cfname);
            ps.setString (2, clname);
            ps.setString (3, cusername);
            ps.setString (4, cemail);
            ps.setString (5, creemail);
            ps.setString (6, cnewpass);
            ps.setString (7, cbmonth);
            ps.setString (8, cbday);
            ps.setString (9, cbyear);
            ps.setString (10, cgender);
            
            ps.executeUpdate();
            
            String query2 = "insert into client (username, password)" + " values (?, ?);";
            ps = con.prepareStatement(query2);            
            ps.setString (1, cusername);            
            ps.setString (2, cnewpass);
            
            ps.executeUpdate();
            
            String query3 = "insert into balance (username)" + " values (?);";
            ps = con.prepareStatement(query3);            
            ps.setString (1, cusername);            
            
            ps.executeUpdate();
            
            pw.println("<script>alert(\"Successful! \");</script>");
            RequestDispatcher rd=request.getRequestDispatcher("Login.html");
            rd.include(request,response);
            
            
        }catch(Exception e)
        {
            pw.println(e);
            pw.println("<script>alert(\"Try Again! \");</script>");
            RequestDispatcher rd=request.getRequestDispatcher("Login.html");
            rd.include(request,response);
        }
    }
    
    
        
    private static MessageDigest md;
    
    public static String cryptWithMD5(String pass)    
    {
    try {
        md = MessageDigest.getInstance("MD5");
        byte[] passBytes = pass.getBytes();
        md.reset();
        byte[] digested = md.digest(passBytes);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb.append(Integer.toHexString(0xff & digested[i]));
        }
        return sb.toString();
        
    } catch (NoSuchAlgorithmException ex) {
        Logger.getLogger(NewClient.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;


   }
}