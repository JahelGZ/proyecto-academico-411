package com.uniajc.servicios;

import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;

import java.util.List;
import java.util.Optional;

public class EstudianteService {
    private final EstudianteDao estudianteDao;

    public EstudianteService() {
        this(new EstudianteDao());
    }

    public EstudianteService(EstudianteDao estudianteDao) {
        if (estudianteDao == null) {
            throw new IllegalArgumentException("El DAO de estudiantes es obligatorio.");
        }
        this.estudianteDao = estudianteDao;
    }

    public void registrarEstudiante(Estudiante estudiante) {
        validarEstudiante(estudiante);
        estudianteDao.guardar(estudiante);
    }

    public List<Estudiante> obtenerTodos() {
        return estudianteDao.obtenerTodos();
    }

    public Optional<Estudiante> buscarPorId(int id) {
        validarId(id);
        return estudianteDao.buscarPorId(id);
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        validarEstudiante(estudiante);
        validarId(estudiante.getIdEstudiante());
        estudianteDao.actualizar(estudiante);
    }

    public void eliminarEstudiante(int id) {
        validarId(id);
        estudianteDao.eliminar(id);
    }

    private void validarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante es obligatorio.");
        }
        if (estudiante.getNombre() == null || estudiante.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
    }

    private void validarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id del estudiante debe ser mayor que cero.");
        }
    }
}
