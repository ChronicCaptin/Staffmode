package it.mr_replete.staff.listener;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.RandomUtil;
import it.mr_replete.staff.events.RandomTpEvent;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RandomTpListener implements Listener {

    public RandomTpListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onrandom(RandomTpEvent e){

        if (Bukkit.getOnlinePlayers().size() == 1){
            e.getStaffer().sendMessage(ChatColor.RED + "There are not enough players");
            return;
        } else if (Bukkit.getOnlinePlayers().size() == Staff.getInstance().getStaffers().size()){
            e.getStaffer().sendMessage(ChatColor.RED + "All players are staffers!");
            return;
        } else {
            Player bestplayer = getBestPlayer(e.getStaffer());
            e.getStaffer().getPlayer().teleport(bestplayer);
            Staff.getInstance().getLastrandomplayer().put(e.getStaffer(),bestplayer);
        }
    }

    public static Player getBestPlayer(Staffer staffer){

        Player rp = RandomUtil.getRandomPlayer();
        Player lastplayer = Staff.getInstance().getLastrandomplayer().containsKey(staffer) ? Staff.getInstance().getLastrandomplayer().get(staffer) : null;

        if (rp.getName().equals(staffer.getName())){
            return getBestPlayer(staffer);
        } else if (lastplayer == null){
            return rp;
        } else if (lastplayer == rp && (Bukkit.getOnlinePlayers().size()-Staff.getInstance().getStaffers().size()) > 1){
            return getBestPlayer(staffer);
        } else if (lastplayer == rp && (Bukkit.getOnlinePlayers().size()-Staff.getInstance().getStaffers().size()) <= 1){
            return rp;
        } else{
            return rp;
        }


    }

}
