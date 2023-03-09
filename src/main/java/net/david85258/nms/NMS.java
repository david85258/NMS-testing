package net.david85258.nms;

import net.david85258.nms.commands.SpawnCustomEntity;
import org.bukkit.plugin.java.JavaPlugin;

public final class NMS extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("spawncustomentity").setExecutor(new SpawnCustomEntity());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
