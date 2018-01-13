package it.mr_replete.staff.Util;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FreezeUtil {

    public static boolean isFrozen(Player player){
        return Staff.getInstance().getFrozen().contains(player.getUniqueId());
    }

    public static void setFrozen(Player player, boolean value, Player staffer){
        if (value){
            player.openInventory(Settings.FREEZE_INV);
            player.sendMessage(Settings.FREEZE_MESSAGE.replace("%staffer%", staffer.getName()));
            staffer.sendMessage(Settings.FREEZE_STAFF_MESSAGE.replace("%player%", player.getName()));
            Staff.getInstance().getFrozen().add(player.getUniqueId());
        } else if (isFrozen(player) && !(value)){
            player.closeInventory();
            player.sendMessage(Settings.UNFREEZE_MESSAGE.replace("%staffer%", staffer.getName()));
            staffer.sendMessage(Settings.UNFREEZE_STAFF_MESSAGE.replace("%player%", player.getName()));
            Staff.getInstance().getFrozen().remove(player.getUniqueId());
        }

    }

}
