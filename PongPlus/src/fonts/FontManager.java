package fonts;

import java.awt.Font;

public interface FontManager {

	public Font getPixelFont();

	public Font pixelFontMainMenu();

	public Font pixelFontSelectables();

	public Font pixelFontOptions();

	public Font pixelFontControls();

	public Font getPointsFont();

	public Font pointFont();

	public Font numberFont();

	public Font infoFont();

	public String loadFromFile();
	
}
