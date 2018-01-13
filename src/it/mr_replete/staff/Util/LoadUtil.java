package it.mr_replete.staff.Util;

import com.maxmind.geoip.LookupService;
import it.mr_replete.staff.Staff;
import it.mr_replete.staff.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class LoadUtil {

    public static void init(){
        if(!new File(Staff.getInstance().getDataFolder(), "GeoLite2-City.mmdb").exists()){
            try {
                downloadFrom("http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz");
            }catch (IOException e){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Something went wrong when downloading the geoip database.");
                e.printStackTrace();
                Bukkit.getPluginManager().disablePlugin(Staff.getInstance());
            }
        }

        try {
            Settings.geoip = new LookupService(new File(Staff.getInstance().getDataFolder(), "GeoLiteCity.dat"), LookupService.GEOIP_CHECK_CACHE);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("Something went wrong.");
            Bukkit.getPluginManager().disablePlugin(Staff.getInstance());
        }
    }

    /**
     *
     * Download from internet <3
     *
     * @param url
     * @throws IOException
     */

    private static void downloadFrom(String url) throws IOException{
        URL CityURL = new URL ("http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz");

        URLConnection connection = CityURL.openConnection();
        connection.setConnectTimeout(10000);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Minecraft server) Bukkit");
        connection.connect();
        InputStream input = connection.getInputStream();
        input = new GZIPInputStream(input);

        Staff.getInstance().getDataFolder().mkdir();

        OutputStream output = new FileOutputStream(new File( Staff.getInstance().getDataFolder(), "GeoLiteCity.dat"));
        byte[] buffer = new byte[2048];
        int length = input.read(buffer);
        while (length >= 0)
        {
            output.write(buffer, 0, length);
            length = input.read(buffer);
        }

        output.close();
        input.close();
    }

}
