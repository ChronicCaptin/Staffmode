package it.mr_replete.staff.events;

import it.mr_replete.staff.staff.Staffer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StaffChatEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Staffer staffer;
    private String message;

    public StaffChatEvent(Staffer staffer, String message) {
        this.staffer = staffer;
        this.message = message;
    }

    public Staffer getStaffer() {
        return staffer;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }

}
