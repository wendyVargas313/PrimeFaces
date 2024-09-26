/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.profamilia.form_pf.modelo;

/**
 *
 * @author iwend
 */
public class PersonaDTO {

    private String tDocumento;
    private String numDocumento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String correo;

    // Constructor
    public PersonaDTO(String tDocumento, String numDocumento, String nombre,
            String apellido, String telefono, String direccion, String correo) {

        this.tDocumento = tDocumento;
        this.numDocumento = numDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    // Getters y Setters
    public String gettDocumento() {
        return tDocumento;
    }

    public void settDocumento(String tDocumento) {
        this.tDocumento = tDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
