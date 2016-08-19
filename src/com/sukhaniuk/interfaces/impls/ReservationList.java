package com.sukhaniuk.interfaces.impls;

import com.shyslav.models.reservation;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class ReservationList {
    private ArrayList<reservation> reservations;

    public ReservationList() {
        reservations = ServerCommands.getReservation(null);
    }

    public ArrayList<reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<reservation> reservations) {
        this.reservations = reservations;
    }
}
