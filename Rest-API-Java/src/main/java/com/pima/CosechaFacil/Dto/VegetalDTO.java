package com.pima.CosechaFacil.Dto;

public class VegetalDTO {

    private Long id;
    private String nombre;
    private String unidad;
    private double precioCompra;
    private double precioVenta;
    private double precioPromedio;
    private double precioUltimaVenta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(double precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public double getPrecioUltimaVenta() {
        return precioUltimaVenta;
    }

    public void setPrecioUltimaVenta(double precioUltimaVenta) {
        this.precioUltimaVenta = precioUltimaVenta;
    }

    
}

