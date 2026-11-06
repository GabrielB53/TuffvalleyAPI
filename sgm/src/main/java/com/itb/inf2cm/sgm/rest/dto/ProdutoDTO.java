package com.itb.inf2cm.sgm.rest.dto;


public class ProdutoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private String porcao;
	private String infoNutricional;
	private Long categoriaId;
	private String statusProduto;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getPorcao() {
		return porcao;
	}
	public void setPorcao(String porcao) {
		this.porcao = porcao;
	}
	public String getInfoNutricional() {
		return infoNutricional;
	}
	public void setInfoNutricional(String infoNutricional) {
		this.infoNutricional = infoNutricional;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getStatusProduto() {
		return statusProduto;
	}
	public void setStatusProduto(String statusProduto) {
		this.statusProduto = statusProduto;
	} 
	
}
