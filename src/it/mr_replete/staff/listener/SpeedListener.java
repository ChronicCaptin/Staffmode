package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.InventoryUtil;
import it.mr_replete.staff.events.SpeedClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SpeedListener implements Listener {

    public SpeedListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onspeedclick(SpeedClickEvent e){
        e.getStaffer().getPlayer().openInventory(InventoryUtil.getSpeedInventory());
    }

}
