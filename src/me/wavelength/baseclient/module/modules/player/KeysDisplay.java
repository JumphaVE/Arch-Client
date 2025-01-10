package me.wavelength.baseclient.module.modules.player;

import me.wavelength.baseclient.event.events.Render2DEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class KeysDisplay extends Module {
    private Render2DEvent event;

    public KeysDisplay() {
        super("KeysDisplay", "KeysDisplay", Keyboard.KEY_F, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
    }

    public void onRender2D(Render2DEvent event) {
        //w
        new KeyRenderer(Minecraft.getMinecraft().gameSettings.keyBindForward).render(35, 145);
        //a
        new KeyRenderer(Minecraft.getMinecraft().gameSettings.keyBindLeft).render(14, 170);
        //s
        new KeyRenderer(Minecraft.getMinecraft().gameSettings.keyBindBack).render(35 , 170);
        //d
        new KeyRenderer(Minecraft.getMinecraft().gameSettings.keyBindRight).render(56, 170);
        super.onRender2D(event);
    }
}

class KeyRenderer {
    public KeyBinding keyBinding;

    public KeyRenderer(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
    }

    public void render(int x,int y) {
        String key = Keyboard.getKeyName(keyBinding.getKeyCode());

        int background = new Color(0, 0, 0, 100).getRGB();
        int press = new Color(255, 255, 255, 190).getRGB();

        int textColor = keyBinding.isKeyDown() ? -1 : 1;
        int bgColor = keyBinding.isKeyDown() ? press : background;
        Gui.drawRect(x - 8, y - 8, x + 8 + Minecraft.getMinecraft().fontRendererObj.getStringWidth(key), y + 2 + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, bgColor);

        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(key, x, y, textColor);
    }
}