package it.mr_replete.staff.Util;

import com.maxmind.geoip.mcimplements.GeoIP;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

import java.net.Inet4Address;
import java.net.InetAddress;

public class PlayerUtil {

    public static double getPlayerHealth(Player player){
        Damageable damageable = player;
        return damageable.getHealth();
    }

    public static int getFoodLvl(Player player){
        return player.getFoodLevel();
    }

    public static String getPlayerIP(Player player){
        return player.getAddress().getAddress().toString();
    }

    public static String getState(Player player){
        if (!isLocalHost(getInetAddress(player))){
            GeoIP geoIP = new GeoIP(getInetAddress(player));
            return geoIP.countryName;
        } else{
            return "Home!";
        }
    }

    public static String getCity(Player player){
        if (!isLocalHost(getInetAddress(player))){
            GeoIP geoIP = new GeoIP(getInetAddress(player));
            return geoIP.city;
        } else{
            return "Home!";
        }
    }

    private static InetAddress getInetAddress(Player player){
        return player.getAddress().getAddress();
    }

    public static boolean isLocalHost(InetAddress address){
        return address.isAnyLocalAddress() || address.isLoopbackAddress();
    }

}
