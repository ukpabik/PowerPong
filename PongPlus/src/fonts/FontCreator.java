package fonts;

import java.awt.Font;
import java.io.InputStream;

public class FontCreator implements FontManager{
	
	
	Font pixel, points;
	
	@Override
	public Font getPixelFont() {
		
		if (pixel == null) {
			try {
				InputStream is = getClass().getResourceAsStream("/fonts/ARCADECLASSIC.ttf");
				pixel = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return pixel;
		
	}
	
	@Override
	public Font getPointsFont() {
		if (points == null) {
			try {
				InputStream is = getClass().getResourceAsStream("/fonts/dpcomic.ttf");
				points = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return points;
	}
	
	@Override
	public Font pixelFontMainMenu() {
		
		return getPixelFont().deriveFont(Font.BOLD, 128);
		
	}
	
	@Override
	public Font pixelFontSelectables() {
		return getPixelFont().deriveFont(Font.ITALIC, 32);
	}
	
	@Override
	public Font pixelFontOptions() {
		return getPixelFont().deriveFont(Font.BOLD, 64);
	}
	
	@Override
	public Font pixelFontControls() {
		return getPixelFont().deriveFont(Font.BOLD, 24);
	}
	
	@Override
	public Font pointFont() {
		return getPointsFont().deriveFont(Font.BOLD, 64);
	}
	
	@Override
	public Font numberFont() {
		return getPointsFont().deriveFont(Font.BOLD, 36);
	}
	
}
