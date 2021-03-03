package com.famarcia.farmacia.models;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fornecedores {
    private @Id @GeneratedValue Long codigo;
    private String descricao;

    Fornecedores() {}

    public Fornecedores(String descricao) {
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Fornecedores))
            return false;
            Fornecedores fornecedores = (Fornecedores) o;
        return Objects.equals(this.codigo, fornecedores.codigo) && Objects.equals(this.descricao, fornecedores.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigo, this.descricao);
    }
}
