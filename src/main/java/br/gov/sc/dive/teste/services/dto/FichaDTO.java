package br.gov.sc.dive.teste.services.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class FichaDTO implements AbstractDTO{
	private Integer idFicha;

	private Date dtCadastro;

	private String observacao;

	private Integer flAtivo = 1;

	private List<AnimalDTO> animais;

	public Integer getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(Integer idFicha) {
		this.idFicha = idFicha;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getFlAtivo() {
		return flAtivo;
	}

	public void setFlAtivo(Integer flAtivo) {
		this.flAtivo = flAtivo;
	}

	public List<AnimalDTO> getAnimais() {
		if(animais == null) {
			animais = new ArrayList<AnimalDTO>();
		}
		return animais;
	}

	public void setAnimais(List<AnimalDTO> animais) {
		this.animais = animais;
	}

	@JsonIgnore
	@Override
	public Integer getId() {
		return this.idFicha;
	}

}
