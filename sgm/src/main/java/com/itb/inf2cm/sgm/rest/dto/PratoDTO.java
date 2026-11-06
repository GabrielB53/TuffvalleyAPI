package com.itb.inf2cm.sgm.rest.dto;
 
public class PratoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private String principal;
	private String secundario;
	private String acompanhamento;
	private String statusPrato;
	
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
}