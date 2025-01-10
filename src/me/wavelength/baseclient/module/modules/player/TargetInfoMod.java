package me.wavelength.baseclient.module.modules.player;

import me.wavelength.baseclient.event.events.Render2DEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Keyboard;

public class TargetInfoMod extends Module {
    public TargetInfoMod() {
        super("TargetInfoMod", "TargetInfoMod", Keyboard.KEY_F, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        Entity entityHit = Minecraft.getMinecraft().objectMouseOver.entityHit;
        if (entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) entityHit;
            GuiInventory.drawEntityOnScreen(110, 100, 30, 0, 0, entity);
            Minecraft.getMinecraft().fontRendererObj.drawString(entity.getName(), 100, 100, 0xFFFFFFFF);
            Minecraft.getMinecraft().fontRendererObj.drawString(String.format("%s/%s", entity.getMaxHealth(), entity.getHealth()), 100, 110, 0xFFFFFFFF);
        }
    }

}
