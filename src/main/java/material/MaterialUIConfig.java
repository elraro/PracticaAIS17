package material;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MaterialUIConfig {
	public static void configureUI() {
		
		UIManager.put("Panel.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("Panel.border", BorderFactory.createEmptyBorder());

		UIManager.put("MenuItem.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());
		UIManager.put("MenuItem.disabledForeground", GUITheme.DARK_THEME.getMenuDisabledText());
		UIManager.put("MenuItem.selectionBackground", GUITheme.DARK_THEME.getMenuSelectionBackground());
		UIManager.put("MenuItem.selectionForeground", GUITheme.DARK_THEME.getMenuSelectionText());
		UIManager.put("MenuItem.foreground", GUITheme.DARK_THEME.getMenuSelectionText());

		UIManager.put("PopupMenu.border",
				BorderFactory.createLineBorder(GUITheme.DARK_THEME.getMenuSelectionBackground(), 1));
		UIManager.put("PopupMenu.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("Menu.border", BorderFactory.createEmptyBorder());
		UIManager.put("Menu.selectionBackground", GUITheme.DARK_THEME.getMenuSelectionBackground());
		UIManager.put("Menu.selectionForeground", GUITheme.DARK_THEME.getMenuSelectionText());
		UIManager.put("Menu.disabledForeground", GUITheme.DARK_THEME.getMenuDisabledText());
		UIManager.put("Menu.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("Menu.foreground", GUITheme.DARK_THEME.getMenuSelectionText());
		UIManager.put("Menu.opaque", true);
		UIManager.put("Menu.menuPopupOffsetY", 10);

		UIManager.put("MenuBar.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("MenuBar.border", GUITheme.DARK_THEME.getMenuBorder());

		UIManager.put("SplitPane.border", BorderFactory.createEmptyBorder());
		UIManager.put("SplitPane.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("SplitPane.dividerSize", 5);
		UIManager.put("SplitPaneDivider.border", BorderFactory.createEmptyBorder());

		UIManager.put("ScrollPane.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());

		UIManager.put("TextArea.background", GUITheme.DARK_THEME.getInactiveTextbox());
		UIManager.put("TextArea.border", BorderFactory.createEmptyBorder());
		UIManager.put("TextArea.foreground", GUITheme.DARK_THEME.getTextboxText());

		UIManager.put("OptionPane.background", GUITheme.DARK_THEME.getCard());
		UIManager.put("OptionPane.border", GUITheme.DARK_THEME.getDefaultBorder());

		UIManager.put("Button.background", GUITheme.DARK_THEME.getInactiveBorderlessButtonBackground());
		UIManager.put("Button.foreground", GUITheme.DARK_THEME.getBorderlessButtonText());
		UIManager.put("Button.highlight", GUITheme.DARK_THEME.getActiveBorderlessButtonBackground());
		UIManager.put("Button.border", GUITheme.DARK_THEME.getDefaultBorder());

		UIManager.put("ComboBox.background", GUITheme.DARK_THEME.getInactiveBorderlessButtonBackground());
		UIManager.put("ComboBox.foreground", GUITheme.DARK_THEME.getBorderlessButtonText());
		// UIManager.put ("Button.highlight",
		// GUITheme.DARK_THEME.getActiveBorderlessButtonBackground ());
		// UIManager.put ("ComboBox.border",
		// GUITheme.DARK_THEME.getDefaultBorder ());

		UIManager.put("RadioButton.background", GUITheme.DARK_THEME.getInactiveTextbox());
		UIManager.put("RadioButton.border", BorderFactory.createEmptyBorder());
		UIManager.put("RadioButton.foreground", GUITheme.DARK_THEME.getTextboxText());
		UIManager.put("RadioButton.highlight", GUITheme.DARK_THEME.getActiveBorderlessButtonBackground());

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
	}
}
