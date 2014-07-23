package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class TestTransaction
{
	/**
	 * 数据库的事务隔级别共分为以下几级：
	 * Connection.TRANSACTION_NONE 表示此数据库连接不支持事务。
	 * Connection.TRANSACTION_READ_UNCOMMITTED 表示数据库支持事务，但是允许脏读，允许非重复读，允许幻读。
	 * Connection.RANSACTION_READ_COMMITTED 表示数据库 支持事务，但是不允许脏读，允许不可复读，允许幻读。
	 * Connection.TRANSACTION_REPEATABLE_READ  表示数据库 支持事务，不允许允许脏读，不允许不可复读，允许幻读。
	 * Connection.TRANSACTION_SERIALIZABLE 表示数据库 支持事务，不允许允许脏读，不允许不可复读，不允许幻读。
	 * 脏读（Dirty Read）：表示以A事务的读操作可以读到尚未提交的B事务写操作所操作的数据行，这样，当B事务一旦回滚，A事务读到的数据可能不正确。
	 *                   这意味着，B事务在写数据行时，没有对该数据行加上排斥其他事务读取锁 。不允许脏读，意味着写数据的事务不能合读事务在相同数据行上并发。
	 * 不可复读 （Non-Repeatable Read）：表示A事务读取某数据行后，B事务对该数据行进行了更新，之后 A事务又读取该数据行，A事务两次对该行数据的读取是一样的。这就是说，不能重复地读到相同的数据。
	 * 者意味者，当一个事务在读数某行数据时，没有对该数据行加上排斥其他事务写数据的锁。 TRANSACTION_REPEATABLE_READ则意味着，不允许这种情况发生，那么当一个事务读取数据的时候，不允许其他事务对它所读取的数据行进行写操作。
	 * 幻读（phantom read）： 当事务 A按照某些条件读取了一些数据行之后，B事务插入一些新的数据行或者修改了一些其他A事务没有读到的数据行，使得这些新增或者被修改的数据行符合了A事务的条件，当A事务再次按照相同条件读取数据的时候，
	 *                     就多出来一些数据行，这些多出来的数据行被称为幻读数据，当然，也可能B事务删除符合A事务读取条件的数据行，或者修改符合A事务读取条件的数据行使之不符合条件。要想防止幻读，数据库就必须执行最为严格的
	 *                     事务隔离机制，让所有的事务都串行执行，但这会降低数据库操作的性能。               
	 * @throws SQLException
	 */
	private static  void printDbTransationLevel() throws SQLException
	{
		Connection con=PostGresTest.getDbConnection();
		DatabaseMetaData dmd=con.getMetaData();
	    System.out.println("TRANSACTION_NONE="+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_NONE));
	    System.out.println("TRANSACTION_READ_UNCOMMITTED="+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED));
	    System.out.println("TRANSACTION_READ_COMMITTED="+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_READ_COMMITTED));
	    System.out.println("TRANSACTION_REPEATABLE_READ="+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
	    System.out.println("TRANSACTION_SERIALIZABLE="+dmd.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE));
	    int transationIsolateLeve=con.getTransactionIsolation();
	    String info="当前数据库连接的 事务隔离级别是： ";
	    switch (transationIsolateLeve)
	    {
	   	     case Connection.TRANSACTION_NONE:
			    info+="TRANSACTION_NONE";
			 break;
	   	     case Connection.TRANSACTION_READ_UNCOMMITTED:
	   	        info+="TRANSACTION_READ_UNCOMMITTED";
	   	     break;
	   	     case Connection.TRANSACTION_READ_COMMITTED:
	   	        info+="TRANSACTION_READ_COMMITTED";
	   	     break;
	   	     case Connection.TRANSACTION_REPEATABLE_READ:
	   	        info+="TRANSACTION_READ_COMMITTED";
	   	     break;
	   	     case Connection.TRANSACTION_SERIALIZABLE:
	   	    	info+="TRANSACTION_SERIALIZABLE";
	   	     break;
		     default:
			    info+="unknown";
			break;
		}
	    System.out.println(info);
	}
	public static void main(String[] args) throws SQLException
	{

		printDbTransationLevel();
	}
	
}
