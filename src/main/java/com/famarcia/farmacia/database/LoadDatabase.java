package com.famarcia.farmacia.database;


import com.famarcia.farmacia.models.Fornecedores;
import com.famarcia.farmacia.models.Produtos;
import com.famarcia.farmacia.repositories.ForncedoresRepository;
import com.famarcia.farmacia.repositories.ProdutosRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ForncedoresRepository forncedoresRepository, ProdutosRepository produtosRepository) {

        return args -> {
            Fornecedores bilbo = new Fornecedores("Bilbo Baggins");
            Fornecedores frodo = new Fornecedores("Frodo Baggins");

            forncedoresRepository.save(bilbo);
            forncedoresRepository.save(frodo);

            forncedoresRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

            List<Fornecedores> fornecedores = new ArrayList<Fornecedores>();
            List<Fornecedores> fornecedores2 = new ArrayList<Fornecedores>();
            fornecedores.add(bilbo);
            fornecedores.add(frodo);
            fornecedores2.add(bilbo);
            fornecedores2.add(frodo);

            produtosRepository.save(new Produtos("Escitalopram", 12L, 14.2, fornecedores));
            produtosRepository.save(new Produtos("Dipirona", 14L, 17.8, fornecedores2));

            produtosRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}