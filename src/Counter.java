import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.EventListener;
import java.util.Timer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.magiclen.magicaudioplayer.AudioPlayer;
//import javax.swing.Timer;



public class Counter extends JFrame{
	private JLabel workLabel,timeLabel,countLabel;
	private JTextField workField,timeField;
	private JButton startButton,restButton,tomatoButton,stopButton;
	private TimerTask task;
	private Setting setting;
	private Rewards rw;
	private MainFrame mf;
	private AudioPlayer badPlayer,goodPlayer;
	
	public Counter(Setting setting,Rewards rw,MainFrame mf) {
		File audioFile = new File("src/bad_cat.wav");
		badPlayer = AudioPlayer.createPlayer(audioFile);
//		player.play();
		badPlayer.setVolume(100);
		badPlayer.setBalance(100);
		badPlayer.setAutoClose(true);
		File audioFile2 = new File("src/cat_soft3.wav");
		goodPlayer = AudioPlayer.createPlayer(audioFile2);
//		player.play();
		goodPlayer.setAutoClose(true);
		goodPlayer.setVolume(100);
		goodPlayer.setBalance(100);
		this.setting = setting;
		this.rw = rw;
		this.mf = mf;
		setLayout(null);
		JButton startButton = createstartbtn();
		JButton tomatoButton = createtomatotbtn();
		JButton restButton = createrestButton();
		JPanel workPanel = creatework();
		JPanel timePanel = createtime();
		countLabel = createLabel("00:00");
		add(workPanel);
		add(timePanel);
		add(startButton);
		add(tomatoButton);
		add(restButton);
		add(createstop());
		add(countLabel);
		countLabel.setLocation(160, 5);
		countLabel.setSize(200, 200);
		workPanel.setLocation(10, 180);
		workPanel.setSize(500, 50);
		timePanel.setLocation(0, 240);
		timePanel.setSize(400, 60);
		startButton.setLocation(30, 340);
		startButton.setSize(120, 100);
		tomatoButton.setLocation(190, 340);
		tomatoButton.setSize(120, 100);
		restButton.setLocation(340, 340);
		restButton.setSize(120, 100);
		stopButton.setLocation(390, 300);
		stopButton.setSize(70, 30);
		stopButton.setBackground(Color.red);
		stopButton.setForeground(Color.green);
		setSize(500, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public JButton createstop() {
		stopButton = new JButton("reset");
		
		class StopListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				task.cancel();
				countLabel.setText("00:00");
			}
		}
		
		ActionListener stopActionListener = new StopListener();
		stopButton.addActionListener(stopActionListener);
		
		return stopButton;
	}
	
	public JPanel creatework() {
		JPanel workPanel = new JPanel();
		workLabel = new JLabel("Your Work: ");
		workLabel.setFont(new Font("monospaced", Font.BOLD|Font.ITALIC , 16));
		workField = new JTextField(30);
		workField.setDocument(new JTextFieldLimit(30));
		workPanel.add(workLabel);
		workPanel.add(workField);
		return workPanel;
	}
	
	public JPanel createtime() {
		JPanel timePanel = new JPanel();
		JLabel minLabel = new JLabel("minutes");
		timeLabel = new JLabel("Time range: ");
		timeLabel.setFont(new Font("monospaced", Font.BOLD|Font.ITALIC , 16));
		timeField = new JTextField(10);
		timeField.setText("25");
		timeField.setForeground(Color.PINK);
	    timePanel.add(timeLabel);
	    timePanel.add(timeField);
	    timePanel.add(minLabel);
		return timePanel;
	}
	
	public JFrame getthis() {
		return this;
	}
	
	public int getmin() {
		return Integer.parseInt(timeField.getText());
	}
	
	public void playbad() {
		File audioFile = new File("src/cat_bad.wav");
		AudioPlayer player = AudioPlayer.createPlayer(audioFile);
		player.play();
		player.setVolume(100);
		player.setBalance(100);
		player.setAutoClose(true);
	}
	
	public TimerTask returnTask(int mins) {
		task = new TimerTask() {
			private int sec=60;
			private int min = mins-1;
			public void run() {
				if(sec==0) {
					sec=59;
					min--;
					if(min<0) {
						//這邊加錢
						try {
							//音樂
							goodPlayer.play();
							//
							setting.getPurse().deposit(5);
							mf.getbaLabel().setText("Balance: $"+String.valueOf(setting.getPurse().getBalance()));
							Connect conn1=new Connect("INSERT INTO User_data(user_id,setting_range_mins,study_plan) VALUES('"+setting.getUser()+"',"+timeField.getText()+",'"+workField.getText()+"')");
							//Connect conn1=new Connect("UPDATE User_data SET setting_range_mins ="+timeField.getText()+" WHERE user_id='"+setting.getUser()+"'");
							//Connect conn2=new Connect("UPDATE User_data SET study_plan ="+workField.getText()+" WHERE user_id='"+setting.getUser()+"'");
							conn1.getConn().close();
							//conn1.getConn().close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
//						catch (UnsupportedAudioFileException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (LineUnavailableException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						countLabel.setText("00:00");
						this.cancel();
					}else {
						countLabel.setText(min+":"+sec);						
					}
				}
				else {
					sec--;
					countLabel.setText(min+":"+sec);
				}					
			}
		};
		return task;
	}
	
	public JButton createstartbtn() {
		startButton = new JButton("Start");
		
		class startmouselis implements MouseListener,MouseMotionListener{
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print("點擊----" + "\t");
				if (e.getClickCount()==1) {
					System.out.println("click！");
				} else if (e.getClickCount()==2) {
					System.out.println("D click！");
				} else if (e.getClickCount()==3) {
					System.out.println("T click！！");
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("按下");
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("鬆開");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//
				System.out.println("exit");
				countLabel.setText("00:00");
				task.cancel();
				//音樂
				badPlayer.play();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				String string = "拖動位置：（" + e.getX() + "，" + e.getY() +"）";
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		class Count implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				startButton.addMouseListener(new startmouselis());
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(returnTask(Integer.parseInt(timeField.getText())), 1000, 1000);
			}
		}
		
		
		ActionListener count = new Count();
		startButton.addActionListener(count);
		return startButton;
	}
	
	public JButton createtomatotbtn() {
		tomatoButton = new JButton("Tomato");
		
		class startmouselis implements MouseListener,MouseMotionListener{
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print("點擊----" + "\t");
				if (e.getClickCount()==1) {
					System.out.println("click！");
				} else if (e.getClickCount()==2) {
					System.out.println("D click！");
				} else if (e.getClickCount()==3) {
					System.out.println("T click！！");
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("按下");
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("鬆開");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//
				System.out.println("exit");
				countLabel.setText("00:00");
				task.cancel();
				//音樂
				badPlayer.play();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				String string = "拖動位置：（" + e.getX() + "，" + e.getY() +"）";
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		}
//		startButton.setSize(50, 30);
		class Count implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				Timer timer = new Timer();
				tomatoButton.addMouseListener(new startmouselis());
				timer.scheduleAtFixedRate(returnTask(25), 1000, 1000);								
			}
		}
		
		ActionListener count = new Count();
		tomatoButton.addActionListener(count);
		return tomatoButton;
	}
	
	public JButton createrestButton() {
		restButton = new JButton("Rest");
		
		class startmouselis implements MouseListener,MouseMotionListener{
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print("點擊----" + "\t");
				if (e.getClickCount()==1) {
					System.out.println("click！");
				} else if (e.getClickCount()==2) {					
					System.out.println("D click！");
				} else if (e.getClickCount()==3) {
					System.out.println("T click！！");
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("按下");
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
//				task.cancel();
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("exit");
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				String string = "拖動位置：（" + e.getX() + "，" + e.getY() +"）";
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		}
		
		class Count implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				restButton.addMouseListener(new startmouselis());
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(returnTask(Integer.parseInt(timeField.getText())), 1000, 1000);
			}
		}
		
		ActionListener count = new Count();
		restButton.addActionListener(count);
		return restButton;
	}
	
	public JLabel createLabel(String x) {
		countLabel = new JLabel(x);
		countLabel.setFont(countLabel.getFont ().deriveFont (64.0f));
		return countLabel;
	}
	

	
}