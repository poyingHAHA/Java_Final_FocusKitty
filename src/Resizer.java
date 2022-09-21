import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Resizer {
	/**
	 * Resize method.
	 * @param image The image object.
	 * @param w The width of image.
	 * @param h The height of image.
	 * @return Resized ImageIcon.
	 */
	public static ImageIcon resize(Image image, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, w, h, null);
		g2.dispose();
		ImageIcon resizedImage = new ImageIcon(resizedImg);
		return resizedImage;
	}
}
