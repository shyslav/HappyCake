package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.cafeCoordinate;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class CafeCoordinateList {
    private ArrayList<cafeCoordinate> cafeCoordinate;

    public CafeCoordinateList(int id) {
        if(id == 0) {
            cafeCoordinate = ServerCommands.getCafeCoordinate(null);
        } else
        {
            cafeCoordinate = ServerCommands.getCafeCoordinate(String.valueOf(id));
        }
    }

    public ArrayList<com.shyslav.models.cafeCoordinate> getCafeCoordinate() {
        return cafeCoordinate;
    }

    public void setCafeCoordinate(ArrayList<com.shyslav.models.cafeCoordinate> cafeCoordinate) {
        this.cafeCoordinate = cafeCoordinate;
    }
}
