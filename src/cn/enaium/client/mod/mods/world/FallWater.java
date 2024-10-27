package cn.enaium.client.mod.mods.world;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author Enaium
 */
public class FallWater extends Mod {
    public FallWater() {
        super("FallWater", Category.WORLD);
    }

    @Override
    public void update() {
        Minecraft minecraft = Minecraft.getMinecraft();
        ItemStack currentItem = minecraft.thePlayer.inventory.getCurrentItem();
        if (currentItem != null) {
            int idFromItem = Item.getIdFromItem(currentItem.getItem());
            if (idFromItem == 326 && minecraft.thePlayer.fallDistance > 4
                    && minecraft.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK
                    && minecraft.objectMouseOver.sideHit.getIndex() == 1) {
                minecraft.rightClickMouse();
            }
        }
    }
}
