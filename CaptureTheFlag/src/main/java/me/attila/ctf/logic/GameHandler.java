package me.attila.ctf.logic;

import org.bukkit.Bukkit;

public class GameHandler {
    private int[] team_points;
    private String[] flag_color_string;

    public GameHandler(String[] flag_color_string) {
        this.team_points = new int[] { 0, 0, 0, 0 };
        this.flag_color_string = flag_color_string;
    }

    public void addPoint(int team_number) {
        this.team_points[team_number] += 1;
    }

    public void removePoint(int team_number) {
        this.team_points[team_number] -= 1;
    }

    public int getPoints(int team_number) {
        return this.team_points[team_number];
    }

    public void broadcastGameStats() {
        Bukkit.getServer().broadcastMessage("Punkteliste:");
        for (int i = 0; i < flag_color_string.length; i++) {
            Bukkit.getServer().broadcastMessage("Team " + this.flag_color_string[i] + ": " + this.team_points[i]);
        }
    }
}