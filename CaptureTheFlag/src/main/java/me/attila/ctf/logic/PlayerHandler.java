package me.attila.ctf.logic;

import java.util.ArrayList;
import java.util.UUID;

/**
 * PlayerHandler
 */
public class PlayerHandler {

    private ArrayList<PlayerObject> playerList = new ArrayList<PlayerObject>();

    public PlayerHandler(){

    }

    public void setTeam(int team_number, UUID uuid){
        for (int i = 0; i < playerList.size(); i++) {
            if(playerList.get(i).getUUID()==uuid){
                playerList.get(i).setTeam(team_number);
                return;
            }
        }

        playerList.add(new PlayerObject(uuid, team_number));

    }

    public int getTeam(UUID uuid) {
        for (int i = 0; i < playerList.size(); i++) {
            if(playerList.get(i).getUUID()==uuid){
                return playerList.get(i).getTeam();
            }
        }
        return -1;
    }
}