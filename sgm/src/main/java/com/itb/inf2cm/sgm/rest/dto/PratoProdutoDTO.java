package com.itb.inf2cm.sgm.rest.dto;

public class PratoProdutoDTO {
	private Long id;
	private String informacao;
	private String statusPratoProduto;
	private Long pratoId;
	private Long produtoId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public Long getPratoId() {
		return pratoId;
	}
	public void setPratoId(Long pratoId) {
		this.pratoId = pratoId;
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	
}
