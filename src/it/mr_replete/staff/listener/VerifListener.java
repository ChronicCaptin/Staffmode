package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.InventoryUtil;
import it.mr_replete.staff.events.VerifEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VerifListener implements Listener {

    public VerifListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onverif(VerifEvent e){
        e.getStaffer().getPlayer().openInventory(InventoryUtil.getPlayerInventory(e.getTarget()));
    }

}
