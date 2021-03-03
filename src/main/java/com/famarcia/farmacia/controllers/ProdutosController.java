package com.famarcia.farmacia.controllers;

import com.famarcia.farmacia.assemblers.ProdutosModelAssembler;
import com.famarcia.farmacia.exceptions.ProdutoNotFoundException;
import com.famarcia.farmacia.models.Produtos;
import com.famarcia.farmacia.repositories.ProdutosRepository;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProdutosController implements Serializable {
    private final ProdutosRepository repository;
    private final ProdutosModelAssembler assembler;

    ProdutosController(ProdutosRepository repository, ProdutosModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/products")
    public CollectionModel<EntityModel<Produtos>> all() {

        List<EntityModel<Produtos>> produtos = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(produtos, linkTo(methodOn(ProdutosController.class).all()).withSelfRel());
    }

    @GetMapping("/products/{id}")
    public EntityModel<Produtos> one(@PathVariable Long id) {

        Produtos produtos = repository.findById(id) //
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        return assembler.toModel(produtos);
    }

    @PostMapping("/products")
    ResponseEntity<?> newProvider(@RequestBody Produtos newProduct) {

        EntityModel<Produtos> entityModel = assembler.toModel(repository.save(newProduct));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/products/{id}")
    ResponseEntity<?> replaceProvider(@RequestBody Produtos newProduct, @PathVariable Long id) {

        Produtos updatedProduct = repository.findById(id) //
                .map(provider -> {
                    provider.setDescricao(newProduct.getDescricao());
                    return repository.save(provider);
                }) //
                .orElseGet(() -> {
                    newProduct.setCodigo(id);
                    return repository.save(newProduct);
                });

        EntityModel<Produtos> entityModel = assembler.toModel(updatedProduct);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<?> deleteProvider(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}