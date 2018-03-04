package br.gov.sc.dive.teste.services.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FiltroFichaDTO {

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtInicial;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtFinal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

}
