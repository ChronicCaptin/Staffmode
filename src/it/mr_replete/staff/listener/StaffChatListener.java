package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.Sender;
import it.mr_replete.staff.events.StaffChatEvent;
import it.mr_replete.staff.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class StaffChatListener implements Listener {

    public StaffChatListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onstaffchat(StaffChatEvent e){
        Sender.sendMessage(Settings.STAFFCHAT_FORMAT.replace("%player%", e.getStaffer().getName()).replace("%message%", e.getMessage().replace("@", "")));
    }

}
