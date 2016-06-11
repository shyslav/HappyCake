package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.reports;
import com.shyslav.server.comands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class ReportsList {
    private ArrayList<reports> report;

    public ReportsList(int id) {
        if(id == 0) {
            report = comands.getReport(null);
        } else
        {
            report = comands.getReport(String.valueOf(id));
        }
    }

    public ArrayList<reports> getReport() {
        return report;
    }

    public void setReport(ArrayList<reports> report) {
        this.report = report;
    }
}
