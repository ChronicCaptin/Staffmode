package it.mr_replete.staff.bukkitevents;

import it.mr_replete.staff.Staff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class TabListener implements Listener {

    public TabListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void oncomplete(PlayerChatTabCompleteEvent e){
       for (Player player : Bukkit.getOnlinePlayers()){
           if (player.getName().contains(e.getChatMessage())){
               e.getTabCompletions().add(player.getName());
           }
       }
    }
}
