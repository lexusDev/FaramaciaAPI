package com.famarcia.farmacia.assemblers;

import com.famarcia.farmacia.controllers.ProdutosController;
import com.famarcia.farmacia.models.Produtos;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProdutosModelAssembler implements RepresentationModelAssembler<Produtos, EntityModel<Produtos>> {
    @Override
    public EntityModel<Produtos> toModel(Produtos produtos) {

        return EntityModel.of(produtos, //
                linkTo(methodOn(ProdutosController.class).one(produtos.getCodigo())).withSelfRel(),
                linkTo(methodOn(ProdutosController.class).all()).withRel("produtos"));
    }
}
