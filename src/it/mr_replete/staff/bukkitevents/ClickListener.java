package it.mr_replete.staff.bukkitevents;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.CurrentUtil;
import it.mr_replete.staff.Util.StafferUtil;
import it.mr_replete.staff.cpscounter.CPSCounter;
import it.mr_replete.staff.events.*;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Set;

public class ClickListener implements Listener {

    public ClickListener() {
        Bukkit.getPluginManager().registerEvents(this, Staff.getInstance());
    }

    @EventHandler
    public void onclick(PlayerInteractEntityEvent e) throws StafferNotFoundException {
        if (e.getPlayer().getItemInHand() != null && e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().hasItemMeta() && StafferUtil.isStaffer(e.getPlayer()) && StafferUtil.getStaffer(e.getPlayer().getName()).isStaffMode()){
            switch(e.getPlayer().getItemInHand().getType()){
                case BLAZE_ROD:
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new FreezeClickEvent(StafferUtil.getStaffer(e.getPlayer().getName()), (Player)e.getRightClicked()));
                    break;
                case PAPER:
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new VerifEvent(StafferUtil.getStaffer(e.getPlayer().getName()), (Player)e.getRightClicked()));
                    break;
                case LEASH:
                    e.setCancelled(true);
                    Bukkit.getPluginManager().callEvent(new FollowEvent(StafferUtil.getStaffer(e.getPlayer().getName()),(Player)e.getRightClicked()));
                    break;
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void oninventoryclick(InventoryClickEvent e){
        if (e.getInventory().equals(Settings.FREEZE_INV)){
            e.setCancelled(true);
        } else if (e.getInventory().getTitle().contains(Settings.CURRENT_STAFFERS_INVENTORY_TITLE.replace("%staffcount%", ""))){
            if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getType().equals(Material.SKULL_ITEM)){
                SkullMeta meta = (SkullMeta) e.getCurrentItem().getItemMeta();
                e.getWhoClicked().teleport(Bukkit.getPlayer(meta.getOwner()));
            }
        } else if (e.getInventory().getTitle().contains("Inventory")){
            e.setCancelled(true);
        } else if (e.getInventory().getTitle().equals(ChatColor.AQUA + "Speed!") && e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.INK_SACK) && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasLore()){
            // SPEED CODE
            Player player = (Player) e.getWhoClicked();
            String firstline = e.getCurrentItem().getItemMeta().getLore().get(0);
            String secondline = e.getCurrentItem().getItemMeta().getLore().get(1);

            if (firstline.contains("Walk")){
                if (secondline.contains("10")){
                    player.setWalkSpeed(1.0f);
                    player.sendMessage(ChatColor.GREEN + "Walk Speed set to 10 (1.0f)");
                } else if (secondline.contains("5")){
                    player.setWalkSpeed(0.5f);
                    player.sendMessage(ChatColor.GREEN + "Walk Speed set to 5 (0.5f)");
                } else if (secondline.contains("1")){
                    player.setWalkSpeed(0.2f);
                    player.sendMessage(ChatColor.GREEN + "Walk Speed set to 1 (0.2f)");
                }
            } else if (firstline.contains("Fly")){
                if (secondline.contains("10")){
                    player.setFlySpeed(1.0f);
                    player.sendMessage(ChatColor.GREEN + "Fly Speed set to 10 (1.0f)");
                } else if (secondline.contains("5")){
                    player.setFlySpeed(0.5f);
                    player.sendMessage(ChatColor.GREEN + "Fly Speed set to 5 (0.5f)");
                } else if (secondline.contains("1")){
                    player.setFlySpeed(0.1f);
                    player.sendMessage(ChatColor.GREEN + "Fly Speed set to 1 (0.1f)");
                }
            }

            player.closeInventory();

        }
    }

    @EventHandler
    public void oninteract(PlayerInteractEvent e) throws StafferNotFoundException{
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
            if (e.getItem() != null && e.getItem().hasItemMeta() && StafferUtil.isStaffer(e.getPlayer()) && StafferUtil.getStaffer(e.getPlayer().getName()).isStaffMode()){
                switch(e.getPlayer().getItemInHand().getType()){
                    case INK_SACK:
                        e.setCancelled(true);
                        if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains(Settings.SPEED_NAME)) {
                            Bukkit.getPluginManager().callEvent(new SpeedClickEvent(StafferUtil.getStaffer(e.getPlayer().getName())));
                            break;
                        } else{
                            Bukkit.getPluginManager().callEvent(new VanishEvent(StafferUtil.getStaffer(e.getPlayer().getName())));
                            break;
                        }
                    case BOOK:
                        e.setCancelled(true);
                        e.getPlayer().openInventory(CurrentUtil.getCurrentStaffInventory());
                        break;
                    case WATCH:
                        e.setCancelled(true);
                        Bukkit.getPluginManager().callEvent(new RandomTpEvent(StafferUtil.getStaffer(e.getPlayer().getName())));
                }
            }
        } else if (e.getAction().equals(Action.LEFT_CLICK_AIR) || (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) && !e.getClickedBlock().getType().equals(Material.GRASS)){
            CPSCounter.getCps().put(e.getPlayer().getUniqueId(), CPSCounter.getCPS(e.getPlayer().getUniqueId())+1);
            if (CPSCounter.getCPS(e.getPlayer().getUniqueId())>= Settings.minClicks){
                CPSCounter.getTimes().put(e.getPlayer().getUniqueId(), CPSCounter.getTimes(e.getPlayer().getUniqueId())+1);
                Bukkit.getPluginManager().callEvent(new AutoClickEvent(e.getPlayer(),CPSCounter.getCPS(e.getPlayer().getUniqueId()),CPSCounter.getTimes(e.getPlayer().getUniqueId())));
            }
        }
    }


}
