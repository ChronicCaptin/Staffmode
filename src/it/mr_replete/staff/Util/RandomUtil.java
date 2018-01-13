package it.mr_replete.staff.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomUtil {


    public static Player getRandomPlayer(){
        Object[] array = Bukkit.getOnlinePlayers().toArray();
        int randomnum = new Random().nextInt(array.length);
        return (Player)array[randomnum];
    }

}
