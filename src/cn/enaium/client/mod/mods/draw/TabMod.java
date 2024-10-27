package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.Client;
import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.List;

/**
 * @author Enaium
 */
public class TabMod extends Mod {

    private int currentCategory = 0;

    private int currentMod;

    private boolean mod = false;

    public TabMod() {
        super("Tab", Category.DRAW);
    }

    @Override
    public void draw() {
        drawTab(0, 10);
    }

    @Override
    public void key(int key) {
        if (!mod) {
            if (key == Keyboard.KEY_UP) {
                if (currentCategory > 0) {
                    currentCategory--;
                } else {
                    currentCategory = Category.values().length - 1;
                }
            } else if (key == Keyboard.KEY_DOWN) {
                if (currentCategory < Category.values().length - 1) {
                    currentCategory++;
                } else {
                    currentCategory = 0;
                }
            } else if (key == Keyboard.KEY_RETURN || key == Keyboard.KEY_RIGHT) {
                mod = true;
            }
        } else {
            if (key == Keyboard.KEY_UP) {
                if (currentMod > 0) {
                    currentMod--;
                } else {
                    currentMod = Client.modManager.getByCategory(Category.values()[currentCategory]).size() - 1;
                }
            } else if (key == Keyboard.KEY_DOWN) {
                if (currentMod < Client.modManager.getByCategory(Category.values()[currentCategory]).size() - 1) {
                    currentMod++;
                } else {
                    currentMod = 0;
                }
            } else if (key == Keyboard.KEY_RETURN || key == Keyboard.KEY_RIGHT) {
                Mod m = Client.modManager.getByCategory(Category.values()[currentCategory]).get(currentMod);
                m.setEnable(!m.isEnable());
            } else if (key == Keyboard.KEY_LEFT) {
                mod = false;
                currentMod = 0;
            }
        }
    }

    private void drawTab(int x, int y) {
        FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
        int index = y;
        Category[] values = Category.values();
        for (int i = 0; i < values.length; i++) {
            Category value = values[i];
            Gui.drawRect(x, index
                    , x + fontRendererObj.getStringWidth(Category.MOVEMENT.name()), index + fontRendererObj.FONT_HEIGHT
                    , i == currentCategory ? new Color(255, 0, 0, 255).getRGB() : new Color(0, 0, 255, 190).getRGB());
            if (i == currentCategory) {
                if (mod) {
                    int modX = x + fontRendererObj.getStringWidth(Category.MOVEMENT.name());
                    List<Mod> byCategory = Client.modManager.getByCategory(Category.values()[currentCategory]);
                    int modY = index;
                    for (int j = 0; j < byCategory.size(); j++) {
                        Mod m = byCategory.get(j);

                        Gui.drawRect(modX, modY, modX + getModWidth(), modY + fontRendererObj.FONT_HEIGHT, j == currentMod ? new Color(255, 0, 0, 255).getRGB() : new Color(0, 117, 255, 153).getRGB());

                        fontRendererObj.drawString(m.getName(), modX, modY, m.isEnable() ? 0x22FF00 : 0xFF9900);
                        modY += fontRendererObj.FONT_HEIGHT;
                    }
                }
            }
            fontRendererObj.drawString(value.name(), x, index, 0xFFFFFFFF);
            index += fontRendererObj.FONT_HEIGHT;
        }
    }


    private int getModWidth() {
        FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
        List<Mod> byCategory = Client.modManager.getByCategory(Category.values()[currentCategory]);
        byCategory.sort((o1, o2) -> fontRendererObj.getStringWidth(o2.getName()) - fontRendererObj.getStringWidth(o1.getName()));
        return fontRendererObj.getStringWidth(byCategory.get(0).getName());
    }
}
