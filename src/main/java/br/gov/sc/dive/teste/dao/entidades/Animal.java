package br.gov.sc.dive.teste.dao.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
@NamedQuery(name = Animal.BUSCA_TODOS_ATIVOS, query = "select a from Animal a where a.flAtivo = 1 order by a.nome")
public class Animal implements Entidade {

	public static final String BUSCA_TODOS_ATIVOS = "Animal.buscaAtivos";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animal")
	private Integer idAnimal;

	@Column(name = "nome")
	private String nome;

	@Column(name = "fl_ativo")
	private Integer flAtivo = 1;

	@ManyToMany(mappedBy = "animais", targetEntity = Ficha.class)
	private List<Ficha> fichas;

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

	public List<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(List<Ficha> fichas) {
		this.fichas = fichas;
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
