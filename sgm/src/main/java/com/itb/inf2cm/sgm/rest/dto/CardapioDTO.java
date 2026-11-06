package com.itb.inf2cm.sgm.rest.dto;

import java.time.LocalDate;

public class CardapioDTO {
    private Long id;
    private String nome;
    private Long pratoId;         // <-- substitui o objeto Prato pelo id
    private LocalDate diaServido;
    private String statusCardapio;
    private String foto; 
    
    // getters e setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPratoId() {
        return pratoId;
    }
    public void setPratoId(Long pratoId) {
        this.pratoId = pratoId;
    }

    public LocalDate getDiaServido() {
        return diaServido;
    }
    public void setDiaServido(LocalDate diaServido) {
        this.diaServido = diaServido;
    }

    public String getStatusCardapio() {
        return statusCardapio;
    }
    public void setStatusCardapio(String statusCardapio) {
        this.statusCardapio = statusCardapio;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
}
