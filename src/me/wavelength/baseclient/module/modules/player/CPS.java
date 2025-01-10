package me.wavelength.baseclient.module.modules.player;

import me.wavelength.baseclient.event.events.Render2DEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CPS extends Module {

    public CPS() {
        super("CPS", "CPS", Keyboard.KEY_F, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
    }

    private List<Long> clicks = new ArrayList<Long>();
    private boolean wasPressed;
    private long lastPressed;

    public void onRender2D(Render2DEvent event) {
        final boolean pressed = Mouse.isButtonDown(0);

        if (pressed != this.wasPressed) {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = pressed;
            if (pressed) {
                this.clicks.add(this.lastPressed);
            }
        }

        int p = new Color(255, 0, 0, 200).getRGB();

        new Keyrender(Minecraft.getMinecraft().fontRendererObj.drawString("CPS :" + getCPS(), 455, 250, p));
    }

    private int getCPS() {
        final long time = System.currentTimeMillis();
        this.clicks.removeIf(aLong -> aLong + 1000 < time);
        return this.clicks.size();
    }

    class Keyrender {

        public Keyrender(int i) {
        }
    }
}


