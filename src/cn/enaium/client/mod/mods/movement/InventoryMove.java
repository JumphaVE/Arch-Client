package cn.enaium.client.mod.mods.movement;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * @author Enaium
 */
public class InventoryMove extends Mod {
    public InventoryMove() {
        super("InventoryMove", Category.MOVEMENT);
    }

    @Override
    public void update() {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiChat)
            return;

        GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;
        KeyBinding[] keyBindings = {gameSettings.keyBindForward, gameSettings.keyBindBack, gameSettings.keyBindLeft, gameSettings.keyBindRight, gameSettings.keyBindJump, gameSettings.keyBindSprint, gameSettings.keyBindSneak};
        for (KeyBinding keyBinding : keyBindings) {
            KeyBinding.setKeyBindState(keyBinding.getKeyCode(), Keyboard.isKeyDown(keyBinding.getKeyCode()));
        }
    }
}
