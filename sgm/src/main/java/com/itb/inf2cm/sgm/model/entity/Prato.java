package com.itb.inf2cm.sgm.model.entity;
 
 
import java.util.List;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "Prato")
public class Prato {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    private String nome;
    private String descricao;
	private String principal;
	private String secundario;
	private String acompanhamento;
    private String statusPrato; // ATIVO ou INATIVO
 
    // Relacionamento com PratoProduto
    @OneToMany(mappedBy = "prato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PratoProduto> pratoProdutos;
 
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
 
    public String getDescricao() {
        return descricao;
    }
 
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
 
    public String getPrincipal() {
		return principal;
	}
 
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
 
	public String getSecundario() {
		return secundario;
	}
 
	public void setSecundario(String secundario) {
		this.secundario = secundario;
	}
 
	public String getAcompanhamento() {
		return acompanhamento;
	}
 
	public void setAcompanhamento(String acompanhamento) {
		this.acompanhamento = acompanhamento;
	}
 
	public String getStatusPrato() {
        return statusPrato;
    }
 
    public void setStatusPrato(String statusPrato) {
        this.statusPrato = statusPrato;
    }
 
    public List<PratoProduto> getPratoProdutos() {
        return pratoProdutos;
    }
 
    public void setPratoProdutos(List<PratoProduto> pratoProdutos) {
        this.pratoProdutos = pratoProdutos;
    }
}
 