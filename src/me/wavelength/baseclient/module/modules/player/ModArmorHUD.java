package me.wavelength.baseclient.module.modules.player;

import me.wavelength.baseclient.event.events.Render2DEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import me.wavelength.baseclient.thealtening.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class ModArmorHUD extends Module {
    public ModArmorHUD() {
        super("ModArmorHUD", "ModArmorHUD", Keyboard.KEY_F, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
    }

    @Override
    public void render(Render2DEvent event) {
        for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
            ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
            renderItemStack(i, itemStack);
        }
    }

    public void onRender2D(Render2DEvent event) {
        if (!Utilities.getInstance().getUnregisteredRenderers().contains(this)) {
            renderItemStack(3, new ItemStack(Items.diamond_helmet));
            renderItemStack(2, new ItemStack(Items.diamond_chestplate));
            renderItemStack(1, new ItemStack(Items.diamond_leggings));
            renderItemStack(0, new ItemStack(Items.diamond_boots));
        }
    }



    private void renderItemStack(int i, ItemStack itemStack) {
        if(itemStack == null) {
            return;
        }
        int yAdd = (-16 * i) + 48;

        GL11.glPushMatrix();

        if (itemStack.getItem().isDamageable()) {
            double damage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage() * 100);
            Minecraft.getMinecraft().fontRendererObj.drawString(String.format("%.2f%%", damage), 0 + 20, 0 + yAdd + 5, -1);
        }

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, 0, 0 + yAdd);
        GL11.glPopMatrix();
    }

}
