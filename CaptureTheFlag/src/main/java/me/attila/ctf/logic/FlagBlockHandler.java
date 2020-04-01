package me.attila.ctf.logic;

import org.bukkit.Material;

public class FlagBlockHandler {
    private FlagBlock[] flag_block_list;
    private Material[] flag_colors;
    private String[] flag_color_string;


    public FlagBlockHandler() {
        this.flag_colors = new Material[] {Material.WHITE_BANNER, Material.RED_BANNER, Material.BLUE_BANNER, Material.GREEN_BANNER};
        this.flag_block_list = new FlagBlock[4];
        this.flag_color_string = new String[] {"Weiss", "Rot", "Blau", "Grüen"};
        for (int i = 0; i < 4; i++) {
            this.flag_block_list[i] = new FlagBlock();
        }
    }


    public void setFlagBlock(int x, int y, int z, int number) {
        this.flag_block_list[number].setPosition(x, y, z, this.flag_colors[number]);
    }

    /**
     * @return team number of block. -1 if no team
     */
    public int getTeamNumber(int x, int y, int z) {
        for (int i = 0; i < this.flag_block_list.length; i++) {
            if (this.flag_block_list[i].isBlock(x, y, z)) {
                return i;
            }
        }
        return -1;
    }

    public String getFlagColor(int team_number){
        return flag_color_string[team_number];
    }

    public Material getFlagMaterial(int team_number){
        return flag_colors[team_number];
    }

    public void pickUpFlag(int flag_number) {
        flag_block_list[flag_number].removeFlag();
    }
}