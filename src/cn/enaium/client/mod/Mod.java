package cn.enaium.client.mod;

import cn.enaium.client.setting.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Enaium
 */
public class Mod {
    private final String name;
    private final Category category;
    private boolean enable;
    private int key;

    private final List<Setting<?>> setting = new ArrayList<>();

    public Mod(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;

        if (enable) {
            enable();
        } else {
            disable();
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Category getCategory() {
        return category;
    }

    public List<Setting<?>> getSetting() {
        return setting;
    }

    public Setting<?> getSettingByName(String name) {
        for (Setting<?> setting : setting) {
            if (setting.getName().equalsIgnoreCase(name)) {
                return setting;
            }
        }
        return null;
    }

    public void enable() {

    }

    public void disable() {

    }

    public void draw() {

    }

    public void render(float partialTicks) {

    }

    public void update() {

    }

    public void key(int key) {

    }
}
