package fr.kenda.verifafk.managers;

import fr.kenda.verifafk.VerifAFK;
import org.bukkit.Bukkit;

public class EventManager implements IManager {
    @Override
    public void register() {
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), VerifAFK.getInstance());
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), VerifAFK.getInstance());
    }
}
