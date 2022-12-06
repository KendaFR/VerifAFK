package fr.kenda.verifafk.managers;

import fr.kenda.verifafk.VerifAFK;
import fr.kenda.verifafk.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class MoveEvent implements Listener {

    final VerifAFK instance = VerifAFK.getInstance();
    final HashMap<Player, Boolean> verified = instance.getIsVerified();
    final HashMap<Player, Player> verifications = instance.getVerifications();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player target = e.getPlayer();
        if (!verified.containsKey(target) || !verifications.containsValue(target)) return;
        if (!verified.get(target)) {
            instance.getIsVerified().put(target, true);
            final Player player = instance.getPlayerBy(target);
            if (player != null) {
                player.sendMessage(MessageUtils.getMessage("verification_end", "%target%", target.getName()));
                target.sendMessage(MessageUtils.getMessage("verification_passed"));
                target.sendTitle(MessageUtils.getMessageTitle("title_end"), MessageUtils.getMessageTitle("subtitle_end"), 20, 60, 20);
            }
        }
    }
}
