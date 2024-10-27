package cn.enaium.client.config.configs;

import cn.enaium.client.Client;
import cn.enaium.client.config.Config;
import cn.enaium.client.mod.Mod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author Enaium
 */
public class ModConfig extends Config {
    public ModConfig() {
        super("Mod");
    }

    @Override
    public void load() {
        try {
            JsonObject jsonObject = new Gson().fromJson(new String(Files.readAllBytes(getPath()), StandardCharsets.UTF_8), JsonObject.class);
            for (Mod mod : Client.modManager.getMods()) {
                if (jsonObject.has(mod.getName())) {
                    JsonObject modJsonObject = jsonObject.get(mod.getName()).getAsJsonObject();
                    if (modJsonObject.has("enable")) {
                        mod.setEnable(modJsonObject.get("enable").getAsBoolean());
                    }

                    if (modJsonObject.has("key")) {
                        mod.setKey(modJsonObject.get("key").getAsInt());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {
        JsonObject jsonObject = new JsonObject();
        for (Mod mod : Client.modManager.getMods()) {
            JsonObject modJsonObject = new JsonObject();
            modJsonObject.addProperty("enable", mod.isEnable());
            modJsonObject.addProperty("key", mod.getKey());
            jsonObject.add(mod.getName(), modJsonObject);
        }
        try {
            Files.write(getPath(), new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
