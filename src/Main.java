import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.util.Scanner;
public class Main {
	private int s=1;
	public void change()
	{
		this.s=10;
	}
	public static void main(String[] args) throws IOException, InterruptedException, SQLException {
		/*System.out.print("Input your user ID:");
		Scanner in=new Scanner(System.in);
		String user=in.next();
		MainFrame mainFrame = new MainFrame(user);
		mainFrame.setTitle("Cat");
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
		int a=1;
		System.out.println("a is "+(++a));
	}

}
