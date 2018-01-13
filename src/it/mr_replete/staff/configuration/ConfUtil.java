package it.mr_replete.staff.configuration;

import it.mr_replete.staff.Staff;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ConfUtil {

    public static String translateString(String instance){
        return ChatColor.translateAlternateColorCodes('&', Staff.getInstance().getConfig().getString(instance));
    }

    public static int readInt(String instance){
        return Staff.getInstance().getConfig().getInt(instance);
    }

    public static boolean readBoolean(String instance){
        return Staff.getInstance().getConfig().getBoolean(instance);
    }

    public static List<String> translateStringList(String instance) {
        List<String> list = new ArrayList<String>();
        for (String string : Staff.getInstance().getConfig().getStringList(instance)){
            list.add(ChatColor.translateAlternateColorCodes('&',string));
        }
        return list;
    }


}
