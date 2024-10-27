package cn.enaium.client.util;

import net.minecraft.client.gui.Gui;

/**
 * @author Enaium
 */
public class DrawUtil {
    public static void drawRect(int x, int y, int width, int height, int color) {
        Gui.drawRect(x, y, x + width, y + height, color);
    }
}
