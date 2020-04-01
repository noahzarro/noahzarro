package me.attila.ctf.logic;

import java.util.UUID;

/**
 * PlayerObject
 */
public class PlayerObject {

    private int team;
    private UUID uuid;

    public PlayerObject(UUID uuid, int team_number) {
        this.team = team_number;
        this.uuid = uuid;
    }

    public void setTeam(int team_number){
        this.team = team_number;
    }

    public int getTeam(){
        return this.team;
    }

    public UUID getUUID(){
        return this.uuid;
    }
}