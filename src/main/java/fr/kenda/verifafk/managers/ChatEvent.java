package fr.kenda.verifafk.managers;

import fr.kenda.verifafk.VerifAFK;
import fr.kenda.verifafk.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final VerifAFK instance = VerifAFK.getInstance();
        final HashMap<Player, Boolean> verified = instance.getIsVerified();
        final HashMap<Player, Player> verifications = instance.getVerifications();

        Player target = e.getPlayer();

        if (verifications.containsValue(target) && verified.containsKey(target) && !verified.get(target)) {
            e.setCancelled(true);
            Player player = instance.getPlayerBy(target);
            if (player != null) {
                instance.getIsVerified().put(target, true);
                player.sendMessage(MessageUtils.getMessage("verification_end", "%target%", target.getName()));
                target.sendMessage(MessageUtils.getMessage("verification_passed"));
                target.sendTitle(MessageUtils.getMessageTitle("title_end"), MessageUtils.getMessageTitle("subtitle_end"), 20, 60, 20);
            }
        }
    }
}
