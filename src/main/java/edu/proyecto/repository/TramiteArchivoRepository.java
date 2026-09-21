package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.TramiteArchivoEntity;

@Repository
public interface TramiteArchivoRepository extends JpaRepository<TramiteArchivoEntity,Integer> {

    @Query(value = "SELECT * FROM tramite_archivo WHERE id_tramite=:idTramite", nativeQuery = true)
    public List<TramiteArchivoEntity> buscarTramitesArchivoPorIdTramite(@Param("idTramite") Integer idTramite);

}


