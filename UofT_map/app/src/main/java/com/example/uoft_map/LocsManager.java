package com.example.uoft_map;

import java.util.ArrayList;
import java.util.List;

public class LocsManager {

    private List<Loc> ItemList;

    public LocsManager(){
       ItemList = new ArrayList<>();
    }

    public List<Loc> getItemList(){
        return ItemList;
    }

    public void addLoc (double latitude, double longitude,
                        String fullName, String absName, String address){
        if(!searchloc(fullName)){
            Loc addItem = new Loc(latitude, longitude, fullName, absName, address);
            ItemList.add(addItem);
        }
    }

    // search whether fullname location is in the list
    private boolean searchloc(String fullName){
        for(Loc locations: ItemList){
            if(locations.getFullName().equals(fullName)){
                return true;
            }
        }
        return false;
    }

    public List<String> getfullNameList(){
        List<String> re_list = new ArrayList<>();
        for(Loc locations: ItemList){
            re_list.add(locations.getFullName());
        }
        return re_list;
    }

    public void deleteLoc (String fullName){
        for(Loc locations: ItemList){
              if(locations.getFullName().equals(fullName)){
                  ItemList.remove(locations);
              }
        }
    }


}
