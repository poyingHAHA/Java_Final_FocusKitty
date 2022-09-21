import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;

public class Sql_update {
	private Connection conn;
	private Statement stat;
	private ResultSet result;
	private ResultSetMetaData metaData;

			
	public void connect(final String query)throws SQLException {
//		rows = new ArrayList<ArrayList<String>>();
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG13";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "MG13";
		String password = "qdj4e8";

		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			ResultSet result = stat.executeQuery(query);
			System.out.println("have result");
			showResultSet(result);
			
			conn.close();
			stat.close();
			result.close();
		} 
		catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void update(final String query)throws SQLException {
//		rows = new ArrayList<ArrayList<String>>();
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG13";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "MG13";
		String password = "qdj4e8";

		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			System.out.println("connected");
			stat.executeUpdate(query);
			System.out.println("have result");
			
			conn.close();
			stat.close();
		} 
		catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void showResultSet(ResultSet result) throws SQLException
	{ 
		 ResultSetMetaData metaData = result.getMetaData();
		 int columnCount = metaData.getColumnCount();
	 
		 for (int i = 1; i <= columnCount; i++)
		 {  
			 if (i > 1) System.out.print(", ");
			 System.out.print(metaData.getColumnLabel(i));
		 }
		 System.out.println();
		 
		 while (result.next())
		 {  
			 for (int i = 1; i <= columnCount; i++)
			 {  
				 if (i > 1) System.out.print(", ");
				 System.out.print(result.getString(i));
			 }
			 System.out.println();
		 }
	 }
	
	public String getString(final String query,final String columnname)throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG13";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "MG13";
		String password = "qdj4e8";

		try {
			String str;
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			result = stat.executeQuery(query);
			if(result.next()) {
				System.out.println("have result");
				str = result.getString(columnname);
//				metaData = result.getMetaData();
				conn.close();
				stat.close();
				result.close();
				return str;
			}
			else {
				return "not thing";
			}
		} 
		catch (Exception e) {
			e.getMessage();
			return "wrong";
		}
	}
	
	public int getInt(final String query,final String columnname)throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG13";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "MG13";
		String password = "qdj4e8";

		try {
			int num;
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			result = stat.executeQuery(query);
			if(result.next()) {
				System.out.println("have result");
				num = result.getInt(columnname);
//				metaData = result.getMetaData();
				conn.close();
				stat.close();
				result.close();
				return num;
			}
			else {
				return 0;
			}
		} 
		catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}
	
	public double getDouble(final String query,final String columnname)throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG13";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "MG13";
		String password = "qdj4e8";

		try {
			double num;
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			result = stat.executeQuery(query);
			if(result.next()) {
				System.out.println("have result");
				num = result.getDouble(columnname);
//				metaData = result.getMetaData();
				conn.close();
				stat.close();
				result.close();
				return num;
			}
			else {
				return 0;
			}
		} 
		catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}
	
	public boolean findString(final String query)throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG13";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "MG13";
		String password = "qdj4e8";

		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			result = stat.executeQuery(query);
			if(result.next()) {
				System.out.println("have result");
				conn.close();
				stat.close();
				result.close();
				return true;
			}
			else {
				return false;
			}
		} 
		catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
}
