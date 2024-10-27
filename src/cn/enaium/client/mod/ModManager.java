package cn.enaium.client.mod;

import cn.enaium.client.mod.mods.combat.AutoAttack;
import cn.enaium.client.mod.mods.combat.HitBox;
import cn.enaium.client.mod.mods.draw.*;
import cn.enaium.client.mod.mods.movement.InventoryMove;
import cn.enaium.client.mod.mods.movement.NoFall;
import cn.enaium.client.mod.mods.movement.SprintMod;
import cn.enaium.client.mod.mods.render.*;
import cn.enaium.client.mod.mods.world.AutoMine;
import cn.enaium.client.mod.mods.world.FallWater;
import cn.enaium.client.mod.mods.world.FastPlace;
import cn.enaium.client.mod.mods.world.XRay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Enaium
 */
public class ModManager {
    private final List<Mod> mods = new ArrayList<>();

    public List<Mod> getMods() {
        return mods;
    }

    public List<Mod> getEnableMods() {
        return mods.stream().filter(Mod::isEnable).collect(Collectors.toList());
    }

    public void onKey(int key) {
        for (Mod enableMod : mods) {
            if (enableMod.getKey() == key) {
                enableMod.setEnable(!enableMod.isEnable());
            }
        }
    }

    public void load() {
        mods.add(new LogoMod());
        mods.add(new SprintMod());
        mods.add(new ModListMod());
        mods.add(new RenderHitBoxMod());
        mods.add(new TabMod());
        mods.add(new AutoAttack());
        mods.add(new QuickTab());
        mods.add(new TargetInfoMod());
        mods.add(new FastPlace());
        mods.add(new KeyBoard());
        mods.add(new InventoryMove());
        mods.add(new FallWater());
        mods.add(new XRay());
        mods.add(new AutoMine());
        mods.add(new ClickGUI());
        mods.add(new CapeMod());
        mods.add(new HitBox());
        mods.add(new SelectBlock());
        mods.add(new NoFall());
        mods.add(new NoHurtCamera());
        mods.add(new NameLabel());
        mods.add(new ModFPS());
        mods.add(new ModCPS());
    }

    public Mod getByName(String name) {
        for (Mod mod : mods) {
            if (name.equalsIgnoreCase(mod.getName())) {
                return mod;
            }
        }
        return null;
    }

    public Mod getByClass(Class<? extends Mod> modClass) {
        for (Mod mod : mods) {
            if (mod.getClass() == modClass) {
                return mod;
            }
        }
        return null;
    }

    public List<Mod> getByCategory(Category category) {
        return mods.stream().filter(m -> m.getCategory() == category).collect(Collectors.toList());
    }
}
