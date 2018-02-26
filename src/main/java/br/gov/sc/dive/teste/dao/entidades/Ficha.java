package br.gov.sc.dive.teste.dao.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ficha")
public class Ficha implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ficha")
	private Integer idFicha;

	@Column(name = "dt_cadastro")
	private Date dtCadastro;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "fl_ativo")
	private Integer flAtivo = 1;

	@OneToMany(mappedBy = "ficha")
	private List<Animal> animais;

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

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtCadastro == null) ? 0 : dtCadastro.hashCode());
		result = prime * result + ((flAtivo == null) ? 0 : flAtivo.hashCode());
		result = prime * result + ((idFicha == null) ? 0 : idFicha.hashCode());
		result = prime * result + ((observacao == null) ? 0 : observacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ficha other = (Ficha) obj;
		if (dtCadastro == null) {
			if (other.dtCadastro != null)
				return false;
		} else if (!dtCadastro.equals(other.dtCadastro))
			return false;
		if (flAtivo == null) {
			if (other.flAtivo != null)
				return false;
		} else if (!flAtivo.equals(other.flAtivo))
			return false;
		if (idFicha == null) {
			if (other.idFicha != null)
				return false;
		} else if (!idFicha.equals(other.idFicha))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ficha [idFicha=" + idFicha + ", dtCadastro=" + dtCadastro + ", observacao=" + observacao + ", flAtivo="
				+ flAtivo + "]";
	}

}
