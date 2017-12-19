package com.genius.whatnumbersays.random_facts;

/**
 * Created by ADMIN on 10/6/2017.
 */

public class MenuTiles {
    private String tileName;
    private int tileIcon;

    public MenuTiles(String tile, int icon){
        tileName = tile;
        tileIcon = icon;
    }

    public int getTileIcon() {
        return tileIcon;
    }

    public void setTileIcon(int tileIcon) {
        this.tileIcon = tileIcon;
    }

    public String getTileName() {
        return tileName;
    }

    public void setTileName(String tileName) {
        this.tileName = tileName;
    }

}
