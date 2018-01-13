package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.events.FollowEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class FollowListener implements Listener {

    public FollowListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onfollow(FollowEvent e){
        e.getTarget().setPassenger(e.getStaffer().getPlayer());
        e.getStaffer().sendMessage(ChatColor.GREEN + "You are following " + e.getTarget().getName() + "!");
    }
}
