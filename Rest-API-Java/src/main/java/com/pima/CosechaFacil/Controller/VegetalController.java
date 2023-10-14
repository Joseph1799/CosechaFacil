package com.pima.CosechaFacil.Controller;

import com.pima.CosechaFacil.Model.Vegetal;
import com.pima.CosechaFacil.Repository.VegetalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/vegetales")
public class VegetalController {

    private final VegetalRepository vegetalRepository;

    @Autowired
    public VegetalController(VegetalRepository vegetalRepository) {
        this.vegetalRepository = vegetalRepository;
    }

    @GetMapping
    public ResponseEntity<List<Vegetal>> obtenerTodosLosVegetales() {
        List<Vegetal> vegetales = vegetalRepository.findAll();
        return new ResponseEntity<>(vegetales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vegetal> obtenerVegetalPorId(@PathVariable Long id) {
        Optional<Vegetal> vegetal = vegetalRepository.findById(id);
        if (vegetal.isPresent()) {
            return new ResponseEntity<>(vegetal.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Vegetal> agregarVegetal(@RequestBody Vegetal vegetal) {
        Vegetal nuevoVegetal = vegetalRepository.save(vegetal);
        return new ResponseEntity<>(nuevoVegetal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vegetal> actualizarVegetal(@PathVariable Long id, @RequestBody Vegetal vegetalActualizado) {
        Optional<Vegetal> existenteVegetal = vegetalRepository.findById(id);
        if (existenteVegetal.isPresent()) {
            Vegetal actual = existenteVegetal.get();
            actual.setFecha(vegetalActualizado.getFecha());
            actual.setNombre(vegetalActualizado.getNombre());
            actual.setUnidad(vegetalActualizado.getUnidad());
            actual.setPrecioMinimo(vegetalActualizado.getPrecioMinimo());
            actual.setPrecioMaximo(vegetalActualizado.getPrecioMaximo());
            actual.setModa(vegetalActualizado.getModa());
            actual.setPromedio(vegetalActualizado.getPromedio());

            vegetalRepository.save(actual);

            return new ResponseEntity<>(actual, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarVegetal(@PathVariable Long id) {
        try {
            vegetalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
