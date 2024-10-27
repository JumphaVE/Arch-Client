package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.Client;
import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModCPS extends Mod {

    private List<Long> clicks = new ArrayList<Long>();
    private boolean wasPressed;
    private long lastPressed;

    public int getWidth() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        return font.getStringWidth("CPS : 00");
    }

    public int getHeight() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        return font.FONT_HEIGHT;
    }

    public void draw() {
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        final boolean pressed = Mouse.isButtonDown(0);

        if(pressed != this.wasPressed) {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = pressed;
            if(pressed) {
                this.clicks.add(this.lastPressed);
            }
        }

        font.drawString("CPS: " + getCPS(), 450, 20, new Color(0, 140, 255).getRGB());

    }

    private int getCPS() {
        final long time = System.currentTimeMillis();
        this.clicks.removeIf(aLong -> aLong + 1000 < time);
        return this.clicks.size();
    }

    public ModCPS() {
        super("CPS", Category.DRAW);
    }
}
