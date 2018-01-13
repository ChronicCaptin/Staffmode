package it.mr_replete.staff.settings;

import it.mr_replete.staff.configuration.ConfUtil;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum StaffersItems {

    FREEZE(Settings.FREEZE_NAME, Material.BLAZE_ROD, Settings.FREEZE_ACTIVE, Settings.SLOT_FREEZE),
    STAFFERS(Settings.STAFFERS_NAME, Material.BOOK, Settings.STAFFERS_ACTIVE, Settings.SLOT_STAFFERS),
    VANISHON(Settings.VANISH_ITEM_STATEON, Material.INK_SACK, (short) 10, Settings.VANISH_ACTIVE, Settings.SLOT_VANISH),
    VANISHOFF(Settings.VANISH_ITEM_STATEOFF, Material.INK_SACK, (short) 8, Settings.VANISH_ACTIVE, Settings.SLOT_VANISH),
    VERIF(Settings.VERIF_NAME, Material.PAPER, Settings.VERIF_ACTIVE, Settings.VERIF_SLOT),
    RANDOMTP(Settings.RANDOMTP_NAME, Material.WATCH, Settings.RANDOMTP_ACTIVE, Settings.RANDOMTP_SLOT),
    FOLLOW(Settings.FOLLOW_NAME, Material.LEASH, Settings.FOLLOW_ACTIVE, Settings.SLOT_FOLLOW),
    SPEED(Settings.SPEED_NAME, Material.INK_SACK, (short)15, Settings.SPEED_ACTIVE, Settings.SLOT_SPEED);

    private String displayname;
    private Material material;
    private boolean active;
    private int position;
    private short dur;

    StaffersItems(String displayname, Material material, boolean active, int position) {
        this.displayname = displayname;
        this.material = material;
        this.active = active;
        this.position = position;
    }

    StaffersItems(String displayname, Material material, short dur, boolean active, int position) {
        this.displayname = displayname;
        this.material = material;
        this.active = active;
        this.position = position;
        this.dur = dur;
    }


    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDur(short dur) {
        this.dur = dur;
    }

    public String getDisplayname() {
        return displayname;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isActive() {
        return active;
    }

    public int getPosition() {
        return position;
    }

    public short getDur() {
        return dur;
    }

    public static List<StaffersItems> getAllEnums(){
        // init the enums

        List<StaffersItems> list = new ArrayList<StaffersItems>();
        list.add(FREEZE);
        list.add(STAFFERS);
        list.add(VANISHON);
        list.add(VANISHOFF);
        list.add(RANDOMTP);
        list.add(VERIF);
        list.add(FOLLOW);
        list.add(SPEED);

        for (StaffersItems items : list){
            String string = toString(items);
            items.setActive(ConfUtil.readBoolean("items."+string+".active"));
            if (items.equals(StaffersItems.VANISHON)){
                items.setDisplayname(ConfUtil.translateString("items."+string+".name").replace("%state%", ConfUtil.translateString("items."+string+".stateon")));
            } else if (items.equals(StaffersItems.VANISHOFF)){
                items.setDisplayname(ConfUtil.translateString("items."+string+".name").replace("%state%", ConfUtil.translateString("items."+string+".stateoff")));
            }  else {
                items.setDisplayname(ConfUtil.translateString("items." + string + ".name"));
            }
            items.setPosition(ConfUtil.readInt("items."+string+".slot"));
        }

        return list;
    }

    private static final String toString(StaffersItems item){
        switch (item){
            case FREEZE:
                return "freeze";
            case STAFFERS:
                return "staffers";
            case VANISHON:
                return "vanish";
            case VANISHOFF:
                return "vanish";
            case RANDOMTP:
                return "randomtp";
            case VERIF:
                return "verif";
            case FOLLOW:
                return "follow";
            case SPEED:
                return "speed";
            default:
                return "";
        }
    }

}
