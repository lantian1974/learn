package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestResultSet 
{
	/***
	 * 所有的JDBC都会支持 对  ava.sql.DatabaseMetaData 接口的实现 ，该接口规范了一组获取
	 * 数据库能力描述的方法。通过 java.sql.Connection的 getMetaData()方法可以获取 DatabaseMetaData接口的实现。
	 * @throws SQLException
	 */
	private static  void printDbResultSurpportType() throws SQLException
	{
		Connection con=PostGresTest.getDbConnection();
		DatabaseMetaData dmd=con.getMetaData();
	    System.out.println("ResultSet.TYPE_FORWARD_ONLY="+dmd.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
		System.out.println("ResultSet.TYPE_SCROLL_INSENSITIVE="+dmd.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
	    System.out.println("ResultSet.TYPE_SCROLL_SENSITIVE="+dmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
	    System.out.println("TYPE_FORWARD_ONLY ResultSet ResultSet.CONCUR_UPDATABLE="+dmd.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY,  ResultSet.CONCUR_UPDATABLE));
	    System.out.println("TYPE_SCROLL_INSENSITIVE ResultSet ResultSet.CONCUR_UPDATABLE="+dmd.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,  ResultSet.CONCUR_UPDATABLE));
	    System.out.println("TRANSACTION_NONE"+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_NONE));
	    System.out.println("TRANSACTION_READ_UNCOMMITTED"+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED));
	    System.out.println("TRANSACTION_READ_COMMITTED"+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED));
	    System.out.println("TRANSACTION_REPEATABLE_READ"+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
	    System.out.println("TRANSACTION_SERIALIZABLE"+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE));
	}

	public static void cursorHoldabilitySupport()
		    throws SQLException {
		    Connection conn=PostGresTest.getDbConnection();
		    DatabaseMetaData dbMetaData = conn.getMetaData();
		    System.out.println("ResultSet.HOLD_CURSORS_OVER_COMMIT = " +
		        ResultSet.HOLD_CURSORS_OVER_COMMIT);

		    System.out.println("ResultSet.CLOSE_CURSORS_AT_COMMIT = " +
		        ResultSet.CLOSE_CURSORS_AT_COMMIT);

		    System.out.println("Default cursor holdability: " +
		        dbMetaData.getResultSetHoldability());

		    System.out.println("Supports HOLD_CURSORS_OVER_COMMIT? " +
		        dbMetaData.supportsResultSetHoldability(
		            ResultSet.HOLD_CURSORS_OVER_COMMIT));

		    System.out.println("Supports CLOSE_CURSORS_AT_COMMIT? " +
		        dbMetaData.supportsResultSetHoldability(
		            ResultSet.CLOSE_CURSORS_AT_COMMIT));
		}
	 public static  void modifyPrices(float percentage) throws SQLException {

	        try(Connection con =PostGresTest.getDbConnection();
	        	Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
	                    ResultSet.CONCUR_UPDATABLE);)
	        {
	           
	            ResultSet uprs = stmt.executeQuery("SELECT * FROM  COFFEES");

	            while (uprs.next()) 
	            {
	                float f = uprs.getFloat("PRICE");
	                uprs.updateFloat( "PRICE", f * percentage);
	                String cofName=uprs.getString("COF_NAME");
	                uprs.updateString("COF_NAME", "COFFEE_"+cofName);
	                uprs.updateRow();
	            }
	

	        }
	    }
	private static void batchUpdateWithStatement() throws SQLException 
	{
		Connection con=PostGresTest.getDbConnection();
		try(Statement stmt = con.createStatement())
		{
			   con.setAutoCommit(false);
			   stmt.addBatch(
			            "INSERT INTO COFFEES " +
			            "VALUES('Amaretto12', 49, 9.99, 0, 0)");

			        stmt.addBatch(
			            "INSERT INTO COFFEES " +
			            "VALUES('Hazelnut1', 49, 9.99, 0, 0)");

			        stmt.addBatch(
			            "INSERT INTO COFFEES " +
			            "VALUES('Amaretto_decaf1', 49, " +
			            "10.99, 0, 0)");

			        stmt.addBatch(
			            "INSERT INTO COFFEES " +
			            "VALUES('Hazelnut_decaf1', 49, " +
			            "10.99, 0, 0)");
			         int[]  updateCounts =  stmt.executeBatch();
			         con.commit();	
			         System.out.println(updateCounts.toString());
		} catch (SQLException e) 
		{
			e.printStackTrace();
			con.rollback();
		}
		finally
		{
			if (con!=null)
		    {
				 con.setAutoCommit(false);
				 con.close();
		    }
		}
	}
	private static void batchUpdateWithPreparedStatement() throws SQLException 
	{
		  Connection con=PostGresTest.getDbConnection();
		  con.setAutoCommit(false);
		  try(PreparedStatement pstmt=con.prepareStatement("insert into COFFEES values(?,?,?,?,?)"))
		  {
			  pstmt.setString(1, "Amaretto3");
			  pstmt.setInt(2, 49);
			  pstmt.setFloat(3, 9.55f);
			  pstmt.setInt(4, 2);
			  pstmt.setInt(5, 3);
			  pstmt.addBatch();
			  pstmt.setString(1, "Amaretto4");
			  pstmt.setInt(2, 49);
			  pstmt.setFloat(3, 9.55f);
			  pstmt.setInt(4, 2);
			  pstmt.setInt(5, 3);
			  pstmt.addBatch();
			  int[] updateCounts=pstmt.executeBatch();
			  con.commit();
			  
		  } catch (SQLException e) {
			  con.rollback();
			  e.printStackTrace();
		}
		  finally
		  {
			  if (con!=null)
			  {
				  con.setAutoCommit(true);
				  con.close();
			  }
		  }
	}
	private static void insertRowWithResultSet() throws SQLException 
	{
		
		Connection con=PostGresTest.getDbConnection();
		con.setAutoCommit(false);
		try(
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rst=stmt.executeQuery("select * from COFFEES"))
	    {
			rst.moveToInsertRow();
			rst.updateString("cof_name","laobabu4");
			rst.updateInt("sup_id",49);
			rst.updateFloat("price", 8.12f);
			rst.updateInt("sales",12 );
			rst.updateInt("total",100);
			rst.insertRow();
			rst.moveToInsertRow();
			rst.updateString("cof_name","laobabu5");
			rst.updateInt("sup_id",49);
			rst.updateFloat("price", 8.12f);
			rst.updateInt("sales",12 );
			rst.updateInt("total",100);
			rst.insertRow();
			rst.beforeFirst();
			con.commit();
			while(rst.next())
			{
			  System.out.println("cof_name:"+ rst.getString("cof_name")+
					             " sup_id:"+rst.getInt("sup_id")+
					             " price:"+rst.getFloat("price")+
					             " sales:"+rst.getInt("sales")+
					             " total:"+rst.getInt("total"));	
			}
		}
		finally
		{
			if (con!=null)
			{
				con.close();
			}
		}
		
	}
	/**
	 * PostGresSQL JDBC4.1 不支持RowID，rowid也不是SQL标准。因此，要想程序跨数据库，应杜绝使用该ＡＰＩ。
	 * @throws SQLException
	 */
	static void testJdbcRowId() throws SQLException
	{
		 try(    Connection con =PostGresTest.getDbConnection();
		         Statement stmt = con.createStatement();
				 ResultSet rst = stmt.executeQuery("SELECT * FROM  COFFEES");
		    )
		 {
			 while (rst.next())
			 {
				 System.out.println("rowId:"+rst.getRowId(1)+
						 " cof_name:"+ rst.getString("cof_name")+
			             " sup_id:"+rst.getInt("sup_id")+
			             " price:"+rst.getFloat("price")+
			             " sales:"+rst.getInt("sales")+
			             " total:"+rst.getInt("total"));	
			 }
		 }
		           
		           
	}
	public static void main(String[] args) throws SQLException
	{
		// printDbResultSurpportType();
	   //	 cursorHoldabilitySupport();
	//	modifyPrices(0.9f);
	//	batchUpdateWithStatement();
	//	batchUpdateWithPreparedStatement() ;
	//	insertRowWithResultSet();
		testJdbcRowId();
	}
	
}
