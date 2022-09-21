import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.text.View;

public class Level_bar extends Sql_update{
	private JProgressBar levelBar;
	private JLabel leveLabel;
	private int level;
	private double present_value;
	private Sql_update sql_update=new Sql_update(); 
	private String user_id;
	private MainFrame mf;
//	JLabel level_str;
	
	public Level_bar(String u,MainFrame mf) {
		this.mf=mf;
		this.user_id=u;
	}
	
	public JPanel create_Panel()throws InterruptedException, SQLException {
		level = sql_update.getInt("SELECT user_level FROM Cat_data WHERE user_id='"+user_id+"'", "user_level");
		JPanel panel = new JPanel();
		JLabel leveLabel = createlevel();
		panel.add(leveLabel);
		panel.add(createLevelBar());
		present_value = sql_update.getDouble("SELECT experience FROM Cat_data WHERE user_id='"+user_id+"'", "experience");
		levelBar.setValue((int)Math.round(present_value));
		return panel;
	}
	
	public JLabel createlevel() {
		leveLabel = new JLabel();
		leveLabel.setText("LV: "+level);
		return leveLabel;
	}
	
	public JProgressBar createLevelBar() {
//		JLabel barPanel = new JLabel();
//		level_str = new JLabel("LV: "+this.level+" ");
		levelBar = new JProgressBar();
//		barPanel.add(level_str);
//		barPanel.add(levelBar);
		levelBar.setValue(10);
		levelBar.setPreferredSize(new Dimension(300,40));
		//用label代替直接改border
		levelBar.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GREEN,5)));
//		levelBar.setBorder(BorderFactory.createLineBorder(Color.green , 10));
		levelBar.setBackground(Color.ORANGE);
		levelBar.setForeground(Color.CYAN);
		levelBar.setBounds(0, 0, 400, 40);
		levelBar.setStringPainted(true);
		return levelBar;
	}
	
	//監視經驗值改變，改變level_bar長度，改變level跟level_str的string
	public void levelBar_update(Double experience) throws InterruptedException, SQLException {
		present_value+=experience;
		if(present_value<100) {
			levelBar.setValue((int)Math.round(present_value));
			sql_update.update("UPDATE Cat_data SET experience="+present_value+" WHERE user_id='"+user_id+"'");
//			level_str.setText("LV: "+this.level+" ");
		}
		else {
			this.present_value=(this.present_value-100);
			levelUp();
			levelBar.setValue((int)Math.round(present_value));
			sql_update.update("UPDATE Cat_data SET experience="+(int)Math.round(present_value)+" WHERE user_id='"+user_id+"'"); 
		}
	}
	
//	public void levelBar_update() throws InterruptedException, SQLException {
////		present_value = sql_update.getDouble("SELECT experience FROM Cat_data WHERE user_id='QQ'", "experience");
//		present_value=0.0;
//		levelBar.setValue((int)Math.round(present_value));
//		sql_update.update("UPDATE Cat_data SET experience="+present_value+" WHERE user_id='"+user_id+"'");
////			level_str.setText("LV: "+this.level+" ");
//	}
	
	//監視等級改變
	public void levelUp() throws SQLException {
		this.level+=1;
		this.leveLabel.setText("LV: "+this.level);
//		mf.getcat().setLocation(30, 300);
		mf.getcat().bigger(1.2);
		sql_update.update("UPDATE Cat_data SET user_level="+this.level+" WHERE user_id='"+user_id+"'");
//		catLabel = mf.getcat().bigger(1.2);
		this.leveLabel.setBackground(new Color(0,0,0,0));
	}
}
