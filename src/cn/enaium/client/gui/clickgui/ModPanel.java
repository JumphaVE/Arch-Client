package cn.enaium.client.gui.clickgui;

import cn.enaium.client.Client;
import cn.enaium.client.mod.Mod;
import cn.enaium.client.mod.mods.draw.ClickGUI;
import cn.enaium.client.util.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;

/**
 * @author Enaium
 */
public class ModPanel {
    private final Mod mod;
    public int x;
    public int y;

    private final int width = 80;
    private final int height = 20;

    private boolean hovered;

    public ModPanel(Mod mod) {
        this.mod = mod;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        hovered = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;
        int background = new Color(0, 140, 255).getRGB();
        int select = new Color(117, 117, 117).getRGB();
        DrawUtil.drawRect(x, y, 80, 20, hovered ? select : background);
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        font.drawString(mod.getName(), x + (width / 2 - font.getStringWidth(mod.getName()) / 2), y + (height / 2 - font.FONT_HEIGHT / 2), 0xFFFFFFFF);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && hovered) {
            if (!mod.equals(Client.modManager.getByClass(ClickGUI.class))) {
                mod.setEnable(!mod.isEnable());
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {

    }
}
