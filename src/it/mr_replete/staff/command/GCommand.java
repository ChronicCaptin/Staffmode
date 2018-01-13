package it.mr_replete.staff.command;

import it.mr_replete.staff.Staff;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class GCommand {

    protected String label, perm;
    protected boolean user;
    protected boolean console;

    public GCommand(String label, boolean user) {
        this.label = label;
        this.user = user;

        Staff.getInstance().getCommand(label).setExecutor(new Executor());
        Staff.getInstance().commands.put(label, this);

        Command cmd =  Staff.getInstance().getCommand(label);

        for (String aliases : cmd.getAliases()) {
            Staff.getInstance().commands.put(aliases, this);
        }

    }

    public GCommand(String label, boolean user, String perm) {
        this.label = label;
        this.user = user;
        this.perm = perm;

        Staff.getInstance().getCommand(label).setExecutor(new Executor());
        Staff.getInstance().commands.put(label, this);

        Command cmd =  Staff.getInstance().getCommand(label);

        for (String aliases : cmd.getAliases()) {
            Staff.getInstance().commands.put(aliases, this);
        }

    }

    public GCommand(String label, boolean console, char key) {

        if (key == '$') {
            this.label = label;
            this.console = console;

            Staff.getInstance().getCommand(label).setExecutor(new Executor());
            Staff.getInstance().commands.put(label, this);

            Command cmd =  Staff.getInstance().getCommand(label);

            for (String aliases : cmd.getAliases()) {
                Staff.getInstance().commands.put(aliases, this);
            }

        }


    }

    public abstract void execute(CommandSender sender, String[] args, String label);

    public boolean isUser() {
        return user;
    }

    public String getPerm() {
        return perm;
    }

    public boolean isConsole() {
        return console;
    }

}
