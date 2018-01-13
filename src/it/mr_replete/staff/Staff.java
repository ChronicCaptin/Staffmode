package it.mr_replete.staff;

import it.mr_replete.staff.Util.Sender;
import it.mr_replete.staff.Util.StafferManager;
import it.mr_replete.staff.bukkitevents.*;
import it.mr_replete.staff.command.GCommand;
import it.mr_replete.staff.command.staffcommand.*;
import it.mr_replete.staff.cpscounter.CPSCounter;
import it.mr_replete.staff.listener.*;
import it.mr_replete.staff.settings.Settings;
import it.mr_replete.staff.staff.Staffer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Staff extends JavaPlugin {

    public static Staff instance;

    private Set<Staffer> staffers = new HashSet<Staffer>();


    private Set<UUID> frozen = new HashSet<UUID>();

    public Map<String, GCommand> commands = new HashMap<String, GCommand>();

    private WeakHashMap<Staffer, ItemStack[]> lastinventories = new WeakHashMap<Staffer, ItemStack[]>();
    private WeakHashMap<Staffer, Player> lastrandomplayer = new WeakHashMap<Staffer, Player>();
    private WeakHashMap<String, String> premiumUUID = new WeakHashMap<String, String>();
    private Map<UUID, Long> reportdelay = new HashMap<UUID, Long>();

    @Override
    public void onEnable() {
        this.instance = this;

        Sender.log(ChatColor.GOLD + "[Staff] Loading components...");

        // save config

        saveDefaultConfig();

        // setUP

        Settings.setUp();

        // CPS run method

        CPSCounter.run();

        // register events

        new JoinListener();
        new QuitListener();
        new ClickListener();
        new TryPlayerListener();
        new DamageListener();
        new ChatListener();
        new TabListener();

        // register custom events

        new FreezeListener();
        new StaffChatListener();
        new VanishListener();
        new RandomTpListener();
        new VerifListener();
        new AutoClickListener();
        new FollowListener();
        new SpeedListener();

        // register custom commands

        new GVanish();
        new GStaffCommand();
        new GStaffChat();
        new GGod();
        new GFreeze();
        new GVerif();
        new GReport();

        // prevent any bugs setting up Staffers

        StafferManager.setUpStaffers();

        Sender.log(ChatColor.GREEN + "[Staff] Components Loaded! OK");

    }

    @Override
    public void onDisable() {
        StafferManager.fixOnRestartOrReload();
    }

    public static Staff getInstance() {
        return instance;
    }

    public Set<Staffer> getStaffers() {
        return staffers;
    }

    public WeakHashMap<String, String> getPremiumUUID() {
        return premiumUUID;
    }

    public Set<UUID> getFrozen() {
        return frozen;
    }

    public WeakHashMap<Staffer, ItemStack[]> getLastinventories() {
        return lastinventories;
    }

    public WeakHashMap<Staffer, Player> getLastrandomplayer() {
        return lastrandomplayer;
    }

    public Map<UUID, Long> getReportdelay() {
        return reportdelay;
    }
}
