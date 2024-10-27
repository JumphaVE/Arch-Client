package cn.enaium.client.config;

import cn.enaium.client.config.configs.ModConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Enaium
 */
public class ConfigManager {
    private final List<Config> configs = new ArrayList<Config>() {
        {
            add(new ModConfig());
        }
    };

    public void load() {
        for (Config config : configs) {
            if (config.getPath().toFile().exists()) {
                config.load();
            }
        }
    }

    public void save() {
        for (Config config : configs) {

            if (!config.getPath().toFile().exists()) {
                try {
                    Files.createDirectories(config.getPath().getParent());
                    config.getPath().toFile().createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            config.save();
        }
    }
}
