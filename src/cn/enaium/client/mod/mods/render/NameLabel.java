package cn.enaium.client.mod.mods.render;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import cn.enaium.client.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * @author Enaium
 * @since 1.0
 */
public class NameLabel extends Mod {
    public NameLabel() {
        super("NameLabel", Category.RENDER);
    }

    @Override
    public void render(float partialTicks) {
        for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (entity instanceof EntityLivingBase) {
                if (entity instanceof EntityPlayerSP) {
                    continue;
                }
                EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
                RenderUtil.renderLabel(entityLivingBase, entityLivingBase.getDisplayName().getFormattedText() + " " + String.format("%s/%s", entityLivingBase.getMaxHealth(), entityLivingBase.getHealth()), 64, partialTicks);
            }
        }
    }
}
