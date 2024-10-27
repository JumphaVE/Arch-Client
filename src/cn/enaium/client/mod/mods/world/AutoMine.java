package cn.enaium.client.mod.mods.world;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author Enaium
 */
public class AutoMine extends Mod {
    public AutoMine() {
        super("AutoMine", Category.WORLD);
    }

    @Override
    public void update() {
        Minecraft minecraft = Minecraft.getMinecraft();
        int keyCode = minecraft.gameSettings.keyBindAttack.getKeyCode();
        if (minecraft.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && minecraft.theWorld.getBlockState(minecraft.objectMouseOver.getBlockPos()).getBlock() != Blocks.air) {
            KeyBinding.setKeyBindState(keyCode, true);
        }
    }

    @Override
    public void disable() {
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
    }
}
