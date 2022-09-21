

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Setting extends JFrame {
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel volumeLabel;
	private JSlider volumeSlider;
	private JTextArea dataArea;
	private JButton historyBtn;
	private JButton saveBtn;
	private JButton aboutUs;
	private String user;
	private Purse purse;
	private Level_bar lvBar;
	private MainFrame mf;
	Image img = Toolkit.getDefaultToolkit().getImage("catBackground.jpg");

	public Setting(String u,MainFrame mf) throws SQLException {
		/*
		 * this.setContentPane(new JPanel() {
		 * 
		 * @Override public void paintComponent(Graphics g) { super.paintComponent(g);
		 * g.drawImage(img, 0, 0, null); } }); pack(); setVisible(true);
		 */
		user=u;
		lvBar = new Level_bar(u,mf);
		purse=new Purse(u,this);
	}
	
	public JFrame getthis() {
		return this;
	}
	
	public Level_bar getlvBar() {
		return lvBar;
	}
	
	public void createframe() throws SQLException {
		setSize(768, 768);
		setTitle("Setting");
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setLayout(new GridLayout(3, 1));
		panel1.add(createNamePanel(),BorderLayout.NORTH);
		panel1.add(createDataArea(),BorderLayout.CENTER);
		panel1.add(createVolumeSlider(),BorderLayout.SOUTH);
		add(panel1);
		panel1.setOpaque(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void setUser(String u)
	{
		user=u;
	}
	
	public Purse getPurse()
	{
		return purse;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public JPanel createNamePanel() throws SQLException{
		JPanel a = new JPanel();
		saveBtn = new JButton("save");
		nameLabel = new JLabel();
		nameLabel.setText("I am");
		nameField = new JTextField(10);
		class saveActionListener implements ActionListener {
			public void actionPerformed(ActionEvent g) {
				String u=nameField.getText();
				Connect conn=null;
				Connect conn1=null;
				Connect conn2=null;
				purse.setUser(u);
				try {
					String query = "UPDATE Purse_data SET user_id ='"+u+"' WHERE user_id='"+user+"'";
					conn=new Connect(query);
					String query1 = "UPDATE User_data SET user_id ='"+u+"' WHERE user_id='"+user+"'";
					conn1=new Connect(query1);
					String query2 = "UPDATE Cat_data SET user_id ='"+u+"' WHERE user_id='"+user+"'";
					conn2=new Connect(query2);
					

				} 
				catch (SQLException e) {
					// TODO: handle exception
					e.getMessage();
				} 
				finally {
						try {
							conn2.getConn().close();
							conn.getConn().close();
							conn1.getConn().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				}
				setUser(u);
				System.out.println("update "+u);
			}
		}
		a.add(nameLabel);
		a.add(nameField);
		ActionListener l = new saveActionListener();
		saveBtn.addActionListener(l);
		a.add(saveBtn);
		a.setOpaque(false);
		return a;
	}

	public JPanel createDataArea() {

		class dbActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					dataArea.setText(null);
					//String query="SELECT * FROM User_data,Cat_data,Purse_data WHERE User_data.user_id=Cat_data.user_id AND User_data.user_id=Purse_data.user_id AND User_data.user_id='"+user+"'"
					Connect connect=new Connect("SELECT * FROM User_data WHERE user_id='"+user+"'");
					String strR;
					ArrayList<ArrayList<String>> rows=connect.getRows();
					strR=String.format("%20s%20s%35s%45s\n", "User_id","Mins","Date","Study Plan");
					for (int x =1;x<rows.size();x++)
					{
						ArrayList<String> arr=rows.get(x);
						strR+=String.format("%20s%25s%35s%35s\n", arr.get(0),arr.get(1),arr.get(3),arr.get(2));
					}
					
					//dataArea.append(connect.toString());
					dataArea.append(strR);
					connect.closeConn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		ActionListener listener = new dbActionListener();
		historyBtn = new JButton("View History");
		historyBtn.addActionListener(listener);

		dataArea = new JTextArea(10, 60);
		dataArea.setEditable(false);
		dataArea.setLineWrap(true);
		dataArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(dataArea);
		JPanel sss = new JPanel();
		sss.add(scrollPane);
		sss.add(historyBtn);
		sss.setOpaque(false);
		return sss;
	}

	public JPanel createVolumeSlider() {
		JPanel a = new JPanel();
		volumeLabel = new JLabel();
		volumeLabel.setText("Volume");
		volumeSlider = new JSlider(0, 100);

		class VolumeListener implements ChangeListener {
			public void stateChanged(ChangeEvent event) {
				// change the background music
			}
		}
		a.add(volumeLabel);
		a.add(volumeSlider);
		a.setOpaque(false);
		return a;
	}

	public void showResultSet(ResultSet result) throws SQLException {
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			if (i > 1)
				dataArea.append(", ");
			dataArea.append(metaData.getColumnLabel(i));
		}
		dataArea.append("/n");
		while (result.next()) {
			for (int i = 1; i <= columnCount; i++) {
				if (i > 1)
					dataArea.append(", ");
				dataArea.append(result.getString(i));
			}
			dataArea.append("/n");
		}
	}

}
