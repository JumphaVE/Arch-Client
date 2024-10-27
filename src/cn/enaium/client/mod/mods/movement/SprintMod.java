package cn.enaium.client.mod.mods.movement;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import cn.enaium.client.setting.EnableSetting;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

/**
 * @author Enaium
 */
public class SprintMod extends Mod {

    private final EnableSetting setting = new EnableSetting("setting1", true);

    public SprintMod() {
        super("Sprint", Category.MOVEMENT);
        setKey(Keyboard.KEY_V);
        getSetting().add(setting);
    }


    @Override
    public void enable() {
        System.out.println(getSettingByName("setting1").getValue());
    }

    @Override
    public void update() {
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()
                || Minecraft.getMinecraft().gameSettings.keyBindLeft.isKeyDown()
                || Minecraft.getMinecraft().gameSettings.keyBindRight.isKeyDown()) {
            if (!Minecraft.getMinecraft().thePlayer.isInWater())
                Minecraft.getMinecraft().thePlayer.setSprinting(true);
        }
    }
}
