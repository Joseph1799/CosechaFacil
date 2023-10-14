package com.pima.CosechaFacil.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "precios_mayoristas")
public class Vegetal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_de_plaza")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "producto")
    private String nombre;

    @Column(name = "unidad_comercializacion")
    private String unidad;

    @Column(name = "minimo")
    private double precioMinimo;

    @Column(name = "maximo")
    private double precioMaximo;

    @Column(name = "moda")
    private double moda;

    @Column(name = "promedio")
    private double promedio;

    public Vegetal() {
    }

    public Vegetal(Long id, Date fecha, String nombre, String unidad, double precioMinimo, double precioMaximo, double moda, double promedio) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.unidad = unidad;
        this.precioMinimo = precioMinimo;
        this.precioMaximo = precioMaximo;
        this.moda = moda;
        this.promedio = promedio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public double getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public double getModa() {
        return moda;
    }

    public void setModa(double moda) {
        this.moda = moda;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

}
