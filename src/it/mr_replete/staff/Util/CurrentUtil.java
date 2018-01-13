package it.mr_replete.staff.Util;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class CurrentUtil {

    public static Inventory getCurrentStaffInventory() throws StafferNotFoundException{

        StafferManager.checkAndFix();

        Inventory inventory = Bukkit.createInventory(null, 54, Settings.CURRENT_STAFFERS_INVENTORY_TITLE.replace("%staffcount%", String.valueOf(Staff.getInstance().getStaffers().size())));
        int index = 0;

        for (Staffer staffers : Staff.getInstance().getStaffers()){
            inventory.setItem(index, ItemsUtil.createHead(ChatColor.AQUA + staffers.getName(), staffers.getName(), 1));
            ++index;
        }

        return inventory;

    }

}
