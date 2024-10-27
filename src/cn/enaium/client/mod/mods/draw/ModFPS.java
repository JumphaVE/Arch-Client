package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.Client;
import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.WorldRenderer;

import java.awt.*;

import static net.minecraft.client.Minecraft.getDebugFPS;
import static net.minecraft.client.Minecraft.getGLMaximumTextureSize;

public class ModFPS extends Mod {

    private WorldRenderer pos;

    public int getWidth() {
        return 50;
    }

    public int getHeight() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        return font.FONT_HEIGHT;
    }

    public void draw() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        font.drawString("FPS: " + getDebugFPS(), 450, 0, new Color(255, 0, 0).getRGB());
    }

    public void save() {
        this.pos = pos;
    }

    public ModFPS() {
        super("FPS", Category.DRAW);
    }
}