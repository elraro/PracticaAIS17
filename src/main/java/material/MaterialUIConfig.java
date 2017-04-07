package material;

import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.UnsupportedLookAndFeelException;

/*
All values below are configurable. To customize a certain UI component, replace the second parameter with the border, color, font, etc. object of your choosing.
There are more UIManager settings available not configured below. A complete list can be found here: http://alvinalexander.com/java/java-uimanager-color-keys-list

The values have been set to the default light theme. A dark theme is also available by default in the GUITheme class.

If you wish to use the default value for a certain setting, simply remove the line corresponding to
unwanted change.
*/

public class MaterialUIConfig {
  public static void configureUI () {
//    UIManager.put ("Button.font", GUITheme.DARK_THEME.getBold ());
//    UIManager.put("RadioButton.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("CheckBox.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("ComboBox.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("Label.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("MenuBar.font", GUITheme.DARK_THEME.getBold ());
//    UIManager.put("MenuItem.font", GUITheme.DARK_THEME.getRegular ());
//    UIManager.put("Menu.font", GUITheme.DARK_THEME.getBold ());
//    UIManager.put("OptionPane.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("Panel.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("ScrollPane.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("Table.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("TableHeader.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("TextField.font", GUITheme.DARK_THEME.getLight ());
//    UIManager.put("TextArea.font", GUITheme.DARK_THEME.getRegular ());

    UIManager.put ("Panel.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put ("Panel.border", BorderFactory.createEmptyBorder ());

    UIManager.put ("MenuItem.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put ("MenuItem.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("MenuItem.disabledForeground", GUITheme.DARK_THEME.getMenuDisabledText ());
    UIManager.put ("MenuItem.selectionBackground", GUITheme.DARK_THEME.getMenuSelectionBackground ());
    UIManager.put("MenuItem.selectionForeground", GUITheme.DARK_THEME.getMenuSelectionText ());
    UIManager.put ("MenuItem.foreground", GUITheme.DARK_THEME.getMenuSelectionText ());

    UIManager.put("PopupMenu.border", BorderFactory.createLineBorder (GUITheme.DARK_THEME.getMenuSelectionBackground (), 1));
    UIManager.put("PopupMenu.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put("Menu.border", BorderFactory.createEmptyBorder ());
    UIManager.put("Menu.selectionBackground", GUITheme.DARK_THEME.getMenuSelectionBackground ());
    UIManager.put("Menu.selectionForeground", GUITheme.DARK_THEME.getMenuSelectionText ());
    UIManager.put("Menu.disabledForeground", GUITheme.DARK_THEME.getMenuDisabledText ());
    UIManager.put ("Menu.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put("Menu.foreground", GUITheme.DARK_THEME.getMenuSelectionText ());
    UIManager.put("Menu.opaque", true);
    UIManager.put("Menu.menuPopupOffsetY", 10);

    UIManager.put("MenuBar.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put ("MenuBar.border", GUITheme.DARK_THEME.getMenuBorder ());

    UIManager.put ("SplitPane.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("SplitPane.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put ("SplitPane.dividerSize", 5);
    UIManager.put ("SplitPaneDivider.border", BorderFactory.createEmptyBorder ());

    UIManager.put ("ScrollPane.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put ("ScrollPane.border", BorderFactory.createEmptyBorder ());

    UIManager.put ("TextArea.background", GUITheme.DARK_THEME.getInactiveTextbox ());
    UIManager.put ("TextArea.border", BorderFactory.createEmptyBorder ());
    UIManager.put ("TextArea.foreground", GUITheme.DARK_THEME.getTextboxText ());

    UIManager.put ("OptionPane.background", GUITheme.DARK_THEME.getCard ());
    UIManager.put ("OptionPane.border", GUITheme.DARK_THEME.getDefaultBorder ());

    UIManager.put ("Button.background", GUITheme.DARK_THEME.getInactiveBorderlessButtonBackground ());
    UIManager.put ("Button.foreground", GUITheme.DARK_THEME.getBorderlessButtonText ());
    UIManager.put ("Button.highlight", GUITheme.DARK_THEME.getActiveBorderlessButtonBackground ());
    UIManager.put ("Button.border", GUITheme.DARK_THEME.getDefaultBorder ());
    
    UIManager.put ("ComboBox.background", GUITheme.DARK_THEME.getInactiveBorderlessButtonBackground ());
    UIManager.put ("ComboBox.foreground", GUITheme.DARK_THEME.getBorderlessButtonText ());
    //UIManager.put ("Button.highlight", GUITheme.DARK_THEME.getActiveBorderlessButtonBackground ());
    //UIManager.put ("ComboBox.border", GUITheme.DARK_THEME.getDefaultBorder ());

    try {
      UIManager.setLookAndFeel (UIManager.getCrossPlatformLookAndFeelClassName ());
    }
    catch (UnsupportedLookAndFeelException e) {}
    catch (ClassNotFoundException e) {}
    catch (InstantiationException e) {}
    catch (IllegalAccessException e) {}
  }
}
