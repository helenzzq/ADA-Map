package com.example.uoft_map;

public class LocsController {
    private LocsManager locsManager = new LocsManager();

    private Loc currentLoaction;

    public LocsManager getLocsManager() {
        setUpInfo();
        return locsManager;
    }

    private void setUpInfo(){
        locsManager.addLoc(43.657,-79.397,"Bahen Center", "BA",
                "40 St George St, Toronto, ON M5S 2E4\n");
        locsManager.addLoc(433.66,-79.386,"Brennan Hall", "BR",
                "81 St. Mary Street, Toronto, ON M5S 1J4\n");
        locsManager.addLoc(43.664,-79.402,"Robarts Library", "RB",
                "130 St George St, Toronto, ON M5S 1A5\n");
    }

    public Loc getCurrentLoaction() {
        return currentLoaction;
    }

    public void setCurrentLoaction(Loc currentLoaction) {
        this.currentLoaction = currentLoaction;
    }







}
