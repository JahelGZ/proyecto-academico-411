package com.uniajc.dao;

import com.uniajc.db.ConexionDatabase;
import com.uniajc.modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteDao {
    private static final String TABLA = "\"practica-mvc\".estudiantes";
    private static final String SELECCION = "SELECT id, name, lastname, email FROM " + TABLA;

    public void guardar(Estudiante estudiante) {
        String sql = "INSERT INTO " + TABLA + " (name, lastname, email) VALUES (?, ?, ?)";

        try (PreparedStatement consulta = obtenerConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            asignarDatos(consulta, estudiante);
            consulta.executeUpdate();
            try (ResultSet claves = consulta.getGeneratedKeys()) {
                if (claves.next()) {
                    estudiante.setIdEstudiante(claves.getInt("id"));
                }
            }
        } catch (SQLException error) {
            throw new IllegalStateException("No se pudo registrar el estudiante.", error);
        }
    }

    public List<Estudiante> obtenerTodos() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (PreparedStatement consulta = obtenerConexion().prepareStatement(SELECCION + " ORDER BY id");
             ResultSet filas = consulta.executeQuery()) {
            while (filas.next()) {
                estudiantes.add(mapearEstudiante(filas));
            }
        } catch (SQLException error) {
            throw new IllegalStateException("No se pudieron listar los estudiantes.", error);
        }
        return estudiantes;
    }

    public Optional<Estudiante> buscarPorId(int id) {
        try (PreparedStatement consulta = obtenerConexion().prepareStatement(SELECCION + " WHERE id = ?")) {
            consulta.setInt(1, id);
            try (ResultSet filas = consulta.executeQuery()) {
                return filas.next() ? Optional.of(mapearEstudiante(filas)) : Optional.empty();
            }
        } catch (SQLException error) {
            throw new IllegalStateException("No se pudo buscar el estudiante.", error);
        }
    }

    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE " + TABLA + " SET name = ?, lastname = ?, email = ? WHERE id = ?";

        try (PreparedStatement consulta = obtenerConexion().prepareStatement(sql)) {
            asignarDatos(consulta, estudiante);
            consulta.setInt(4, estudiante.getIdEstudiante());
            consulta.executeUpdate();
        } catch (SQLException error) {
            throw new IllegalStateException("No se pudo actualizar el estudiante.", error);
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM " + TABLA + " WHERE id = ?";

        try (PreparedStatement consulta = obtenerConexion().prepareStatement(sql)) {
            consulta.setInt(1, id);
            consulta.executeUpdate();
        } catch (SQLException error) {
            throw new IllegalStateException("No se pudo eliminar el estudiante.", error);
        }
    }

    private Connection obtenerConexion() throws SQLException {
        // ConexionDatabase administra la conexión compartida.
        Connection conexion = ConexionDatabase.getConnection();
        if (conexion == null || conexion.isClosed()) {
            throw new SQLException("No hay una conexión activa a la base de datos.");
        }
        return conexion;
    }

    private void asignarDatos(PreparedStatement consulta, Estudiante estudiante) throws SQLException {
        consulta.setString(1, estudiante.getNombre());
        consulta.setString(2, estudiante.getApellido());
        consulta.setString(3, estudiante.getEmail());
    }

    private Estudiante mapearEstudiante(ResultSet fila) throws SQLException {
        return new Estudiante(fila.getInt("id"), fila.getString("name"),
                fila.getString("lastname"), fila.getString("email"));
    }
}
