package cn.enaium.client.command.commands;

import cn.enaium.client.command.Command;

import java.util.Arrays;

/**
 * @author Enaium
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super(new String[]{"h", "help"});
    }

    @Override
    public void run(String[] args) {
        System.out.println(Arrays.toString(args));
    }
}
