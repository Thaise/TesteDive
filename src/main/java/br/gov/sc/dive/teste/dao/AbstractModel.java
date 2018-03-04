package br.gov.sc.dive.teste.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.sc.dive.teste.dao.entidades.Entidade;


public abstract class AbstractModel<T extends Entidade> {
	
	protected Logger logger = LoggerFactory.getLogger(AbstractModel.class);


	public void insere(T c) throws Exception {
		try {
			getEm().getTransaction().begin();
			getEm().persist(c);
			getEm().getTransaction().commit();
		} catch (Exception e) {
			logger.error("Erro ao inserir dado: ", e);
			throw e;
		}
	}

	public void atualiza(T c) throws Exception {
		try {
			getEm().getTransaction().begin();
			getEm().merge(c);
			getEm().getTransaction().commit();
		} catch (Exception e) {
			logger.error("Erro ao atualizar dado: ", e);
			throw e;
		}
	}

	public void remove(T c) throws Exception {
		try {
			getEm().getTransaction().begin();
			c = getEm().merge(c);
			getEm().remove(c);
			getEm().getTransaction().commit();
		} catch (Exception e) {
			logger.error("Erro ao excluir dado: ", e);
			throw e;
		}

	}

	public T getPeloId(Integer id) throws Exception {
		T c = null;
		try {
			c = getEm().find(getEntidade(), id);
		} catch (Exception e) {
			logger.error("Erro ao pesquisar dado pelo ID: ", e);
			throw e;
		}
		return c;
	}

	public abstract EntityManager getEm();

	public abstract Class<T> getEntidade();

}
