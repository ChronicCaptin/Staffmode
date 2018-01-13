package it.mr_replete.staff.command.staffcommand;

import it.mr_replete.staff.Util.RandomUtil;
import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.command.Toggle;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GGod extends Toggle {

    public GGod() {
        super("god", false, "staff.god");
    }

    @Override
    protected void toggle(Staffer staffer) {
       staffer.setGod(staffer.isGod() ? false : true);
       staffer.sendMessage((staffer.isGod() ? Settings.GOD_MSGON : Settings.GOD_MSGOFF));
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {
        try {
            Staffer staffer = StafferUtil.getStaffer(sender.getName());
            toggle(staffer);
        } catch(StafferNotFoundException e){
            if (StafferUtil.isStaffer((Player)sender)) {
                StafferUtil.fixUP(sender);
                execute(sender,args,label);
            }
        }

    }
}
