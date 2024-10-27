package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import cn.enaium.client.util.DrawUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.GameSettings;

import java.awt.*;

/**
 * @author Enaium
 */
public class KeyBoard extends Mod {
    public KeyBoard() {
        super("KeyBoard", Category.DRAW);
    }

    @Override
    public void draw() {
        drawKey(0, 100);
    }

    public void drawKey(int x, int y) {
        int background = new Color(0, 0, 0, 100).getRGB();
        int press = new Color(255, 255, 255, 190).getRGB();


        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings gameSettings = minecraft.gameSettings;
        FontRenderer fontRendererObj = minecraft.fontRendererObj;

        DrawUtil.drawRect(x + 25, y, 25, 25, gameSettings.keyBindForward.isKeyDown() ? press : background);
        fontRendererObj.drawString("W", x + 25 + 10, y + 10, 0xFFFFFFFF);
        DrawUtil.drawRect(x + 25, y + 25, 25, 25, gameSettings.keyBindBack.isKeyDown() ? press : background);
        fontRendererObj.drawString("S", x + 25 + 10, y + 25 + 10, 0xFFFFFFFF);
        DrawUtil.drawRect(x, y + 25, 25, 25, gameSettings.keyBindLeft.isKeyDown() ? press : background);
        fontRendererObj.drawString("A", x + 10, y + 25 + 10, 0xFFFFFFFF);
        DrawUtil.drawRect(x + 50, y + 25, 25, 25, gameSettings.keyBindRight.isKeyDown() ? press : background);
        fontRendererObj.drawString("D", x + 50 + 10, y + 25 + 10, 0xFFFFFFFF);
    }
}
