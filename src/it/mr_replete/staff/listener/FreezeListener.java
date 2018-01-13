package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.FreezeUtil;
import it.mr_replete.staff.events.FreezeClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FreezeListener implements Listener {

    public FreezeListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onfreeze(FreezeClickEvent e){
        if (FreezeUtil.isFrozen(e.getTarget())){
            FreezeUtil.setFrozen(e.getTarget(), false, e.getStaffer().getPlayer());
        } else{
            FreezeUtil.setFrozen(e.getTarget(), true, e.getStaffer().getPlayer());
        }

    }

}
