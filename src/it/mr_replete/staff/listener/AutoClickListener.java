package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.Sender;
import it.mr_replete.staff.cpscounter.CPSCounter;
import it.mr_replete.staff.events.AutoClickEvent;
import it.mr_replete.staff.reflection.Reflection;
import it.mr_replete.staff.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AutoClickListener implements Listener {

    public AutoClickListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onautoclick(AutoClickEvent e){
        if (!Settings.AUTOCLICK_ACTIVE){
            e.setCancelled(true);
            return;
        }
        switch (e.getTimes()){
            case 1:
                Sender.sendMessage(Settings.AUTOCLICK_MSG.replace("%player%", e.getPlayer().getName()).replace("%ping%", String.valueOf(Reflection.getPlayerPing(e.getPlayer()))).replace("%clicks%", String.valueOf(e.getClicks())));
                break;
            case 50:
                CPSCounter.getTimes().put(e.getPlayer().getUniqueId(), 0);
                break;
            default:
                e.setCancelled(true);
                break;
        }
    }

}
