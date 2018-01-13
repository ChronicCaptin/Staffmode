package it.mr_replete.staff.Util;

import it.mr_replete.staff.Staff;
import it.mr_replete.staff.exception.StafferNotFoundException;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StafferManager implements Staffer {

    private String name;
    private UUID uuid;
    private Player player;
    private boolean vanish;
    private boolean staffmode;
    private boolean god;

    public Staffer registerStaffer(String name, UUID uuid, Player player){
        setName(name);
        setUUID(uuid);
        setPlayer(player);
        setVanish(false);
        setStaffMode(false);
        setGod(false);
        return this;
    }


    public static void checkAndFix(){
        for (Player op : Bukkit.getOnlinePlayers()){
            try{
                Staffer staffer = StafferUtil.getStaffer(op.getName());
                if (!StafferUtil.isStaffer(op) && Staff.getInstance().getStaffers().contains(staffer)){
                    unregister(op.getUniqueId());
                }
            } catch(StafferNotFoundException e){

            }
        }
    }

    public static void setUpStaffers(){
        Staff.getInstance().getStaffers().clear();
        for (Player op : Bukkit.getServer().getOnlinePlayers()){
            if (StafferUtil.isStaffer(op)) {
                Staffer staffer = new StafferManager().registerStaffer(op.getName(), op.getUniqueId(), op);
                Staff.getInstance().getStaffers().add(staffer);
                staffer.setVanish(false);
                staffer.setStaffMode(false);
                staffer.setGod(false);
                show(op);
            }
        }
    }

    public static void fixOnRestartOrReload(){
        for (Staffer staffers : Staff.getInstance().getStaffers()){
            if (staffers.isStaffMode()) {
                ItemsUtil.clear(staffers.getPlayer());
                staffers.getPlayer().getInventory().setContents(Staff.getInstance().getLastinventories().get(staffers));
                Staff.getInstance().getLastinventories().remove(staffers);
            }
        }
    }

    public static void hide(Player player){
        for (Player op : Bukkit.getServer().getOnlinePlayers()){
            op.hidePlayer(player);
        }
    }

    public static void show(Player player){
        for (Player op : Bukkit.getServer().getOnlinePlayers()){
            op.showPlayer(player);
        }
    }

    public static void hideOnlyPlayers(Player player){
        for (Player op : Bukkit.getServer().getOnlinePlayers()){
            if (!StafferUtil.isStaffer(op)){
                op.hidePlayer(player);
            }
        }
    }

    public static void unregister(UUID uuid){
        Staffer staffer = StafferUtil.getStafferWithoutControl(uuid);
        if (Staff.getInstance().getStaffers().contains(staffer)){
            Staff.getInstance().getStaffers().remove(staffer);
        }
    }

    public static void fixUPHide(Player player){
        for (Staffer staffer : Staff.getInstance().getStaffers()) {
                if (staffer.isVanish()) {
                    if (Settings.VANISH_SCSS) {
                        StafferManager.hideOnlyPlayers(staffer.getPlayer());
                    } else {
                        StafferManager.hide(staffer.getPlayer());
                    }
                }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean isVanish() {
        return vanish;
    }

    @Override
    public void setVanish(boolean value) {
        this.vanish = value;
    }

    @Override
    public boolean isStaffMode() {
        return staffmode;
    }

    @Override
    public void setStaffMode(boolean value) {
        this.staffmode = value;
    }

    @Override
    public boolean isGod() {
        return god;
    }

    @Override
    public void setGod(boolean value) {
        this.god = value;
    }

    @Override
    public void heal() {
        getPlayer().setHealth(20.0D);
        getPlayer().setFoodLevel(20);
    }
}
