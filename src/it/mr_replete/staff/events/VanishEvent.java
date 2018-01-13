package it.mr_replete.staff.events;

import it.mr_replete.staff.staff.Staffer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class VanishEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Staffer staffer;

    public VanishEvent(Staffer staffer) {
        this.staffer = staffer;
    }

    public Staffer getStaffer() {
        return staffer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }

}
