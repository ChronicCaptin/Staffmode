package it.mr_replete.staff.staff;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface Staffer {


    public String getName();

    public void setName(String name);

    public UUID getUUID();

    public void setUUID(UUID uuid);

    public void sendMessage(String message);

    public void heal();

    public Player getPlayer();

    public void setPlayer(Player player);

    public boolean isVanish();

    public void setVanish(boolean value);

    public boolean isStaffMode();

    public void setStaffMode(boolean value);

    public boolean isGod();

    public void setGod(boolean value);

}
