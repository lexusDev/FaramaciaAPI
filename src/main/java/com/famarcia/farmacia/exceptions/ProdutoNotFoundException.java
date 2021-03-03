package com.famarcia.farmacia.exceptions;

public class ProdutoNotFoundException  extends RuntimeException {
    public ProdutoNotFoundException(Long id) {
        super("Could not find products " + id);
    }
}
