package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.Genero;
import com.rd.projetointegrador.rdservicesapi.entity.GeneroEntity;
import com.rd.projetointegrador.rdservicesapi.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    //EntityToDto
    public Genero conversaoGeneroDTO(GeneroEntity generoEntity, Genero genero) {
        genero.setIdGenero(generoEntity.getIdGenero());
        genero.setDsGenero(generoEntity.getDsGenero());

        return genero;

    }
    //DtoToEntity
    public GeneroEntity conversaoGeneroEntity(Genero genero, GeneroEntity generoEntity) {
        generoEntity.setIdGenero(genero.getIdGenero());
        generoEntity.setDsGenero(genero.getDsGenero());

        return generoEntity;

    }

    public GeneroEntity getGenero(BigInteger idGenero) {
        System.out.println("Id: " + idGenero);
        Optional<GeneroEntity> optional = repository.findById(idGenero);
        return optional.get();
    }

    public List<GeneroEntity> getGeneros() {
        return repository.findAll();
    }
}
