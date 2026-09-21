package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.OficinaEntity;


@Repository
public interface OficinaRepository extends JpaRepository<OficinaEntity, Integer> {
     @Query(value = "SELECT * FROM oficina WHERE activo=1", nativeQuery = true)
     public List<OficinaEntity> consultarOficinaActivos(); 

     

}
