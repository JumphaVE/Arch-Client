package cn.enaium.client.mod.mods.world;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;

/**
 * @author Enaium
 */
public class XRay extends Mod {
    public XRay() {
        super("XRay", Category.WORLD);
    }

    @Override
    public void enable() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
    }

    @Override
    public void disable() {
        Minecraft.getMinecraft().renderGlobal.loadRenderers();
    }
}
