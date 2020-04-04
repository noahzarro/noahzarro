package me.attila.ctf.logic;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;

/**
 * FlagBlock
 */
public class FlagBlock {

    private int x;
    private int y;
    private int z;

    private boolean has_flag;

    private boolean is_set;

    public FlagBlock() {
        this.is_set = false;
        this.has_flag = false;
    }

    public FlagBlock(int x, int y, int z, Material color) {
        this.is_set = false;
        this.has_flag = false;
        setPosition(x, y, z, color);
    }

    public void setPosition(int x, int y, int z, Material color) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.is_set = true;
        this.has_flag = true;

        if (color != Material.WHITE_BANNER) {
            addVisualFlag(color);
        }
    }

    public void removeFlag() {

        this.is_set = false;
        removeVisualFlag();
    }

    public boolean isSet() {
        return this.is_set;
    }

    public boolean isBlock(int x, int y, int z) {
        return this.x == x && this.y == y && this.z == z && is_set;
    }

    private void addVisualFlag(Material color) {
        Location flag_location = new Location(Bukkit.getWorld("world"), this.x, this.y + 1, this.z);
        Block flag = flag_location.getBlock();
        flag.setType(color);
    }

    private void removeVisualFlag() {
        Location flag_location = new Location(Bukkit.getWorld("world"), this.x, this.y + 1, this.z);
        Block flag = flag_location.getBlock();
        flag.setType(Material.AIR);
    }
}