

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Purse {
	private int balance;
	private String user;
	private JFrame jFrame;
	//Construct Purse Object if it exits already find data and Construct by the given user id 
	//else balance set as 0
	public Purse(String u,JFrame jFrame) throws SQLException
	{
		this.jFrame = jFrame;
		user=u;
		int a;
		Connect connect = null;
		Connect conn=null;
		try {
			if(findPurse())
			{
				String query="SELECT balance FROM Purse_data WHERE user_id='"+user+"'";
				connect=new Connect(query);
				ArrayList<ArrayList<String>> rows=connect.getRows();
				ArrayList<String> row=rows.get(1);
				a=Integer.parseInt(row.get(0));
				balance=a;
				connect.getResult().close();
				System.out.println("exits");
			}
			else {
				balance=0;
				String query="INSERT INTO Purse_data(user_id,balance) VALUES('"+user+"',0) ";
				String query1="INSERT INTO Cat_data(user_id,user_level,cat_size,experience) VALUES('"+user+"',1,100.00,1.00) ";
				conn=new Connect(query1);
				connect=new Connect(query);
				System.out.println("not exits");
				conn.getConn().close();
			}
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} 
		finally {
			connect.getConn().close();
			
		}
		
		
	}
	
	public void setUser(String u)
	{
		
	user=u;
	}
	
	public int withdrawal(int amount) throws SQLException
	{
		//Connect conn=new Connect("");
		String str="";
		if(balance-amount<0)
		{
			str+="You only have "+balance+" left, "+amount+" is too expensive for you!";
			System.out.println(str);
			JOptionPane.showMessageDialog(jFrame, str);
			return balance;
		}
		balance-=amount;
		Connect conn=null;
		try {
			String query1 = "UPDATE Purse_data SET balance ="+balance+" WHERE user_id='"+user+"'";
			conn=new Connect(query1);
			System.out.println("update "+balance);

		} 
		catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} 
		finally {
			conn.getConn().close();
		}
		return balance;
	}
	
	public int deposit(int amount) throws SQLException
	{
		balance+=amount;
		Connect conn=null;
		try {
			String query1 = "UPDATE Purse_data SET balance ="+balance+" WHERE user_id='"+user+"'";
			conn=new Connect(query1);
			System.out.println("update "+balance);

		} 
		catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		} 
		finally {
			conn.getConn().close();
		}
		return balance;
	}
	
	public int getBalance() throws SQLException
	{
		Connect conn = null;
		
		try {
			conn=new Connect("SELECT balance FROM Purse_data WHERE user_id='"+user+"'");
			ArrayList<ArrayList<String>> rows=new ArrayList<ArrayList<String>>();
			rows=conn.getRows();
			ArrayList<String> row=rows.get(1);
			int b=Integer.parseInt(row.get(0));
			balance=b;
			
			
		} finally {
			conn.closeConn();
		}
		return balance;
	}
	
	public boolean findPurse() throws SQLException
	{
		Connect  conn = null;
		boolean result = false;
		try {
			String query ="SELECT user_id FROM Purse_data WHERE user_id='"+user+"'";
			conn =new Connect (query);
			System.out.println(conn.toString());
			if(conn.getRows().size()==2)
			{
			//conn.closeConn();
			System.out.println("found");
			result=true;
			}
			//conn.getConn().close();
			else{System.out.println("not found");
			result= false;}
			
			}
		catch (SQLException e) {
			// TODO: handle exception
			e.getMessage();
		} 
		finally {
			conn.closeConn();
		}
		return result;
	}
	
}
