package fr.kenda.verifafk.utils;

import fr.kenda.verifafk.VerifAFK;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.util.List;

@SuppressWarnings("all")
public class MessageUtils {

    private static final VerifAFK main = VerifAFK.getInstance();

    public static String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("prefix"));
    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("messages." + path).replace("%prefix%", getPrefix()));
    }

    public static String getPermission(String path) {
        return ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("permissions." + path).replace("%prefix%", getPrefix()));
    }

    public static String getMessage(String path, String... args) {
        String message = main.getConfig().getString("messages." + path);
        for (int i = 0; i < args.length; i += 2)
            message = message.replace(args[i], args[i + 1]);
        return ChatColor.translateAlternateColorCodes('&', message).replace("%prefix%", getPrefix());
    }

    public static String getMessageTitle(String path) {
        return ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("title." + path).replace("%prefix%", getPrefix()));
    }

    public static String getHelp() {
        final List<String> lines = main.getConfig().getStringList("messages.help");
        String msg = "";
        for (String line : lines) {
            msg += ChatColor.translateAlternateColorCodes('&', line).replace("%prefix%", getPrefix()) + "\n";
        }
        return msg;
    }

    public static Sound getSound() {
        return Sound.valueOf(main.getConfig().getString("sound.alert").toUpperCase().replace(" ", "_"));
    }
}