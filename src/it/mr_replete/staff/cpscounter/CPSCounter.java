package it.mr_replete.staff.cpscounter;

import it.mr_replete.staff.Staff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;
import java.util.WeakHashMap;

public class CPSCounter {

    private static WeakHashMap<UUID,Integer> cps = new WeakHashMap<UUID, Integer>();
    private static WeakHashMap<UUID,Integer> times = new WeakHashMap<UUID,Integer>();

    public static void run(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Staff.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                for (Player players : Bukkit.getOnlinePlayers()){

                    if (!times.containsKey(players.getUniqueId())){
                        times.put(players.getUniqueId(),0);
                    }

                    if (!cps.containsKey(players.getUniqueId())){
                        cps.put(players.getUniqueId(),0);
                    }

                    if (cps.containsKey(players.getUniqueId())){
                        cps.put(players.getUniqueId(), 0);
                    }
                }
            }
        },0L,20L);
    }

    public static int getCPS(UUID uuid){
        if (cps.containsKey(uuid)) {
            return cps.get(uuid);
        } else{
            return 0;
        }
    }

    public static int getTimes(UUID uuid){
        if (times.containsKey(uuid)) {
            return times.get(uuid);
        } else{
            return 0;
        }
    }

    public static WeakHashMap<UUID, Integer> getCps() {
        return cps;
    }

    public static WeakHashMap<UUID, Integer> getTimes() {
        return times;
    }
}
