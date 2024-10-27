package cn.enaium.client.mod.mods.movement;

import cn.enaium.client.mod.Category;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

/**
 * @author Enaium
 * @since 1.0
 */
public class NoFall extends Mod {
    public NoFall() {
        super("NoFall", Category.MOVEMENT);
    }

    @Override
    public void update() {
        if (Minecraft.getMinecraft().thePlayer.fallDistance > 0.5) {
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C03PacketPlayer(true));
        }
    }
}
