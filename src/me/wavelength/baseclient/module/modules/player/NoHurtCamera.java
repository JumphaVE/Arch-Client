package me.wavelength.baseclient.module.modules.player;

import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import org.lwjgl.input.Keyboard;

public class NoHurtCamera extends Module {
    public NoHurtCamera() {
        super("NoHurtCamera", "NoHurtCamera", Keyboard.KEY_F, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
    }
}
