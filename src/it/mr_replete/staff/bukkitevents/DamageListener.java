package it.mr_replete.staff.bukkitevents;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.FreezeUtil;
import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    public DamageListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void ondamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player && e.getEntity() instanceof  Player){
            if (FreezeUtil.isFrozen((Player)e.getEntity())) {
                e.setCancelled(true);
            } else if (FreezeUtil.isFrozen((Player)e.getDamager())){
                e.setCancelled(true);
            } else if (StafferUtil.isStaffer((Player)e.getEntity())){
                try{
                    Staffer staffer = StafferUtil.getStaffer(e.getEntity().getName());
                    if (staffer.isGod()){
                        e.setCancelled(true);
                    }
                } catch(StafferNotFoundException e2){
                    if (StafferUtil.isStaffer((Player)e.getEntity())) {
                        StafferUtil.fixUP(e.getEntity());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onanydamage(EntityDamageEvent e) throws StafferNotFoundException{
        if (e.getEntity() instanceof  Player){
            if (StafferUtil.isStaffer((Player)e.getEntity())){
                if (StafferUtil.getStaffer(e.getEntity().getName()).isGod()){
                    e.setCancelled(true);
                }
            }
        }
    }

}
