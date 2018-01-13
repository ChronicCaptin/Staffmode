package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.StafferManager;
import it.mr_replete.staff.events.VanishEvent;
import it.mr_replete.staff.reflection.Reflection;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.settings.StaffersItems;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VanishListener implements Listener {

    public VanishListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onvanish(VanishEvent e) throws Exception{

        Staffer staffer = e.getStaffer();

        if (staffer.isVanish()){
            staffer.setVanish(false);
            if (staffer.isStaffMode()) {
                staffer.getPlayer().getInventory().setItem(StaffersItems.VANISHOFF.getPosition() - 1, Settings.ITEM_VANISHOFF);
                staffer.getPlayer().updateInventory();
            }
            StafferManager.show(staffer.getPlayer());
            staffer.sendMessage(Settings.VANISH_OFFMSG);
        } else{
            staffer.setVanish(true);
           // Reflection.addPlayerFromReflection(e.getStaffer().getPlayer());
            if (staffer.isStaffMode()) {
                staffer.getPlayer().getInventory().setItem(StaffersItems.VANISHON.getPosition() - 1, Settings.ITEM_VANISHON);
                staffer.getPlayer().updateInventory();
            }
            staffer.sendMessage(Settings.VANISH_ONMSG);
            if (Settings.VANISH_SCSS){
                StafferManager.hideOnlyPlayers(staffer.getPlayer());
            } else{
                StafferManager.hide(staffer.getPlayer());
            }
        }
    }

}
