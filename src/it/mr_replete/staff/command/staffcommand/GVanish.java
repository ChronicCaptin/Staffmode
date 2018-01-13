package it.mr_replete.staff.command.staffcommand;


import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.command.Toggle;
import it.mr_replete.staff.events.VanishEvent;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GVanish extends Toggle {

    public GVanish() {
        super("vanish", false, "staff.vansish");
    }

    @Override
    protected void toggle(Staffer staffer) {
        Bukkit.getPluginManager().callEvent(new VanishEvent(staffer));
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {
        try{
            Staffer staffer = StafferUtil.getStaffer(sender.getName());
            toggle(staffer);
        } catch(StafferNotFoundException e){
            if (StafferUtil.isStaffer((Player)sender)) {
                StafferUtil.fixUP(sender);
                execute(sender, args, label);
            }
        }
    }
}
