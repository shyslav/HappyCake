package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.cafeCoordinate;
import com.shyslav.models.position;
import com.shyslav.server.comands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class PositionList {
    private ArrayList<position> positionList;

    public PositionList(int id) {
        if(id == 0) {
            positionList = comands.getPosition(null);
        } else
        {
            positionList = comands.getPosition(String.valueOf(id));
        }
    }

    public ArrayList<position> getPositionList() {
        return positionList;
    }

    public void setPositionList(ArrayList<position> positionList) {
        this.positionList = positionList;
    }
}
