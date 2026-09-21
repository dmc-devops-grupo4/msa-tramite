package edu.proyecto.repository;

import edu.proyecto.entity.ArchivoEntity;

public interface ArchivoRepository {
    public ArchivoEntity obtenerArchivo(String idArchivo);
}
