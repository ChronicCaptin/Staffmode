package it.mr_replete.staff.Util;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;

public class Sender {

    public static void sendMessage(String message){
        for (Staffer staffer : Staff.getInstance().getStaffers()){
            staffer.sendMessage(message);
        }
    }

    public static void log(String message){
        Bukkit.getConsoleSender().sendMessage(message);
    }

}
