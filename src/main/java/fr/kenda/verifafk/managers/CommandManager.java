package fr.kenda.verifafk.managers;

import fr.kenda.verifafk.VerifAFK;
import fr.kenda.verifafk.commands.VerifCommand;

public class CommandManager implements IManager {

    private static final VerifAFK instance = VerifAFK.getInstance();

    @SuppressWarnings("all")
    @Override
    public void register() {
        instance.getCommand("verif").setExecutor(new VerifCommand());
    }
}
