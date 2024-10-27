package cn.enaium.client.mod.mods.world;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;

/**
 * @author Enaium
 */
public class FastPlace extends Mod {
    public FastPlace() {
        super("FastPlace", Category.WORLD);
    }

    @Override
    public void update() {
        if (Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem() != null) {
            if (Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
                Minecraft.getMinecraft().rightClickDelayTimer = 0;
            } else {
                Minecraft.getMinecraft().rightClickDelayTimer = 4;
            }
        }
    }
}
