package br.gov.sc.dive.teste.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import br.gov.sc.dive.teste.dao.conexao.ProdutorEntityManager;
import br.gov.sc.dive.teste.dao.entidades.Animal;
import br.gov.sc.dive.teste.dao.entidades.Ficha;

@Stateless
public class AnimalDao extends AbstractDao<Animal> {

	private EntityManager em;
	
	public List<Animal> getTodos() {
		List<Animal> animais = null;
		try {
			QAnimal animal = QAnimal.animal;
			JPQLQuery query = new JPAQuery(getEmPesquisa());
			animais = query.from(animal).fetch();

		} catch (Exception e) {
			logger.error("Erro ao buscar animais", e);
			throw e;
		}
		return animais;
	}


	@Override
	public EntityManager getEm() {
		if (em == null) {
			em = new ProdutorEntityManager().criaEntityManager();
		}
		return em;
	}
	
	public EntityManager getEmPesquisa() {
		return new ProdutorEntityManager().criaEntityManager();
	}

	@Override
	public Class<Animal> getEntidade() {
		return Animal.class;
	}

}
