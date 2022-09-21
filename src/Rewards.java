
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rewards extends JFrame{
	private JButton milkButton;
	private JButton fishButton;
	private JButton waterButton;
	private JButton cheeseButton;
	private JButton toyButton;
	private JButton boxButton;
	private String user;
	//private Purse purse;
	private JLabel balance;
	private Setting set;
	private MainFrame mf;
	//cat object
	//purchase object
	Image img = Toolkit.getDefaultToolkit().getImage("shopBackground.jpg");

	public Rewards (Setting set,MainFrame mf) throws IOException//Constructor
, SQLException
	{
		this.mf=mf;
		this.set=set;
		user=set.getUser();
//		//purse=new Purse(user);
//		this.setContentPane(new JPanel() {
//	         @Override
//	         public void paintComponent(Graphics g) {
//	            super.paintComponent(g);
//	            g.drawImage(img, 60, 50, null);
//	  //drawImage(Image img, int x, int y, ImageObserver observer);The x,y location specifies the position for the top-left of the image.
//	         }
//	      });
//	      pack();
//	     setVisible(true);
		setSize(400,600);
		setTitle("Rewards");
		createPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);      
	}
	
	public JLabel getbalance() {
		return balance;
	}
	
	public JButton createMilk()//milkButton
	{
		
		ImageIcon milkIcon = new ImageIcon("milk.png");
		milkButton=new JButton(milkIcon);
		milkButton.setSize(10,10);
		milkButton.setOpaque(false);
		milkButton.setContentAreaFilled(false);
		class milkActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//cat 's level increase 10
				try {
					set.getlvBar().levelBar_update(10.0);
					set.getPurse().withdrawal(10);
					balance.setText("$:"+set.getPurse().getBalance());
					mf.getbaLabel().setText("Balance: $"+String.valueOf(set.getPurse().getBalance()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
		ActionListener l =new milkActionListener();
		milkButton.addActionListener(l);
		return milkButton;
	}
	
	public JButton createWater()//waterButton
	{
		ImageIcon waterIcon = new ImageIcon("water.png");
		waterButton=new JButton(waterIcon);
		waterButton.setSize(10,10);
		waterButton.setOpaque(false);
		waterButton.setContentAreaFilled(false);
		class waterActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//cat 's level increase 5
				try {
					set.getlvBar().levelBar_update(5.0);
					set.getPurse().withdrawal(5);
					balance.setText("$:"+set.getPurse().getBalance());
					mf.getbaLabel().setText("Balance: $"+String.valueOf(set.getPurse().getBalance()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
		ActionListener l =new waterActionListener();
		waterButton.addActionListener(l);
		return waterButton;
	}
	
	public JButton createFish()//fishButton
	{
		ImageIcon fishIcon = new ImageIcon("salmon.png");
		fishButton=new JButton(fishIcon);
		fishButton.setSize(10,10);
		fishButton.setOpaque(false);
		fishButton.setContentAreaFilled(false);
		class fishActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//cat 's level increase 40
				try {
					set.getlvBar().levelBar_update(40.0);
					set.getPurse().withdrawal(40);
					balance.setText("$:"+set.getPurse().getBalance());
					mf.getbaLabel().setText("Balance: $"+String.valueOf(set.getPurse().getBalance()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
		ActionListener l =new fishActionListener();
		fishButton.addActionListener(l);
		return fishButton;	
	}
	
	public JButton createCheese()//cheeseButton
	{
		ImageIcon cheeseIcon = new ImageIcon("cheese.png");
		cheeseButton=new JButton(cheeseIcon);
		cheeseButton.setSize(10,10);
		cheeseButton.setOpaque(false);
		cheeseButton.setContentAreaFilled(false);
		class cheeseActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//cat 's level increase 20
				try {
					set.getlvBar().levelBar_update(20.0);
					set.getPurse().withdrawal(20);
					balance.setText("$:"+set.getPurse().getBalance());
					mf.getbaLabel().setText("Balance: $"+String.valueOf(set.getPurse().getBalance()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
		ActionListener l =new cheeseActionListener();
		cheeseButton.addActionListener(l);
		return cheeseButton;	
	}
	
	public JButton createToy()//逗貓棒Button
	{
		ImageIcon toyIcon = new ImageIcon("cat-toy.png");
		toyButton=new JButton(toyIcon);
		toyButton.setSize(10,10);
		toyButton.setOpaque(false);
		toyButton.setContentAreaFilled(false);
		class toyActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//cat 's level increase 30
				try {
					set.getlvBar().levelBar_update(30.0);
					set.getPurse().withdrawal(30);
					balance.setText("$:"+set.getPurse().getBalance());
					mf.getbaLabel().setText("Balance: $"+String.valueOf(set.getPurse().getBalance()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
			}
		}
		ActionListener l =new toyActionListener();
		toyButton.addActionListener(l);
		return toyButton;	
	}
	
	public JButton createBox()//misteryBox
	{
		ImageIcon boxIcon = new ImageIcon("question-mark.png");
		boxButton=new JButton(boxIcon);
		boxButton.setSize(10,10);
		boxButton.setOpaque(false);
		boxButton.setContentAreaFilled(false);
		class boxActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				double a1=Math.random()*100;
//				int a=(int) a1;
				System.out.println(a1);
				
				//cat 's level increase a
				try {
					set.getlvBar().levelBar_update(a1);
					set.getPurse().withdrawal(66);
					balance.setText("$:"+set.getPurse().getBalance());
					mf.getbaLabel().setText("Balance: $"+String.valueOf(set.getPurse().getBalance()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		}
		ActionListener l =new boxActionListener();
		boxButton.addActionListener(l);
		return boxButton;	
	}
	
	
	public void createPanel() throws SQLException
	{
		JPanel big = new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel11=new JPanel();
		JPanel panel22=new JPanel();
		JPanel panel2 = new JPanel();
		JLabel ml=new JLabel();
		JLabel wl=new JLabel();
		JLabel fl=new JLabel();
		JLabel cl=new JLabel();
		JLabel tl=new JLabel();
		JLabel bl=new JLabel();
		ml.setText("     $10/+10");
		wl.setText("     $5/+5");
		fl.setText("     $40/+40");
		cl.setText("     $20/+20");
		tl.setText("     $30/+30");
		bl.setText("   $66/random");
		balance=new JLabel();
		balance.setText("$:"+set.getPurse().getBalance());
		//JPanel mainPanel=new JPanel();
		panel1.setSize(200, 200);
		panel11.setSize(200, 200);
		panel22.setSize(200, 200);
		panel2.setSize(200, 200);
		panel1.setLayout(new GridLayout(1,3));
		panel1.add(createMilk());
		panel1.add(createFish());
		panel1.add(createWater());
		panel11.setLayout(new GridLayout(1,3));
		panel11.add(ml);
		panel11.add(fl);
		panel11.add(wl);
		panel2.setLayout(new GridLayout(1,3));
		panel2.add(createCheese());
		panel2.add(createToy());
		panel2.add(createBox());
		panel22.setLayout(new GridLayout(1,3));
		panel22.add(cl);
		panel22.add(tl);
		panel22.add(bl);
		big.add(balance);
		//mainPanel.setLayout(new BorderLayout());
		/*FlowLayout f = (FlowLayout)panel1.getLayout();
		f.setHgap(100);
		f.setVgap(30);
		FlowLayout f1 = (FlowLayout)panel11.getLayout();
		f1.setHgap(100);
		f1.setVgap(30);
		FlowLayout f22 = (FlowLayout)panel22.getLayout();
		f22.setHgap(100);
		f22.setVgap(30);
		FlowLayout f2 = (FlowLayout)panel2.getLayout();
		f2.setHgap(100);
		f2.setVgap(30);*/
//		panel1.setBorder(new TitledBorder(new EtchedBorder(),"Shop"));
		//panel1.setBackground(null);
		panel1.setOpaque(false);
		panel2.setOpaque(false);
		//mainPanel.add(panel1,BorderLayout.CENTER);
		//mainPanel.setOpaque(false);
		
		JPanel mp=new JPanel();
		mp.setLayout(new GridLayout(4,1));
		mp.add(panel1);
		mp.add(panel11);
		mp.add(panel2);
		mp.add(panel22);
		//add(panel1,BorderLayout.NORTH);
		//add(panel11,BorderLayout.NORTH);
		//add(panel2,BorderLayout.CENTER);
		//add(panel22,BorderLayout.NORTH);
		add(mp);
		add(big,BorderLayout.SOUTH);
	}
}
