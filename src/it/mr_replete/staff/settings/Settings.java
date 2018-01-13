package it.mr_replete.staff.settings;

import com.google.common.collect.Lists;
import com.maxmind.geoip.LookupService;
import it.mr_replete.staff.Staff;
import it.mr_replete.staff.Util.ItemsUtil;
import it.mr_replete.staff.Util.LoadUtil;
import it.mr_replete.staff.configuration.ConfUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


import javax.security.auth.login.Configuration;
import java.util.*;

public class Settings {

    public static String stafferpermission;

    public static Set<ItemStack> itemStackSet = new HashSet<ItemStack>();
    public static Map<ItemStack, Integer> positions = new HashMap<ItemStack, Integer>();

    public static int minClicks = 0;

    public static LookupService geoip = null;

    public static String NOPERMISSION;

    public static String SYNTAX_ERROR;

    public static int SLOT_FREEZE;
    public static int SLOT_STAFFERS;
    public static int SLOT_VANISH;
    public static int SLOT_FOLLOW;
    public static int SLOT_SPEED;

    public static boolean FREEZE_ACTIVE;
    public static boolean STAFFERS_ACTIVE;
    public static boolean VANISH_ACTIVE;
    public static boolean FOLLOW_ACTIVE;
    public static boolean SPEED_ACTIVE;

    public static String FREEZE_NAME;
    public static String STAFFERS_NAME;
    public static String VANISH_NAME;
    public static String SPEED_NAME;

    public static String GUINAME;
    public static String PAPERNAME;
    public static List<String> PAPERLORE;

    public static boolean FREEZE_BANONQUIT;

    public static String FREEZE_CMD;
    public static Inventory FREEZE_INV;

    public static String FREEZE_MESSAGE;
    public static String UNFREEZE_MESSAGE;
    public static String FREEZE_STAFF_MESSAGE;
    public static String UNFREEZE_STAFF_MESSAGE;
    public static String FREEZE_SLOGMESSAGE;
    public static String FOLLOW_NAME;

    public static boolean VANISH_SCSS;

    public static String VANISH_ONMSG;
    public static String VANISH_OFFMSG;

    public static String VANISH_ITEM_STATEON;
    public static String VANISH_ITEM_STATEOFF;

    public static String STAFFMODE_MESSAGE;
    public static String STAFFMODE_ON;
    public static String STAFFMODE_OFF;

    public static String STAFFCHAT_FORMAT;

    public static ItemStack ITEM_VANISHON;
    public static ItemStack ITEM_VANISHOFF;

    public static String CURRENT_STAFFERS_INVENTORY_TITLE;

    public static String GOD_MSGON;
    public static String GOD_MSGOFF;

    public static String RANDOMTP_NAME;

    public static String VERIF_NAME;
    public static int VERIF_SLOT;
    public static boolean VERIF_ACTIVE;

    public static int RANDOMTP_SLOT;

    public static boolean RANDOMTP_ACTIVE;

    public static int REPORT_DELAY;
    public static String REPORT_DELAY_MSG;

    public static String AUTOCLICK_MSG;
    public static boolean AUTOCLICK_ACTIVE;

    public static List<String> REPORT_FORMAT;

    public static void setUp(){

        LoadUtil.init();

        itemStackSet.clear();
        positions.clear();

        stafferpermission = "staff.admin";

        NOPERMISSION = ConfUtil.translateString("messages.nopermission");

        SLOT_FREEZE = ConfUtil.readInt("items.freeze.slot");
        SLOT_STAFFERS = ConfUtil.readInt("items.staffers.slot");
        SLOT_VANISH = ConfUtil.readInt("items.vanish.slot");

        FREEZE_ACTIVE = ConfUtil.readBoolean("items.freeze.active");
        STAFFERS_ACTIVE = ConfUtil.readBoolean("items.staffers.active");
        VANISH_ACTIVE = ConfUtil.readBoolean("items.vanish.active");
        SPEED_ACTIVE = ConfUtil.readBoolean("items.speed.active");

        FOLLOW_ACTIVE = ConfUtil.readBoolean("items.follow.active");
        SLOT_FOLLOW = ConfUtil.readInt("items.follow.slot");
        FOLLOW_NAME = ConfUtil.translateString("items.follow.name");
        SPEED_NAME = ConfUtil.translateString("items.speed.name");

        VERIF_NAME = ConfUtil.translateString("items.verif.name");
        VERIF_ACTIVE = ConfUtil.readBoolean("items.verif.active");
        VERIF_SLOT =  ConfUtil.readInt("items.verif.slot");
        SLOT_SPEED = ConfUtil.readInt("items.speed.slot");

        FREEZE_NAME = ConfUtil.translateString("items.freeze.name");
        STAFFERS_NAME = ConfUtil.translateString("items.staffers.name");
        VANISH_NAME = ConfUtil.translateString("items.vanish.name");

        GUINAME = ConfUtil.translateString("options.freeze.guiname");
        PAPERNAME = ConfUtil.translateString("options.freeze.papername");
        PAPERLORE = ConfUtil.translateStringList("options.freeze.paperlore");

        RANDOMTP_ACTIVE = ConfUtil.readBoolean("items.randomtp.active");
        RANDOMTP_NAME = ConfUtil.translateString("items.randomtp.name");
        RANDOMTP_SLOT = ConfUtil.readInt("items.randomtp.slot");

        FREEZE_BANONQUIT = ConfUtil.readBoolean("options.freeze.banonquit");

        FREEZE_MESSAGE = ConfUtil.translateString("messages.freezemessage");
        UNFREEZE_MESSAGE = ConfUtil.translateString("messages.unfreezemessage");
        FREEZE_STAFF_MESSAGE = ConfUtil.translateString("messages.freezestaffmessage");
        UNFREEZE_STAFF_MESSAGE = ConfUtil.translateString("messages.unfreezestaffmessage");
        FREEZE_SLOGMESSAGE = ConfUtil.translateString("options.freeze.slogmessage");

        FREEZE_CMD = ConfUtil.translateString("options.freeze.slogbancommand");
        FREEZE_INV = Bukkit.createInventory(null, 9, GUINAME);

        VANISH_SCSS = ConfUtil.readBoolean("options.vanish.stafferscanseeotherstaffer");

        VANISH_ONMSG = ConfUtil.translateString("messages.vanish").replace("%state%", ConfUtil.translateString("messages.vanishon"));
        VANISH_OFFMSG = ConfUtil.translateString("messages.vanish").replace("%state%", ConfUtil.translateString("messages.vanishoff"));

        VANISH_ITEM_STATEON = ConfUtil.translateString("items.vanish.name").replace("%state%", ConfUtil.translateString("items.vanish.stateon"));
        VANISH_ITEM_STATEOFF = ConfUtil.translateString("items.vanish.name").replace("%state%", ConfUtil.translateString("items.vanish.stateoff"));

        STAFFMODE_MESSAGE = ConfUtil.translateString("messages.staffmode");

        STAFFMODE_ON = ConfUtil.translateString("messages.staffmode").replace("%state%", ConfUtil.translateString("messages.staffmodeon"));
        STAFFMODE_OFF = ConfUtil.translateString("messages.staffmode").replace("%state%", ConfUtil.translateString("messages.staffmodeoff"));

        STAFFCHAT_FORMAT = ConfUtil.translateString("options.staffchat.format");

        SYNTAX_ERROR = ChatColor.RED + "Invalid command!";

        ITEM_VANISHON = ItemsUtil.createItemStack(StaffersItems.VANISHON.getDisplayname(), StaffersItems.VANISHON.getMaterial(), 1, StaffersItems.VANISHON.getDur());
        ITEM_VANISHOFF = ItemsUtil.createItemStack(StaffersItems.VANISHOFF.getDisplayname(), StaffersItems.VANISHOFF.getMaterial(), 1, StaffersItems.VANISHOFF.getDur());

        CURRENT_STAFFERS_INVENTORY_TITLE = ConfUtil.translateString("options.staffonlineinventory.title");

        GOD_MSGON = ConfUtil.translateString("messages.god").replace("%state%", ConfUtil.translateString("messages.godon"));
        GOD_MSGOFF = ConfUtil.translateString("messages.god").replace("%state%", ConfUtil.translateString("messages.godoff"));

        REPORT_DELAY = ConfUtil.readInt("options.report.commandelay"); // in seconds
        REPORT_DELAY_MSG = ConfUtil.translateString("messages.reportdelay");

        minClicks = ConfUtil.readInt("options.clicks.amountofclicks");

        AUTOCLICK_MSG = ConfUtil.translateString("messages.autoclick");
        AUTOCLICK_ACTIVE = ConfUtil.readBoolean("options.clicks.active");

        REPORT_FORMAT = ConfUtil.translateStringList("options.report.format");

        // setting up freeze inventory

        FREEZE_INV.setItem(4, ItemsUtil.createItemStack(PAPERNAME, Material.PAPER, 1, PAPERLORE));

        for (StaffersItems types : StaffersItems.getAllEnums()){
            if (types.isActive()){
                if (!types.equals(StaffersItems.VANISHOFF)){
                    ItemStack item = null;
                    if (types.getDur() != (short)0){
                        item = ItemsUtil.createItemStack(types.getDisplayname(), types.getMaterial(), 1, types.getDur());
                    } else {
                        item = ItemsUtil.createItemStack(types.getDisplayname(), types.getMaterial(), 1);
                    }
                    itemStackSet.add(item);
                    positions.put(item, types.getPosition());
                }
            }
        }
    }

}
