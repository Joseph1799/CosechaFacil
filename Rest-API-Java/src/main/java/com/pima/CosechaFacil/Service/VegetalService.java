package com.pima.CosechaFacil.Service;

import com.pima.CosechaFacil.Model.Vegetal;
import com.pima.CosechaFacil.Repository.VegetalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VegetalService {

    private final VegetalRepository vegetalRepository;

    @Autowired
    public VegetalService(VegetalRepository vegetalRepository) {
        this.vegetalRepository = vegetalRepository;
    }

    public List<Vegetal> obtenerTodosLosVegetales() {
        return vegetalRepository.findAll();
    }

    public Optional<Vegetal> obtenerVegetalPorId(Long id) {
        return vegetalRepository.findById(id);
    }

    public Vegetal guardarVegetal(Vegetal vegetal) {
        return vegetalRepository.save(vegetal);
    }

    public void eliminarVegetal(Long id) {
        vegetalRepository.deleteById(id);
    }
}

