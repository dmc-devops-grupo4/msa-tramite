package edu.proyecto.repository.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import edu.proyecto.entity.ArchivoEntity;
import edu.proyecto.repository.ArchivoRepository;

@Repository
public class ArchivoRepositoryImpl implements ArchivoRepository {
    private RestTemplate restTemplate;

    @Value("${uri.service.archivo}")
    private String urlApiArchivo;

    public ArchivoRepositoryImpl(){
        restTemplate = new RestTemplate();
    }

    @Override
    public ArchivoEntity obtenerArchivo(String idArchivo) {
        ArchivoEntity archivo = restTemplate.getForObject(urlApiArchivo + "/api/v1/archivos/" + idArchivo, ArchivoEntity.class);
        System.out.println("archivo = " + archivo);
        return archivo;
    }
}
