package com.famarcia.farmacia.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.famarcia.farmacia.controllers.FornecedoresController;
import com.famarcia.farmacia.models.Fornecedores;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ForncedoresModelAssembler implements RepresentationModelAssembler<Fornecedores, EntityModel<Fornecedores>> {

    @Override
    public EntityModel<Fornecedores> toModel(Fornecedores fornecedores) {

        return EntityModel.of(fornecedores, //
                linkTo(methodOn(FornecedoresController.class).one(fornecedores.getCodigo())).withSelfRel(),
                linkTo(methodOn(FornecedoresController.class).all()).withRel("fornecedores"));
    }
}