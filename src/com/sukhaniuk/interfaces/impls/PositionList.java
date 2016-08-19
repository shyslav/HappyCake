package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.position;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class PositionList {
    private ArrayList<position> positionList;

    public PositionList(int id) {
        if(id == 0) {
            positionList = ServerCommands.getPosition(null);
        } else
        {
            positionList = ServerCommands.getPosition(String.valueOf(id));
        }
    }

    public ArrayList<position> getPositionList() {
        return positionList;
    }

    public void setPositionList(ArrayList<position> positionList) {
        this.positionList = positionList;
    }
}
