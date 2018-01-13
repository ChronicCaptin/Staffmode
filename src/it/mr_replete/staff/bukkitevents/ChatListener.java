package it.mr_replete.staff.bukkitevents;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.events.StaffChatEvent;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    public ChatListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onchat(AsyncPlayerChatEvent e){

        for (Staffer staffer : Staff.getInstance().getStaffers()){
            if (e.getMessage().contains(staffer.getName())){
                for (String array : e.getMessage().split(" ")){
                    if (array.equals(staffer.getName()) && !e.getPlayer().getName().equals(staffer.getName())){
                        staffer.sendMessage(ChatColor.AQUA + e.getPlayer().getName() + ChatColor.GRAY + " wrote your name in chat!");
                        e.setMessage(e.getMessage().replace(array, ChatColor.AQUA +  array + ChatColor.RESET));
                    }
                }
            }
        }

        if (e.getMessage().startsWith("@") && StafferUtil.isStaffer(e.getPlayer())){
            e.setCancelled(true);
            try{
            Bukkit.getPluginManager().callEvent(new StaffChatEvent(StafferUtil.getStaffer(e.getPlayer().getName()),e.getMessage()));
            } catch(StafferNotFoundException e2){
                e2.printStackTrace();
            }
        }
    }

}
