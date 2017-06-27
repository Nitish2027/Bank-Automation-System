import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
    {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;   
        Statement stmt = null;
        
        response.setContentType("text/HTML");
        PrintWriter pw=response.getWriter();
        
        String ClientName =request.getParameter("clientuserid");
        String ClientPass =request.getParameter("clientpassword");
        
        String NewClientPass = cryptWithMD5(ClientPass); 
        
        try
        {
            if (validate(ClientName, NewClientPass))
            {
                
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            String myUrl = "jdbc:mysql://localhost:3306/bank";
            con = DriverManager.getConnection(myUrl,"root","");
           
            stmt  = con.createStatement();
            stmt.execute("truncate current; ");
    
            String query = "insert into current (username)" + " values (?);";
            ps = con.prepareStatement(query);            
            ps.setString (1, ClientName);            
            
            ps.executeUpdate();
            
            ps=con.prepareStatement("select max(sno) from current;");
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                int z = rs.getInt(1);
                z=z+1;
            pw.println(z);
            stmt = con.createStatement();
            String query1 = "update current set sno=" + z + " and username='" + ClientName + "'; ";
            stmt.executeUpdate(query1);
                
               	RequestDispatcher rd=request.getRequestDispatcher("Client.html");
		rd.include(request,response);
            }
            
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
            con = DriverManager.getConnection(myUrl,"root","Nitish2027");
            

	ps=con.prepareStatement("select * from client where username=? and password=?");
	ps.setString(1,name);
	ps.setString(2,pass);
	
	rs=ps.executeQuery();
	status=rs.next();
	
	
}catch(Exception e){System.out.println(e);}
return status;
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
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;


   }

}