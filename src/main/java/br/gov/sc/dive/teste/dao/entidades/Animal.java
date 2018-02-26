package br.gov.sc.dive.teste.dao.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "task")
public class Animal implements Entidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animal")
	private Integer idAnimal;

	@Column(name = "nome")
	private String nome;
	
	@Column(name = "fl_ativo")
	private Integer flAtivo = 1;
	

	@ManyToOne
	@JoinColumn(name = "id_ficha")
	private Ficha ficha;

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
	
	public Ficha getFicha() {
		return ficha;
	}
	
	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flAtivo == null) ? 0 : flAtivo.hashCode());
		result = prime * result + ((idAnimal == null) ? 0 : idAnimal.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Animal other = (Animal) obj;
		if (flAtivo == null) {
			if (other.flAtivo != null)
				return false;
		} else if (!flAtivo.equals(other.flAtivo))
			return false;
		if (idAnimal == null) {
			if (other.idAnimal != null)
				return false;
		} else if (!idAnimal.equals(other.idAnimal))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [idAnimal=" + idAnimal + ", nome=" + nome + ", flAtivo=" + flAtivo + "]";
	}
	
	

	
}
