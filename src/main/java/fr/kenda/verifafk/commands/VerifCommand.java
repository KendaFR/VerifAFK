package fr.kenda.verifafk.commands;

import fr.kenda.verifafk.VerifAFK;
import fr.kenda.verifafk.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class VerifCommand implements CommandExecutor {

    private final VerifAFK instance = VerifAFK.getInstance();
    private final HashMap<Player, Player> verifications = VerifAFK.getInstance().getVerifications();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Check si le sender est là console
        if (!(sender instanceof final Player player)) {
            sender.sendMessage(MessageUtils.getMessage("console_no_permission"));
            return false;
        }
        //Check si le joueur as la permission
        if (!player.hasPermission(MessageUtils.getPermission("verif"))) {
            player.sendMessage(MessageUtils.getMessage("no_permission"));
            return false;
        }

        //check si c'est uniquement 1 argument
        if (args.length != 1) {
            player.sendMessage(MessageUtils.getMessage("command_use"));
            return false;
        }

        //check si c'est /verif confirm
        if (args[0].equalsIgnoreCase("confirm")) {
            if (verifications.containsKey(player)) {
                final Player target = verifications.get(player);
                player.sendMessage(MessageUtils.getMessage("verification_confirm", "%target%", target.getName()));
                instance.getVerifications().remove(player);
                instance.getIsVerified().remove(target);
                return true;
            } else
                player.sendMessage(MessageUtils.getMessage("not_in_verification"));
            return false;
        }

        //check si c'est /verif help
        else if (args[0].equalsIgnoreCase("help")) {
            player.sendMessage(MessageUtils.getHelp());
            return true;
        }

        //récupère le target
        Player target = Bukkit.getPlayer(args[0]);
        //check si le target est pas null (donc connecté)
        if (target == null) {
            player.sendMessage(MessageUtils.getMessage("target_offline", "%target%", args[0]));
            return false;
        }
        if (target == player) {
            player.sendMessage(MessageUtils.getMessage("cannot_verif_yourself"));
            return false;
        }
        //check si le joueur est déjà en mode de vérification
        if (verifications.containsKey(player)) {
            player.sendMessage(MessageUtils.getMessage("already_in_verification", "%target%", verifications.get(player).getName()));
            return false;
        }
        instance.getVerifications().put(player, target);
        instance.getIsVerified().put(target, false);
        player.sendMessage(MessageUtils.getMessage("enter_verification", "%target%", target.getName()));
        target.sendMessage(MessageUtils.getMessage("verification_player"));
        target.playSound(target.getLocation(), MessageUtils.getSound(), 1, 1);
        target.sendTitle(MessageUtils.getMessageTitle("title_verif"), MessageUtils.getMessageTitle("subtitle_verif"), 20, Integer.MAX_VALUE, 20);

        return true;
    }
}
