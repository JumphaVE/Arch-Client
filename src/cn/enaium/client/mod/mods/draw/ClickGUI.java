package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.Client;
import cn.enaium.client.gui.clickgui.ClickGui;
import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * @author Enaium
 */
public class ClickGUI extends Mod {
    public ClickGUI() {
        super("ClickGUI", Category.DRAW);
        setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void enable() {
        Minecraft.getMinecraft().displayGuiScreen(Client.clickGui);
        setEnable(false);
    }
}
