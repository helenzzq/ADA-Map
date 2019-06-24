package com.example.uoft_map;

import java.util.ArrayList;
import java.util.List;

public class LocsManager {
    private List<Loc> LocManager;



    public List<Loc> getLocsManger(){
        if (LocManager == null){
            return new ArrayList<>();
        }
        else{
            return this.LocManager;
        }
    }

    public void addLoc (double latitude, double longitude,
                        String fullName, String absName, String address){
        Loc addItem = new Loc(latitude, longitude, fullName, absName, address);
        LocManager.add(addItem);
    }

    public void deleteLoc (String fullName){
        for(Loc locations: LocManager){
              if(locations.getFullName().equals(fullName)){
                  LocManager.remove(locations);
              }
        }
    }


}
