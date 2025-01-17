package me.wavelength.baseclient.module.modules.player;

import me.wavelength.baseclient.event.events.Render2DEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import me.wavelength.baseclient.thealtening.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmorStand;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class ModArmorHUD extends Module {
    public ModArmorHUD() {
        super("ModArmorHUD", "ModArmorHUD", Keyboard.KEY_F, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
    }

    @Override
    public void render(Render2DEvent pos) {
        for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
            renderItemStack(pos ,i, itemStack);
        }
    }

    public void onRender2D(Render2DEvent pos) {
        if (!Utilities.getInstance().getUnregisteredRenderers().contains(this)) {
            renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
            renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
            renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
            renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
        }
    }



    private void renderItemStack(Render2DEvent pos, int i, ItemStack itemStack) {
        if(itemStack == null) {
            return;
        }
        int yAdd = (-16 * i) + 48;

        GL11.glPushMatrix();

        if (itemStack.getItem().isDamageable()) {
            double damage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage() * 100);
            Minecraft.getMinecraft().fontRendererObj.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, -1);
        }

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
        GL11.glPopMatrix();
    }

}
