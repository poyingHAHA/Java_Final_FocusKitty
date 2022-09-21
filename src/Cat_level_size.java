import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Cat_level_size extends JLabel{
	//cat label
	private JLabel catLabel;
	//cat image location
	private String cat_img_location;
	//cat img
	private ImageIcon catImg;
	//cat size
	private double catsize;
	private Sql_update sql_update;
	private String user_id;
	
	public Cat_level_size(String user_id,String cat_img_location) throws SQLException {
		this.user_id=user_id;
		sql_update = new Sql_update();
		this.cat_img_location = cat_img_location;
		this.catsize = sql_update.getDouble("SELECT cat_size FROM Cat_data WHERE user_id='"+user_id+"'", "cat_size");
		catImg = new ImageIcon(cat_img_location); 
		Image image = catImg.getImage();
		catImg = getScaledImage(image, (int)Math.round(catsize*4), (int)Math.round(catsize*3));
		this.setIcon(catImg);
		this.setBounds(0, 0, catImg.getIconWidth(), catImg.getIconHeight());
	}
	
	
//	public JLabel addCatImg() throws IOException {
//		catImg = new ImageIcon(cat_img_location); 
//		Image image = catImg.getImage();
//		//image ratio = 4:3
//		catImg = getScaledImage(image, (int)Math.round(catsize*4), (int)Math.round(catsize*3));
//		catLabel = new JLabel(catImg);
//		catLabel.setBounds(0, 0, catImg.getIconWidth(), catImg.getIconHeight());
//		return catLabel;
//	}
	
	public void bigger(double x) throws SQLException {
		catsize = sql_update.getDouble("SELECT cat_size FROM Cat_data WHERE user_id='"+user_id+"'", "cat_size");
		catImg = new ImageIcon(cat_img_location); 
		Image image = catImg.getImage();
		//image ratio = 4:3
		catImg = getScaledImage(image, (int)Math.round(catsize*4*x), (int)Math.round(catsize*3*x));
		sql_update.update("UPDATE Cat_data SET cat_size="+catsize*x+" WHERE user_id='"+user_id+"'");
		this.setIcon(catImg);
		this.setLocation(-30, 30);
		//原圖的邊界太大，要改
		this.setBounds(0, 0, catImg.getIconWidth(), catImg.getIconHeight());
//		return catLabel;
	}
	
	//resize method
	public ImageIcon getScaledImage(Image catImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
    	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(catImg, 0, 0, w, h, null);
		g2.dispose();
		return new ImageIcon(resizedImg);
	}
}
