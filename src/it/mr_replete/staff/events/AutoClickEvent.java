package it.mr_replete.staff.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AutoClickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private int clicks;
    private int times;

    private boolean cancelled;


    public AutoClickEvent(Player player, int clicks, int times) {
        this.player = player;
        this.clicks = clicks;
        this.times = times;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public int getClicks() {
        return clicks;
    }

    public int getTimes() {
        return times;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean bln) {
        this.cancelled = bln;
    }

}

