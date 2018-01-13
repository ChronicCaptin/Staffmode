package it.mr_replete.staff.bukkitevents;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.StafferManager;
import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;
import java.util.UUID;

public class JoinListener implements Listener {

    private String name;
    private UUID uuid;

    public JoinListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent e) throws IOException {
        StafferManager.fixUPHide(e.getPlayer());
        if (StafferUtil.isStaffer(e.getPlayer())){
            Staffer staffer = new StafferManager().registerStaffer(e.getPlayer().getName(), e.getPlayer().getUniqueId(), e.getPlayer());
            Staff.getInstance().getStaffers().add(staffer);
            staffer.getPlayer().setWalkSpeed(0.2f);
            staffer.getPlayer().setFlySpeed(0.1f);
        }

    }


}
