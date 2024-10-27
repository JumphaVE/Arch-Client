package cn.enaium.client.mod.mods.render;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Enaium
 */
public class RenderHitBoxMod extends Mod {
    public RenderHitBoxMod() {
        super("HitBox", Category.RENDER);
    }

    @Override
    public void render(float partialTicks) {
        for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {

            double renderPosX = Minecraft.getMinecraft().getRenderManager().renderPosX;
            double renderPosY = Minecraft.getMinecraft().getRenderManager().renderPosY;
            double renderPosZ = Minecraft.getMinecraft().getRenderManager().renderPosZ;
            if (entity instanceof EntityLivingBase) {
                if (!(entity instanceof EntityPlayerSP)) {
                    AxisAlignedBB entityBoundingBox = entity.getEntityBoundingBox();
                    RenderGlobal.func_181563_a(new AxisAlignedBB(
                            entityBoundingBox.minX - renderPosX,
                            entityBoundingBox.minY - renderPosY,
                            entityBoundingBox.minZ - renderPosZ,
                            entityBoundingBox.maxX - renderPosX,
                            entityBoundingBox.maxY - renderPosY,
                            entityBoundingBox.maxZ - renderPosZ
                    ), 255, 255, 255, 255);
                }
            }
        }
    }
}
