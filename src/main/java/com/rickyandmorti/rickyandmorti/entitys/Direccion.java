package com.rickyandmorti.rickyandmorti.entitys;

import jakarta.persistence.Embeddable;

/**
 * Se utiliza para marcar una clase como una entidad embebible, lo que significa
 * que sus propiedades se pueden "incrustar" dentro de otra entidad sin crear
 * una tabla separada en la base de datos
 * 
 * 
 */
@Embeddable
public class Direccion {

    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;

    // Getters y Setters
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}