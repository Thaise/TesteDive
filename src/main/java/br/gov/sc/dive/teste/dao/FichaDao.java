package br.gov.sc.dive.teste.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import br.gov.sc.dive.teste.dao.conexao.ProdutorEntityManager;
import br.gov.sc.dive.teste.dao.entidades.Ficha;
import br.gov.sc.dive.teste.dao.entidades.QFicha;

@Stateless
public class FichaDao extends AbstractDao<Ficha> {

	private EntityManager em;

	@Override
	public EntityManager getEm() {
		if (em == null) {
			em = new ProdutorEntityManager().criaEntityManager();
		}
		return em;
	}

	public List<Ficha> getPorDtcadastro(Date dtInicial, Date dtFinal) {
		List<Ficha> fichas = null;
		try {
			QFicha ficha = QFicha.ficha;
			JPQLQuery query = new JPAQuery(getEmPesquisa());
			fichas = query.from(ficha).where(ficha.dtCadastro.between(dtInicial, dtFinal)).fetch();

		} catch (Exception e) {
			logger.error("Erro ao buscar ficha por intervalo de data de cadastro", e);
			throw e;
		}
		return fichas;
	}

	public EntityManager getEmPesquisa() {
		return new ProdutorEntityManager().criaEntityManager();
	}

	@Override
	public Class<Ficha> getEntidade() {
		return Ficha.class;
	}

}
