package com.example.mauro.menudinacap;

import java.util.Date;

public class Almuerzo {

    private int almuerzo_id;
    private String nomAlmuerzo;
    private String infoNutri;
    private int precio;
    private Date fecha;

    public Almuerzo() {
    }

    public Almuerzo(int almuerzo_id, String nomAlmuerzo, String infoNutri, int precio, Date fecha) {
        this.almuerzo_id = almuerzo_id;
        this.nomAlmuerzo = nomAlmuerzo;
        this.infoNutri = infoNutri;
        this.precio = precio;
        this.fecha = fecha;
    }

    public int getAlmuerzo_id() {
        return almuerzo_id;
    }

    public void setAlmuerzo_id(int almuerzo_id) {
        this.almuerzo_id = almuerzo_id;
    }

    public String getNomAlmuerzo() {
        return nomAlmuerzo;
    }

    public void setNomAlmuerzo(String nomAlmuerzo) {
        this.nomAlmuerzo = nomAlmuerzo;
    }

    public String getInfoNutri() {
        return infoNutri;
    }

    public void setInfoNutri(String infoNutri) {
        this.infoNutri = infoNutri;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


}
