package br.gov.sc.dive.teste.dao;

import java.util.Date;

public class FiltroFichaDTO {

	private Integer id;
	private Date dtInicial;
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
