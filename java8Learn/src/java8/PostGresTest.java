package java8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostGresTest
{
	static String url = "jdbc:postgresql://127.0.0.1:5432/right";
    static String usr = "admin";
    static String pwd = "111111"; 
    private void test() throws SQLException
    {
    	Connection con=null;
    	Statement stmt=null;
    	ResultSet rs =null;
      try
	   {
          con = DriverManager.getConnection(url,usr,pwd);
          stmt = con.createStatement();
          rs = stmt.executeQuery("SELECT id,code,name,address FROM test");
          while (rs.next())
          {
              float id = rs.getFloat("id");
              String code = rs.getString("code");
              String name = rs.getString("name");
              String address=rs.getString("address");
              System.out.println("id="+id+",code="+code+",name="+name+",address="+address);
          }
	   }
       finally
       {
    	   if (rs!=null) rs.close();
    	   if (stmt!=null) stmt.close();
    	   if (con!=null) con.close();
       }
   }
    public static void main(String[] args) throws SQLException
    {
    	PostGresTest pgt=new PostGresTest();
    	pgt.test();
    }
}
