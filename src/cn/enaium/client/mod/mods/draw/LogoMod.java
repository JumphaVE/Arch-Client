package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.Client;
import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;

/**
 * @author Enaium
 */
public class LogoMod extends Mod {
    public LogoMod() {
        super("Logo", Category.DRAW);
    }

    @Override
    public void draw() {
        String text = Client.NAME + " | " + Client.VERSION;
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        font.drawString(text, 0, 0, new Color(0, 26, 255).getRGB());
    }
}
