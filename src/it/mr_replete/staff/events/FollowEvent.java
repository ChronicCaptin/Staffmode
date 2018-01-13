package it.mr_replete.staff.events;

import it.mr_replete.staff.staff.Staffer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FollowEvent  extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Staffer staffer;
    private Player target;


    public FollowEvent(Staffer staffer, Player target) {
        this.staffer = staffer;
        this.target = target;
    }

    public Staffer getStaffer() {
        return staffer;
    }

    public Player getTarget() {
        return target;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;

    }
}
