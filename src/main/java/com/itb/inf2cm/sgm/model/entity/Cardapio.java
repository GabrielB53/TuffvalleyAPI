package com.itb.inf2cm.sgm.model.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Cardapio")
public class Cardapio {

    public Cardapio() {
    }

    public Cardapio(long id, String nome, LocalDate diaServido, String statusCardapio, Prato prato, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.diaServido = diaServido;
        this.statusCardapio = statusCardapio;
        this.prato = prato;
        this.foto = foto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "diaServido", nullable = false)
    private LocalDate diaServido;

    @Column(name = "statusCardapio", nullable = false, length = 10)
    private String statusCardapio; // ATIVO ou INATIVO

    @ManyToOne
    @JoinColumn(name = "prato_id", nullable = false)
    private Prato prato;  

    @Column(name = "foto", nullable = true)
    private byte[] foto;  // Foto do cardápio armazenada como array de bytes (binário)

    @Transient
    private String mensagemErro = "";

    @Transient
    private boolean isValid = true;

    // Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDiaServido() {
        return diaServido;
    }

    public void setDiaServido(LocalDate string) {
        this.diaServido = string;
    }

    public String getStatusCardapio() {
        return statusCardapio;
    }

    public void setStatusCardapio(String statusCardapio) {
        this.statusCardapio = statusCardapio;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public boolean validarCardapio() {
        if (nome == null || nome.isEmpty()) {
            mensagemErro += "O nome do cardápio é obrigatório. ";
            isValid = false;
        }
        if (statusCardapio == null || statusCardapio.isEmpty()) {
            mensagemErro += "O status do cardápio (ATIVO ou INATIVO) é obrigatório. ";
            isValid = false;
        }
        if (prato == null) {
            mensagemErro += "O prato associado é obrigatório. ";
            isValid = false;
        }
        return isValid;
    }
}
