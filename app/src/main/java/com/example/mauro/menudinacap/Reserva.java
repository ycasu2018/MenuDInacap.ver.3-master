package com.example.mauro.menudinacap;

import java.util.Date;

public class Reserva {

    private int reserva_id;
    private int user_id;
    private int almuerzo_id;
    private Date fecha_Reserva;
    private String estado_Reserva;

    public Reserva() {
    }

    public Reserva(int reserva_id, int user_id, int almuerzo_id, Date fecha_Reserva, String estado_Reserva) {
        this.reserva_id = reserva_id;
        this.user_id = user_id;
        this.almuerzo_id = almuerzo_id;
        this.fecha_Reserva = fecha_Reserva;
        this.estado_Reserva = estado_Reserva;
    }

    public int getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(int reserva_id) {
        this.reserva_id = reserva_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAlmuerzo_id() {
        return almuerzo_id;
    }

    public void setAlmuerzo_id(int almuerzo_id) {
        this.almuerzo_id = almuerzo_id;
    }

    public Date getFecha_Reserva() {
        return fecha_Reserva;
    }

    public void setFecha_Reserva(Date fecha_Reserva) {
        this.fecha_Reserva = fecha_Reserva;
    }

    public String getEstado_Reserva() {
        return estado_Reserva;
    }

    public void setEstado_Reserva(String estado_Reserva) {
        this.estado_Reserva = estado_Reserva;
    }
}
