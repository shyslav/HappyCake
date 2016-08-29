package com.sukhaniuk.interfaces.impls;

import appmodels._Reservation;
import com.shyslav.server.ServerCommands;

import java.util.ArrayList;

/**
 * Created by Shyshkin Vladyslav on 16.05.2016.
 */
public class ReservationList {
    private ArrayList<_Reservation> reservations;

    public ReservationList() {
        reservations = ServerCommands.getReservation(null);
    }

    public ArrayList<_Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<_Reservation> reservations) {
        this.reservations = reservations;
    }
}
