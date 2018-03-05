package br.gov.sc.dive.teste.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.sc.dive.teste.dao.conexao.ProdutorEntityManager;
import br.gov.sc.dive.teste.dao.entidades.Animal;
import br.gov.sc.dive.teste.dao.entidades.Animal;
import br.gov.sc.dive.teste.services.dto.AnimalDTO;

@Stateless
public class AnimalModel extends AbstractModel<Animal> {

	private EntityManager em;

	public List<Animal> getTodos() {
		List<Animal> animais = null;
		try {
			animais = getEm().createNamedQuery(Animal.BUSCA_TODOS_ATIVOS).getResultList();
		} catch (Exception e) {
			logger.error("Erro ao buscar animais", e);
			throw e;
		}
		return animais;
	}

	public List<Animal> busca(AnimalDTO filtro) {
		List<Animal> animais = null;
		try {
			CriteriaBuilder cb = getEmPesquisa().getCriteriaBuilder();
			CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
			Root<Animal> f = cq.from(Animal.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (filtro != null) {
				if (filtro.getNome() != null) {
					predicates.add(cb.like(f.get("nome"), "%" + filtro.getNome() + "%"));
				}
			}
			cq.select(f).where(predicates.toArray(new Predicate[] {}));
			animais = getEmPesquisa().createQuery(cq).getResultList();

		} catch (Exception e) {
			logger.error("Erro ao buscar animal por intervalo de data de cadastro", e);
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
