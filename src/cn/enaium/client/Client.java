package cn.enaium.client;

import cn.enaium.client.command.CommandManager;
import cn.enaium.client.config.ConfigManager;
import cn.enaium.client.gui.clickgui.ClickGui;
import cn.enaium.client.mod.ModManager;
import org.lwjgl.opengl.Display;

/**
 * @author Enaium
 */
public class Client {
    public static final String NAME = "HB-Client";
    public static final String VERSION = "0.1.0";
    public static ModManager modManager;
    public static ConfigManager configManager;
    public static CommandManager commandManager;

    public static ClickGui clickGui;

    public static void start() {
        modManager = new ModManager();
        configManager = new ConfigManager();
        commandManager = new CommandManager();
        modManager.load();
        configManager.load();
        commandManager.load();
        clickGui = new ClickGui();
        Display.setTitle(NAME + " | " + VERSION);
    }

    public static void stop() {
        configManager.save();
    }
}
