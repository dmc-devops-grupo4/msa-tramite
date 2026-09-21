package edu.proyecto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.proyecto.entity.TramiteEntity;

@Repository
public interface TramiteRepository extends JpaRepository<TramiteEntity,Integer> {

    @Query(value = "SELECT * FROM tramite WHERE activo=1", nativeQuery = true)
    public List<TramiteEntity> listarTramiteActivos();
    
    //consulta tramites por fecha
    @Query(value="SELECT * FROM tramite WHERE DATE(fecha) BETWEEN :fecha1 AND :fecha2 AND id_usuario=:idUsuario AND activo=1 ORDER BY fecha DESC", nativeQuery = true)  
    public List<TramiteEntity> listarPorFechasUsuario(@Param("fecha1")Date fecha1,@Param("fecha2")Date fecha2, @Param("idUsuario")Integer idUsuario);


}
