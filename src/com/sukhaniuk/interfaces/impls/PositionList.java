package com.sukhaniuk.interfaces.impls;

import appmodels._Positions;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class PositionList {
    private ArrayList<_Positions> positionList;

    public PositionList(int id) {
        if(id == 0) {
            positionList = ServerCommands.getPosition(null);
        } else
        {
            positionList = ServerCommands.getPosition(String.valueOf(id));
        }
    }

    public ArrayList<_Positions> getPositionList() {
        return positionList;
    }

    public void setPositionList(ArrayList<_Positions> positionList) {
        this.positionList = positionList;
    }
}
