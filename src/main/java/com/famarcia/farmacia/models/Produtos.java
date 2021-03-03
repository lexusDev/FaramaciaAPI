package com.famarcia.farmacia.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Produtos implements Serializable {
    private @Id @GeneratedValue Long codigo;
    private String descricao;
    private Long quantidade;
    private Double preco;

    @ManyToMany
    private List<Fornecedores> fornecedores;

    Produtos(){}

    public Produtos(String descricao, Long quantidade, Double preco, List<Fornecedores> fornecedores) {

        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedores = fornecedores;
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
    public Long getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public List<Fornecedores> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Fornecedores> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Produtos))
            return false;
            Produtos produtos = (Produtos) o;
        return Objects.equals(this.codigo, produtos.codigo) && Objects.equals(this.descricao, produtos.descricao)
            && Objects.equals(this.preco, produtos.preco) && Objects.equals(this.fornecedores, produtos.fornecedores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codigo, this.descricao, this.preco, this.fornecedores);
    }
}