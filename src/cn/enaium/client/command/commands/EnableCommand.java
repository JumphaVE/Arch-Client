package cn.enaium.client.command.commands;

import cn.enaium.client.Client;
import cn.enaium.client.command.Command;
import cn.enaium.client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

/**
 * @author Enaium
 */
public class EnableCommand extends Command {
    public EnableCommand() {
        super(new String[]{"e", "enable"});
    }

    @Override
    public void run(String[] args) {
        if (args.length == 1) {
            Mod byName = Client.modManager.getByName(args[0]);
            if (byName != null) {
                byName.setEnable(!byName.isEnable());
            } else {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Mod " + args[0] + " not found"));
            }
        }
    }
}
