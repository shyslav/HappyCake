package com.sukhaniuk.interfaces.impls;

import appmodels.reports;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class ReportsList {
    private ArrayList<reports> report;

    public ReportsList(int id) {
        if(id == 0) {
            report = ServerCommands.getReport(null);
        } else
        {
            report = ServerCommands.getReport(String.valueOf(id));
        }
    }

    public ArrayList<reports> getReport() {
        return report;
    }

    public void setReport(ArrayList<reports> report) {
        this.report = report;
    }
}
