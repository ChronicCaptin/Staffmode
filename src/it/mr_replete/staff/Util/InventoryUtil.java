package it.mr_replete.staff.Util;

import com.google.common.collect.Lists;
import it.mr_replete.staff.reflection.Reflection;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class InventoryUtil {

    public static Inventory getPlayerInventory(Player player){

        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.YELLOW + player.getName() + "'s Inventory");

        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + player.getName());
        meta.setOwner(player.getName());

        head.setItemMeta(meta);

        inventory.setItem(4, head);

        int i = 9;

        for (ItemStack itemstack : player.getInventory().getContents()){
            inventory.setItem(i, itemstack);
            ++i;
        }

        int j = 45;

        for (ItemStack itemStack : player.getInventory().getArmorContents()){
            inventory.setItem(j, itemStack);
            ++j;
        }

        inventory.setItem(49, ItemsUtil.createItemStack(ChatColor.RED + "Health",Material.SPECKLED_MELON,1, Lists.newArrayList(ChatColor.RED + "" + PlayerUtil.getPlayerHealth(player) + "/20.0")));
        inventory.setItem(50, ItemsUtil.createItemStack(ChatColor.AQUA + "Food Level", Material.COOKED_BEEF, 1, Lists.newArrayList(ChatColor.AQUA + "" + PlayerUtil.getFoodLvl(player) + "/20")));
        inventory.setItem(53, ItemsUtil.createItemStack(ChatColor.GOLD + "IP Address", Material.BLAZE_ROD, 1, Lists.newArrayList(ChatColor.AQUA + "InetAddress: " + ChatColor.GOLD + PlayerUtil.getPlayerIP(player))));
        inventory.setItem(51, ItemsUtil.createItemStack(ChatColor.GREEN + "Player Ping", Material.GHAST_TEAR, 1, Lists.newArrayList(ChatColor.GRAY + "Ping: " + ChatColor.GREEN + Reflection.getPlayerPing(player) + "ms")));
        inventory.setItem(52, ItemsUtil.createItemStack(ChatColor.DARK_AQUA + "Player Info", Material.BOOK, 1, Lists.newArrayList(
                ChatColor.AQUA + "GeoLocation: " + ChatColor.YELLOW + PlayerUtil.getState(player) + ", " + PlayerUtil.getCity(player),
                ChatColor.AQUA + "Flying: " + (player.isFlying() ? ChatColor.GREEN + "true" : ChatColor.RED + "false"),
                ChatColor.AQUA + "OP: " + (player.isOp() ? ChatColor.GREEN + "true" : ChatColor.RED + "false"),
                ChatColor.AQUA + "Frozen: " + (FreezeUtil.isFrozen(player) ? ChatColor.GREEN + "true" : ChatColor.RED + "false"),
                ChatColor.AQUA + "Staffer: " + (StafferUtil.isStaffer(player) ? ChatColor.GREEN + "true" : ChatColor.RED + "false"))));

        return inventory;

    }

    public static Inventory getSpeedInventory(){

        Inventory inventory = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Speed!");

        inventory.setItem(12, ItemsUtil.createItemStack(ChatColor.GREEN + "Speed Booster", Material.INK_SACK, 1, (short)15, Lists.newArrayList(
                ChatColor.YELLOW + "Type: " + ChatColor.GRAY + "Walk Speed",
                ChatColor.YELLOW + "Level: " + ChatColor.GRAY + "1 (Normal Speed)"
        )));
        inventory.setItem(13, ItemsUtil.createItemStack(ChatColor.GREEN + "Speed Booster", Material.INK_SACK, 1, (short)15, Lists.newArrayList(
                ChatColor.YELLOW + "Type: " + ChatColor.GRAY + "Walk Speed",
                ChatColor.YELLOW + "Level: " + ChatColor.GRAY + "5 (Hight Speed)"
        )));
        inventory.setItem(14, ItemsUtil.createItemStack(ChatColor.GREEN + "Speed Booster", Material.INK_SACK, 1, (short)15, Lists.newArrayList(
                ChatColor.YELLOW + "Type: " + ChatColor.GRAY + "Walk Speed",
                ChatColor.YELLOW + "Level: " + ChatColor.GRAY + "10 (Crazy Speed)"
        )));

        inventory.setItem(21, ItemsUtil.createItemStack(ChatColor.GREEN + "Speed Booster", Material.INK_SACK, 1, (short)15, Lists.newArrayList(
                ChatColor.YELLOW + "Type: " + ChatColor.GRAY + "Fly Speed",
                ChatColor.YELLOW + "Level: " + ChatColor.GRAY + "1 (Normal Speed)"
        )));
        inventory.setItem(22, ItemsUtil.createItemStack(ChatColor.GREEN + "Speed Booster", Material.INK_SACK, 1, (short)15, Lists.newArrayList(
                ChatColor.YELLOW + "Type: " + ChatColor.GRAY + "Fly Speed",
                ChatColor.YELLOW + "Level: " + ChatColor.GRAY + "5 (Hight Speed)"
        )));
        inventory.setItem(23, ItemsUtil.createItemStack(ChatColor.GREEN + "Speed Booster", Material.INK_SACK, 1, (short)15, Lists.newArrayList(
                ChatColor.YELLOW + "Type: " + ChatColor.GRAY + "Fly Speed",
                ChatColor.YELLOW + "Level: " + ChatColor.GRAY + "10 (Crazy Speed)"
        )));

        return inventory;

    }

}
