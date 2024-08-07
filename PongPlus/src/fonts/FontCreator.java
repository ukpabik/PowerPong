package fonts;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FontCreator implements FontManager{
	
	
	Font pixel, points;
	String infoMessage;
	
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
	
//	@Override
//	public Font getTextFont() {
//		return new Font("Arial", Font.ITALIC, )
//	}
	
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
	
	@Override
	public Font infoFont() {
		return getPointsFont().deriveFont(Font.PLAIN, 24);
	}
	
	@Override
	public String loadFromFile() {
		if (infoMessage == null) {
	        try {
	            infoMessage = Files.readString(Paths.get("src/fonts/info_message.txt"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		return infoMessage;
	}
	
}
