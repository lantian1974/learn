package jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostGresTest
{
	//PostGresSQL数据库 JDBC  URL设置规则 如下 ：
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
              long id = rs.getLong("id");
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
    public void testWithNewJava()
    {
    	
     /**
      * 在Java SE 7之后 ，可以用 try-with-resources 语法 来自动关闭 资源，
      *    所谓资源就是实现了 java.lang.AutoCloseable接口或其派生接口 java.io.Closeable接口的类 。
      *    在JDBC4.1 以后，java.sql.Connection,java.sql.ResultSet,java.sql.Statement等资源
      *    类都实现了  java.lang.AutoCloseable接口 ，所以可以使用 try-with-resources 语法  。
      */
      try(Connection con = DriverManager.getConnection(url,usr,pwd);
    			         Statement stmt = con.createStatement();)
 	   {
           
           
    		ResultSet rs  = stmt.executeQuery("SELECT id,code,name,address FROM test");
           while (rs.next())
           {
               long id = rs.getLong("id");
               String code = rs.getString("code");
               String name = rs.getString("name");
               String address=rs.getString("address");
               System.out.println("id="+id+",code="+code+",name="+name+",address="+address);
           }
 	   }
       catch(SQLException e)
       {
    	   System.out.println(e);   
       }
    }
    public static void main(String[] args) throws SQLException
    {
    	PostGresTest pgt=new PostGresTest();
    	//pgt.test();
    	pgt.testWithNewJava();
    }
}
