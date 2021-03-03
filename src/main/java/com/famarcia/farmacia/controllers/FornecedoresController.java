package com.famarcia.farmacia.controllers;

import com.famarcia.farmacia.assemblers.ForncedoresModelAssembler;
import com.famarcia.farmacia.exceptions.ProdutoNotFoundException;
import com.famarcia.farmacia.models.Fornecedores;
import com.famarcia.farmacia.repositories.ForncedoresRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class FornecedoresController {
    private final ForncedoresRepository repository;
    private final ForncedoresModelAssembler assembler;

    FornecedoresController(ForncedoresRepository repository, ForncedoresModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/providers")
    public CollectionModel<EntityModel<Fornecedores>> all() {

        List<EntityModel<Fornecedores>> fornecedores = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(fornecedores, linkTo(methodOn(FornecedoresController.class).all()).withSelfRel());
    }

    @GetMapping("/providers/{id}")
    public EntityModel<Fornecedores> one(@PathVariable Long id) {

        Fornecedores fornecedores = repository.findById(id) //
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        return assembler.toModel(fornecedores);
    }

    @PostMapping("/providers")
    ResponseEntity<?> newProvider(@RequestBody Fornecedores newProvider) {

        EntityModel<Fornecedores> entityModel = assembler.toModel(repository.save(newProvider));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceProvider(@RequestBody Fornecedores newProvider, @PathVariable Long id) {

        Fornecedores updatedProvider = repository.findById(id) //
                .map(provider -> {
                    provider.setDescricao(newProvider.getDescricao());
                    return repository.save(provider);
                }) //
                .orElseGet(() -> {
                    newProvider.setCodigo(id);
                    return repository.save(newProvider);
                });

        EntityModel<Fornecedores> entityModel = assembler.toModel(updatedProvider);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/providers/{id}")
    ResponseEntity<?> deleteProvider(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}