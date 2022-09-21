import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame {
	private JPanel panel;
	private JLabel bg,balLabel,catLabel;
	private final int FRAME_WIDTH = 432;
	private final int FRAME_HEIGHT = 650;
	private final String[] btnNames = {"timer.jpg", "setting.jpg", "shop.png"};
	private final int JBUTTON_COUNT = btnNames.length;
	private JButton[] jButtons = new JButton[JBUTTON_COUNT];
	private Setting setting;
	private Rewards rw;
	private Cat_level_size cat_level_size;
	
	public MainFrame(String user_id) throws IOException, InterruptedException, SQLException {
		// this.setLayout(null);
		setting = new Setting(user_id,getthis());
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		balLabel = creatbalLabel();
		Level_bar lvBar = setting.getlvBar();
		cat_level_size = new Cat_level_size(user_id, "src/cat.gif");
//		catLabel = cat_level_size.addCatImg();
		JPanel levelBar=lvBar.create_Panel();
		cat_level_size.setLocation(30, 350);
		levelBar.setLocation(0, 10);
		levelBar.setSize(420, 50);
		Color color = new Color(0,0,0,0);
		levelBar.setBackground(color);
		balLabel.setSize(200,200);
		balLabel.setLocation(300, 600);
		
		ImageIcon icon = new ImageIcon("bg.jpg");
		icon = Resizer.resize(icon.getImage(), this.getWidth(), this.getHeight());
		bg = new JLabel();
		bg.setIcon(icon);
		bg.setSize(this.getSize());
		
		createJButtons();
		// panel.setBorder(new TitledBorder(new EtchedBorder(), "1"));
		for (JButton button : jButtons) {
			panel.add(button);
		}
		panel.add(levelBar);
		panel.add(cat_level_size);
		panel.add(balLabel);
		panel.add(bg);
		this.add(panel);
	}
	
	public Cat_level_size getcat() {
		return cat_level_size;
	}
	
	public JLabel getbaLabel() {
		return balLabel;
	}
	
	public JLabel getcatLabel() {
		return this.catLabel;
	}
	
	public MainFrame getthis() {
		return this;
	}
	
	private void createJButtons() {
		double fw = this.getWidth();
		double fh = this.getHeight();
		double w = fw * 40 / 100;
		double h = fh * 5 / 100;

		//System.out.println(x + ", " + y + ", " + fw + ", " + fh);
		//System.out.println(fw * 10 / 100);

		for (int i = 0; i < JBUTTON_COUNT; i++) {
			jButtons[i] = createButton(btnNames[i]);
//			jButtons[i].setSize((int) w, (int) h);
			jButtons[i].setLocation((int) (fw * 6 / 100), (int) (fh * 67 / 100 + i * (1.8*h)));
			//jButtons[i].setBorder(null);
			jButtons[i].setContentAreaFilled(false);
		}
		
		class timerListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				new Counter(setting,rw,getthis());
			}
		}
		
		class settingListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					setting.createframe();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		class shopListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					rw = new Rewards(setting,getthis());
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		ActionListener timerActionListener = new timerListener();
		ActionListener settingActionListener = new settingListener();
		ActionListener shopActionListener = new shopListener();
		jButtons[0].addActionListener(timerActionListener);
		jButtons[1].addActionListener(settingActionListener);
		jButtons[2].addActionListener(shopActionListener);
	}

	public JButton createButton(final String name) {
		ImageIcon imgIcon = Resizer.resize(new ImageIcon(name).getImage(), 100, 50);
		JButton button = new JButton(imgIcon);
		button.setSize(imgIcon.getIconWidth(), imgIcon.getIconHeight());
		
		return button;
	}
	
	public JLabel creatbalLabel() throws SQLException {
		balLabel = new JLabel();
		balLabel.setText("Balance: $"+String.valueOf(setting.getPurse().getBalance()));
		return balLabel;
	}
}
