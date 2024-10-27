package cn.enaium.client.gui.clickgui;

import cn.enaium.client.Client;
import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import cn.enaium.client.util.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enaium
 */
public class CategoryPanel {
    private int x;
    private int y;
    private Category category;

    private final int width = 80;
    private final int height = 20;

    private int prevX;
    private int prevY;

    private boolean press;

    private boolean hovered;

    private final List<ModPanel> modPanels = new ArrayList<>();

    private boolean displayMod = false;

    public CategoryPanel(int x, int y, Category category) {
        this.x = x;
        this.y = y;
        this.category = category;

        for (Mod mod : Client.modManager.getByCategory(category)) {
            modPanels.add(new ModPanel(mod));
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        hovered = mouseX >= x && mouseY >= y && mouseX < x + this.width && mouseY < y + this.height;

        if (hovered && press) {
            x = mouseX + prevX;
            y = mouseY + prevY;
        }

        int background = new Color(0, 140, 255, 255).getRGB();
        int select = new Color(255, 98, 0, 255).getRGB();
        DrawUtil.drawRect(x, y, width, height, hovered ? select : background);
        FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
        fontRendererObj.drawString(category.name(), x + (80 / 2 - fontRendererObj.getStringWidth(category.name()) / 2), y + (20 / 2 - fontRendererObj.FONT_HEIGHT / 2), 0xFFFFFFFF);


        if (displayMod) {
            int index = 0;
            for (ModPanel it : modPanels) {
                it.drawScreen(mouseX, mouseY, partialTicks);
                it.x = x;
                it.y = y + 20 + index;
                index += 20;
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            press = true;
            prevX = x - mouseX;
            prevY = y - mouseY;
        } else if (mouseButton == 1 && hovered) {
            displayMod = !displayMod;
        }

        if (displayMod) {
            modPanels.forEach(it -> it.mouseClicked(mouseX, mouseY, mouseButton));
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        press = false;

        if (displayMod) {
            modPanels.forEach(it -> it.mouseReleased(mouseX, mouseY, state));
        }
    }
}
