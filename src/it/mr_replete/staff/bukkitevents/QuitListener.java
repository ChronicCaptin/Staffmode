package it.mr_replete.staff.bukkitevents;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.*;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class QuitListener implements Listener {

    public QuitListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onquit(PlayerQuitEvent e){

        if (StafferUtil.isStaffer(e.getPlayer())){
            Staffer staffer = null;
            try{
                staffer = StafferUtil.getStaffer(e.getPlayer().getName());
            } catch(StafferNotFoundException e2){
                return;
            }
            if (staffer != null){
                Staff.getInstance().getStaffers().remove(staffer);
                if (staffer.isStaffMode()) {
                    ItemsUtil.clear(e.getPlayer());
                }
            }

        } else if (FreezeUtil.isFrozen(e.getPlayer())){
            Staff.getInstance().getFrozen().remove(e.getPlayer().getUniqueId());
            Sender.sendMessage(Settings.FREEZE_SLOGMESSAGE.replace("%player%", e.getPlayer().getName()));

            if (Settings.FREEZE_BANONQUIT){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Settings.FREEZE_CMD.replace("%player%", e.getPlayer().getName()));
            }

        } else if (!StafferUtil.isStaffer(e.getPlayer()) && StafferUtil.contains(e.getPlayer().getUniqueId())){
            StafferManager.unregister(e.getPlayer().getUniqueId());
            ItemsUtil.clear(e.getPlayer());
        }

    }

}
