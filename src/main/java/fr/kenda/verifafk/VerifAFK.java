package fr.kenda.verifafk;

import fr.kenda.verifafk.managers.CommandManager;
import fr.kenda.verifafk.managers.EventManager;
import fr.kenda.verifafk.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class VerifAFK extends JavaPlugin {

    private static VerifAFK instance;
    public String prefix = "";
    HashMap<Player, Player> verifications = new HashMap<>();
    HashMap<Player, Boolean> verifieds = new HashMap<>();

    public static VerifAFK getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        prefix = getConfig().getString("prefix");
        getLogger().info("plugin loaded");
        new CommandManager().register();
        new EventManager().register();


    }

    @Override
    public void onDisable() {
        getLogger().info("plugin stopped");
    }

    public HashMap<Player, Player> getVerifications() {
        return verifications;
    }

    public HashMap<Player, Boolean> getIsVerified() {
        return verifieds;
    }

    public Player getPlayerBy(Player target) {
        for (Map.Entry<Player, Player> p : verifications.entrySet()) {
            if (p.getValue() == target)
                return p.getKey();
        }
        return null;
    }
}
