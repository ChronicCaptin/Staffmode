package it.mr_replete.staff.Util;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.settings.StaffersItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class ItemsUtil {

    public static ItemStack createItemStack(String displayname, Material material, int amnt){

        ItemStack item = new ItemStack(material,amnt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);

        return item;

    }

    public static ItemStack createItemStack(String displayname, Material material, int amnt, List<String> lore){

        ItemStack item = new ItemStack(material,amnt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

    public static ItemStack createItemStack(String displayname, Material material, int amnt, short dur){

        ItemStack item = new ItemStack(material,amnt, dur);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);

        return item;

    }

    public static ItemStack createItemStack(String displayname, Material material, int amnt, short dur, List<String> lore){

        ItemStack item = new ItemStack(material,amnt, dur);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;

    }

    public static ItemStack createHead(String displayname, String owner, int amnt) throws StafferNotFoundException{

        ItemStack item = new ItemStack(Material.SKULL_ITEM, amnt, (short)3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        final List<String> lore = new ArrayList<String>();

        lore.add("§8*§m----------§r§8*");
        lore.add("§eVanish§7: " + (StafferUtil.getStaffer(owner).isVanish() ? "§aON" : "§cOFF"));
        lore.add("§eStaffMode§7: " + (StafferUtil.getStaffer(owner).isStaffMode() ? "§aON" : "§cOFF"));
        lore.add("§eGod: " + (StafferUtil.getStaffer(owner).isGod() ? "§aON" : "§cOFF"));
        lore.add("§dClick to teleport to " + owner + "!");
        lore.add("§8*§m----------§r§8*");

        meta.setLore(lore);
        meta.setDisplayName(displayname);
        meta.setOwner(owner);

        item.setItemMeta(meta);

        return item;
    }

    public static void addItems(Player player){

        for (ItemStack staffersitemstacks : Settings.itemStackSet){
            player.getInventory().setItem(Settings.positions.get(staffersitemstacks)-1, staffersitemstacks);
        }

    }

    public static void clear(Player player){
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getActivePotionEffects().clear();
    }


}
