package com.itb.inf2cm.sgm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

	

@Entity
@Table(name = "PratoProduto")
public class PratoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String informacao;

    private String statusPratoProduto; // ATIVO, CARDAPIO ou INATIVO

    @ManyToOne
    @JoinColumn(name = "prato_id")
    private Prato prato;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public String getStatusPratoProduto() {
        return statusPratoProduto;
    }

    public void setStatusPratoProduto(String statusPratoProduto) {
        this.statusPratoProduto = statusPratoProduto;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
