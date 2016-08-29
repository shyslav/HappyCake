package com.sukhaniuk.interfaces.impls;

import appmodels.*;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class CafeCoordinateList {
    private ArrayList<_CafeCoordinate> cafeCoordinate;

    public CafeCoordinateList(int id) {
        if(id == 0) {
            cafeCoordinate = ServerCommands.getCafeCoordinate(null);
        } else
        {
            cafeCoordinate = ServerCommands.getCafeCoordinate(String.valueOf(id));
        }
    }

    public ArrayList<_CafeCoordinate> getCafeCoordinate() {
        return cafeCoordinate;
    }

    public void setCafeCoordinate(ArrayList<_CafeCoordinate> cafeCoordinate) {
        this.cafeCoordinate = cafeCoordinate;
    }
}
