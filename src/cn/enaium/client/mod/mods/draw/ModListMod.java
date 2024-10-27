package cn.enaium.client.mod.mods.draw;

import cn.enaium.client.Client;
import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * @author Enaium
 */
public class ModListMod extends Mod {
    public ModListMod() {
        super("ModList", Category.DRAW);
        setKey(Keyboard.KEY_O);
    }

    @Override
    public void draw() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        List<Mod> enableMods = Client.modManager.getEnableMods();
        FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        enableMods.sort((o1, o2) -> font.getStringWidth(o2.getName()) - font.getStringWidth(o1.getName()));

        int y = 0;
        for (Mod enableMod : enableMods) {
            font.drawString(enableMod.getName(), width - font.getStringWidth(enableMod.getName()), y, 0xFFFFFFFF);
            y += font.FONT_HEIGHT + 5;
        }
    }
}
