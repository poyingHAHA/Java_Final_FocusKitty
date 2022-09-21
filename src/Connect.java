
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
	private Connection conn;
	private Statement stat;
	private ResultSet result;
	private ResultSetMetaData metaData;
	
	private ArrayList<ArrayList<String>> rows;
	private ArrayList<String> row;
	
	public ArrayList<ArrayList<String>> getRows() {
		return rows;
	}
	
	public Connection getConn() {
		return conn;
	}
	public Statement getStat() {
		return stat;
	}
	public ResultSet getResult() {
		return result;
	}
	public ResultSetMetaData getMetaData() {
		return metaData;
	}
	
	public Connect(final String query) throws SQLException {
		rows = new ArrayList<ArrayList<String>>();
		
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG13";
		String config= "?useUnicode=true&characterEncoding=utf8";
		String url = server + database + config;
		String username = "MG13";
		String password = "qdj4e8";

		try {
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			boolean hasResultSet = stat.execute(query);
			if(hasResultSet) {
				result = stat.getResultSet();
				metaData = result.getMetaData();
				toArrayList(result);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void toArrayList(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		
		row = new ArrayList<String>();
		for (int i = 1; i <= columnCount; i++) {
			row.add(metaData.getColumnLabel(i));
		}
		rows.add(row);

		while (result.next()) {
			row = new ArrayList<String>();
			for (int i = 1; i <= columnCount; i++) {
				row.add(result.getString(i));
			}
			rows.add(row);
		}
	}
	
	public String toString() {
		String str="";
		ArrayList<ArrayList<String>> a =getRows();
		for (ArrayList<String> b: a) {
			for (String col : b) {
				str+=col + "\t";
			}
			str+="\n";
		}
		return str;
	}
	
	public void closeConn() throws SQLException {
		getResult().close();
		getConn().close();
	}
}
