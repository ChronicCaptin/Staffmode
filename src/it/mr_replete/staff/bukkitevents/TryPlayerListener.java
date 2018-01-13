package it.mr_replete.staff.bukkitevents;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.FreezeUtil;
import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class TryPlayerListener implements Listener {

    public TryPlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onclose(InventoryCloseEvent e){
        if (e.getInventory().equals(Settings.FREEZE_INV)){
            Bukkit.getScheduler().scheduleSyncDelayedTask(Staff.getInstance(), new BukkitRunnable() {
                @Override
                public void run() {
                    if (FreezeUtil.isFrozen((Player)e.getPlayer())){
                        e.getPlayer().openInventory(Settings.FREEZE_INV);
                    } else{
                        e.getPlayer().closeInventory();
                    }
                }
            },1L);
        }
    }

    @EventHandler
    public void onchange(FoodLevelChangeEvent e) throws StafferNotFoundException{
        if (StafferUtil.isStaffer((Player)e.getEntity())){
            if (StafferUtil.getStaffer(e.getEntity().getName()).isStaffMode()){
                e.setCancelled(true);
            }
        }
    }

}
