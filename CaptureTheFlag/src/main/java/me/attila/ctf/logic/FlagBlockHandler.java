package me.attila.ctf.logic;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

public class FlagBlockHandler {
    private FlagBlock[] flag_block_list;
    private Material[] flag_colors;
    private String[] flag_color_string;

    private Plugin plugin;

    public FlagBlockHandler(String[] flag_color_string, Plugin plugin) {

        // set config
        this.plugin = plugin;

        // set color lists
        this.flag_colors = new Material[] { Material.WHITE_BANNER, Material.RED_BANNER, Material.BLUE_BANNER,
                Material.GREEN_BANNER };
        this.flag_block_list = new FlagBlock[4];
        this.flag_color_string = flag_color_string;

        for (int i = 0; i < 4; i++) {

            FlagBlock new_flag_block;

            // if they existed, create new flag block
            if (plugin.getConfig().contains("flags." + i)) {
                // get all flag block positions

                int x = plugin.getConfig().getInt("flags." + i + ".x");
                int y = plugin.getConfig().getInt("flags." + i + ".y");
                int z = plugin.getConfig().getInt("flags." + i + ".z");

                new_flag_block = new FlagBlock(x, y, z, getFlagMaterial(i));
            } else {
                new_flag_block = new FlagBlock();
            }

            this.flag_block_list[i] = new_flag_block;
        }
    }

    public void setFlagBlock(int x, int y, int z, int number) {
        this.flag_block_list[number].setPosition(x, y, z, this.flag_colors[number]);
        plugin.getConfig().set("flags." + number + ".x", x);
        plugin.getConfig().set("flags." + number + ".y", y);
        plugin.getConfig().set("flags." + number + ".z", z);
        plugin.saveConfig();
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

    public String getFlagColor(int team_number) {
        return flag_color_string[team_number];
    }

    public Material getFlagMaterial(int team_number) {
        return flag_colors[team_number];
    }

    public void pickUpFlag(int flag_number) {
        flag_block_list[flag_number].removeFlag();
    }
}