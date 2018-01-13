package it.mr_replete.staff.command;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.settings.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (Staff.getInstance().commands.containsKey(label)) {
            GCommand command = Staff.getInstance().commands.get(label);
            if (command.isConsole() && sender instanceof Player) {
               //TODO : SEND MESSAGE { EXECUTE FROM CONSOLE }
            } else if (command.isConsole() && !(sender instanceof Player)) {
                command.execute(sender, args, label);
            }
            else if (!command.isUser() && !sender.hasPermission(command.getPerm())) {
                sender.sendMessage(Settings.NOPERMISSION);
                return true;

            } else if (!command.isUser() && sender.hasPermission(command.getPerm())) {
                command.execute(sender, args, label);
            } else if (command.isUser()) {
                command.execute(sender, args, label);
            }

        }

        return true;
    }

}

