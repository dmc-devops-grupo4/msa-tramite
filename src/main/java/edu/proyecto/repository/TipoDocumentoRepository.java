package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.TipoDocumentoEntity;


@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity,Integer>{
     @Query(value = "SELECT * FROM tipo_documento WHERE activo=1", nativeQuery = true)
    public List<TipoDocumentoEntity> consultarTipoDocumentoActivos();
}
