package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * @author Enaium
 */
public class TargetInfoMod extends Mod {
    public TargetInfoMod() {
        super("TargetInfo", Category.DRAW);
    }

    @Override
    public void draw() {
        Entity entityHit = Minecraft.getMinecraft().objectMouseOver.entityHit;
        if (entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) entityHit;
            GuiInventory.drawEntityOnScreen(110, 100, 30, 0, 0, entity);
            Minecraft.getMinecraft().fontRendererObj.drawString(entity.getName(), 100, 100, 0xFFFFFFFF);
            Minecraft.getMinecraft().fontRendererObj.drawString(String.format("%s/%s", entity.getMaxHealth(), entity.getHealth()), 100, 110, 0xFFFFFFFF);
        }
    }
}
