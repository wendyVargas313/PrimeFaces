/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.profamilia.form_pf.dao;

import org.profamilia.form_pf.modelo.PersonaDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private Connection connection;

    // Configurar la conexión con la base de datos MySQL
    public PersonaDAO() throws SQLException {
        try {
            String jdbcURL = "jdbc:derby:profamilia_db;create=true";
            connection = DriverManager.getConnection(jdbcURL);
            crearTabla();
        } catch (SQLException e) {
            throw new SQLException("Error al conectar con la base de datos.", e);
        }
    }

    private void crearTabla() throws SQLException {
        String crearTablaSQL = "CREATE TABLE formulario ("
                + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, "
                + "INCREMENT BY 1),"
                + "tDocumento VARCHAR(50)"
                + "numDocumento VARCHAR(50)"
                + "nombre VARCHAR(50)"
                + "apellido VARCHAR(50)"
                + "telefono VARCHAR(50)"
                + "direccion VARCHAR(50)"
                + "correo VARCHAR(50))";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(crearTablaSQL);
        } catch (SQLException e) {
            if (!e.getSQLState().equals("XOY32")) //codigo de error cuando la tabla ya existe
            {
                throw e;
            }
        }

    }

    // Método para guardar una nueva persona en la base de datos
    public void guardar(PersonaDTO persona) throws SQLException {
        String query = "INSERT INTO formulario (tDocumento, numDocumento, nombre, "
                + "apellido, telefono, direccion, correo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, persona.gettDocumento());
            stmt.setString(2, persona.getNumDocumento());
            stmt.setString(3, persona.getNombre());
            stmt.setString(4, persona.getApellido());
            stmt.setString(5, persona.getTelefono());
            stmt.setString(6, persona.getDireccion());
            stmt.setString(7, persona.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al guardar la persona.", e);
        }
    }

    // Método para actualizar una persona existente en la base de datos
    public void actualizar(PersonaDTO persona) throws SQLException {
        String query = "UPDATE formulario SET tDocumento = ?, nombre = ?, "
                + "apellido = ?, telefono = ?, direccion = ?, correo = ? "
                + "WHERE numDocumento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, persona.gettDocumento());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getTelefono());
            stmt.setString(5, persona.getDireccion());
            stmt.setString(6, persona.getCorreo());
            stmt.setString(7, persona.getNumDocumento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar la persona.", e);
        }
    }

    // Método para eliminar una persona de la base de datos utilizando el número de documento
    public void eliminar(String numDocumento) throws SQLException {
        String query = "DELETE FROM formulario WHERE numDocumento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, numDocumento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar la persona.", e);
        }
    }

    // Método para consultar una persona en la base de datos por su número de documento
    public PersonaDTO consultar(String numDocumento) throws SQLException {
        String query = "SELECT * FROM formulario WHERE numDocumento = ?";
        PersonaDTO persona = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, numDocumento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                persona = new PersonaDTO(
                        rs.getString("tDocumento"),
                        rs.getString("numDocumento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error al consultar la persona.", e);
        }
        return persona;
    }

    // Método para obtener una lista de todas las personas en la base de datos
    public List<PersonaDTO> listarPersonas() throws SQLException {
        String query = "SELECT * FROM formulario";
        List<PersonaDTO> listaPersonas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PersonaDTO persona = new PersonaDTO(
                        rs.getString("tDocumento"),
                        rs.getString("numDocumento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("correo")
                );
                listaPersonas.add(persona);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar las personas.", e);
        }
        return listaPersonas;
    }

    // Cerrar la conexión cuando no sea necesaria
    public void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
