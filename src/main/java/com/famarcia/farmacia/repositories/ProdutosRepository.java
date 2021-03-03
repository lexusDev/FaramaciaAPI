package com.famarcia.farmacia.repositories;

import com.famarcia.farmacia.models.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
}
