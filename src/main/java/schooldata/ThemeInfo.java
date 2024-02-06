package schooldata;

import java.io.Serializable;

public class ThemeInfo implements Serializable {
	
	String layoutPrimaryColor, topbarTheme, componentTheme, menuMode, menuColor, menuTheme, inputStyle;

	public String getLayoutPrimaryColor() {
		return layoutPrimaryColor;
	}

	public void setLayoutPrimaryColor(String layoutPrimaryColor) {
		this.layoutPrimaryColor = layoutPrimaryColor;
	}

	public String getTopbarTheme() {
		return topbarTheme;
	}

	public void setTopbarTheme(String topbarTheme) {
		this.topbarTheme = topbarTheme;
	}

	public String getComponentTheme() {
		return componentTheme;
	}

	public void setComponentTheme(String componentTheme) {
		this.componentTheme = componentTheme;
	}

	public String getMenuMode() {
		return menuMode;
	}

	public void setMenuMode(String menuMode) {
		this.menuMode = menuMode;
	}

	public String getMenuColor() {
		return menuColor;
	}

	public void setMenuColor(String menuColor) {
		this.menuColor = menuColor;
	}

	public String getMenuTheme() {
		return menuTheme;
	}

	public void setMenuTheme(String menuTheme) {
		this.menuTheme = menuTheme;
	}

	public String getInputStyle() {
		return inputStyle;
	}

	public void setInputStyle(String inputStyle) {
		this.inputStyle = inputStyle;
	}
	
	

}
