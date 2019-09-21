package com.example.uoft_map;

import java.util.List;

public class LocsController {
    public static LocsManager locsManager;

    private Loc currentLoaction;

    public LocsController() {
        if(locsManager == null){
            locsManager = new LocsManager();
            setUpInfo();
        }
    }

    public LocsManager getLocsManager() {
        return locsManager;
    }


    private void setUpInfo(){
        locsManager.addLoc(43.6596,-79.3977,"Bahen Center", "BA",
                "40 St George St, Toronto, ON M5S 2E4\n");
        locsManager.addLoc(43.666375,-79.389815,"Brennan Hall", "BR",
                "81 St. Mary Street, Toronto, ON M5S 1J4\n");
        locsManager.addLoc(43.66447,-79.399456,"Robarts Library", "RB",
                "130 St George St, Toronto, ON M5S 1A5\n");
    }

    public Loc getCurrentLoaction() {
        return currentLoaction;
    }

    public void setCurrentLoaction(Loc currentLoaction) {
        this.currentLoaction = currentLoaction;
    }



}
