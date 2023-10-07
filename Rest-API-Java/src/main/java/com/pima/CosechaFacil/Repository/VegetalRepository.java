package com.pima.CosechaFacil.Repository;

import com.pima.CosechaFacil.Model.Vegetal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetalRepository extends JpaRepository<Vegetal, Long> {

}

