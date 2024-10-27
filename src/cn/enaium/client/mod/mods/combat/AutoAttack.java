package cn.enaium.client.mod.mods.combat;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Keyboard;

/**
 * @author Enaium
 */
public class AutoAttack extends Mod {
    public AutoAttack() {
        super("AutoAttack", Category.COMBAT);
        setKey(Keyboard.KEY_R);
    }

    @Override
    public void update() {
        for (Entity entity : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (entity instanceof EntityLivingBase) {
                EntityLivingBase livingBase = (EntityLivingBase) entity;
                if (!livingBase.equals(Minecraft.getMinecraft().thePlayer) && !(livingBase.getHealth() <= 0) && !livingBase.isDead && !livingBase.isInvisible() && livingBase.getDistanceToEntity(Minecraft.getMinecraft().thePlayer) <= 3.8) {
                    Minecraft.getMinecraft().thePlayer.swingItem();
                    Minecraft.getMinecraft().playerController.attackEntity(Minecraft.getMinecraft().thePlayer, livingBase);
                }
            }
        }
    }
}
