package br.gov.sc.dive.teste.services.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AnimalDTO implements AbstractDTO{
	private Integer idAnimal;

	private String nome;

	private Integer flAtivo = 1;

	public Integer getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Integer idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(Integer flAtivo) {
		this.flAtivo = flAtivo;
	}
	
	@JsonIgnore
	@Override
	public Integer getId() {
		return this.idAnimal;
	}

}
