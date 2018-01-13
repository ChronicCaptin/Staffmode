package it.mr_replete.staff.Util;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class StafferUtil {


    public static boolean isStaffer(Player player){
        return player.hasPermission(Settings.stafferpermission);
    }

    public static Staffer getStaffer(String name) throws StafferNotFoundException {
        Staffer staffer = null;
        for (Staffer staffers : Staff.getInstance().getStaffers()){
            if (staffers.getName().equals(name)){
                return staffers;
            }
        }
        if (staffer == null){
            throw new StafferNotFoundException();
        }
        return staffer;
    }

    public static void fixUP(CommandSender sender){
        if (sender instanceof Player){
            if (StafferUtil.isStaffer((Player)sender)){
                Staffer staffer = new StafferManager().registerStaffer(sender.getName(), ((Player) sender).getUniqueId(),(Player)sender);
                staffer.setVanish(false);
                staffer.setStaffMode(false);
                StafferManager.show(staffer.getPlayer());
                Staff.getInstance().getStaffers().add(staffer);
            }
        }
    }

    public static boolean contains(UUID uuid){
        for (Staffer staffer : Staff.getInstance().getStaffers()){
            if (staffer.getUUID().equals(uuid)){
                return true;
            }
        }
        return false;
    }

    public static Staffer getStafferWithoutControl(UUID uuid){
        if (contains(uuid)){
            for (Staffer staffer : Staff.getInstance().getStaffers()){
                if (staffer.getUUID().equals(uuid)){
                    return staffer;
                }
            }
        }
        return null;
    }

}
