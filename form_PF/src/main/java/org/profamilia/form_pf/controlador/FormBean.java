/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.profamilia.form_pf.controlador;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.profamilia.form_pf.dao.PersonaDAO;
import org.profamilia.form_pf.modelo.PersonaDTO;

import java.io.Serializable;
import java.sql.SQLException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named("formBean")
@ViewScoped
public class FormBean implements Serializable {

    private String tDocumento;
    private String numDocumento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String correo;

    private PersonaDAO personaDAO;

    public FormBean() {
        try {
            personaDAO = new PersonaDAO();
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "Error al conectar con la base de datos."));
        }
    }

    // Crear PersonaDTO a partir de los campos del formulario
    private PersonaDTO crearPersonaDTO() {
        return new PersonaDTO(tDocumento, numDocumento, nombre, apellido, telefono, direccion, correo);
    }

    public void guardar() {
        try {
            PersonaDTO persona = crearPersonaDTO();
            personaDAO.guardar(persona);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Persona guardada exitosamente"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "No se pudo guardar la persona."));
        }
    }

    public void actualizar() {
        try {
            PersonaDTO persona = crearPersonaDTO();
            personaDAO.actualizar(persona);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Persona actualizada exitosamente"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "No se pudo actualizar la persona."));
        }
    }

    public void eliminar() {
        try {
            personaDAO.eliminar(numDocumento);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Persona eliminada exitosamente"));
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "No se pudo eliminar la persona."));
        }
    }

    public void consultar() {
        try {
            PersonaDTO persona = personaDAO.consultar(numDocumento);
            if (persona != null) {
                this.tDocumento = persona.getTDocumento();
                this.nombre = persona.getNombre();
                this.apellido = persona.getApellido();
                this.telefono = persona.getTelefono();
                this.direccion = persona.getDireccion();
                this.correo = persona.getCorreo();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Persona encontrada: " + persona.getNombre()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
                                "No se encontró la persona con numDocumento: " + numDocumento));
            }
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "No se pudo consultar la persona."));
        }
   }

    // Método opcional para cerrar la conexión (generalmente no necesario con DAO bien gestionado)
    public void cerrarConexion() {
        try {
            personaDAO.cerrarConexion();
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            "Error al cerrar la conexión con la base de datos."));
        }
    }

    // Getters y Setters
    public String getTDocumento() {
        return tDocumento;
    }

    public void setTDocumento(String tDocumento) {
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
