package me.wavelength.baseclient.module.modules.player;

import me.wavelength.baseclient.event.events.Render2DEvent;
import me.wavelength.baseclient.module.AntiCheat;
import me.wavelength.baseclient.module.Category;
import me.wavelength.baseclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.Collection;

public class ModPotionHUD extends Module {
    public ModPotionHUD() {
        super("ModPotionHUD", "ModPotionHUD", Keyboard.KEY_F, Category.PLAYER, AntiCheat.VANILLA, AntiCheat.AAC);
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        int i = 80;
        int i2 = 16;
        Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();

        if (!collection.isEmpty())
        {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            int l = 33;

            if (collection.size() > 5)
            {
                l = 132 / (collection.size() - 1);
            }

            for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects())
            {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.2", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 2)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.3", new Object[0]);
                }
                else if (potioneffect.getAmplifier() == 3)
                {
                    s1 = s1 + " " + I18n.format("enchantment.level.4", new Object[0]);
                }
                Minecraft.getMinecraft().fontRendererObj.drawString(s1, 10, 250 + i2 - 30, Color.WHITE.getRGB(), true);
                String s = Potion.getDurationString(potioneffect);
                Minecraft.getMinecraft().fontRendererObj.drawString(s, 10, 250 + i2 + 10 - 30, 8355711, true);
                i2 += l;
            }
        }
    }

    public void render() {
        Minecraft.getMinecraft().fontRendererObj.drawString("Speed", 10, 120, Color.WHITE.getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawString("3:00", 10, 180 + 10, Color.WHITE.getRGB());
    }

}
