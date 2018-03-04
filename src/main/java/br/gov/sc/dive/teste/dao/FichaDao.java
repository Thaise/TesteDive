package br.gov.sc.dive.teste.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import br.gov.sc.dive.teste.dao.conexao.ProdutorEntityManager;
import br.gov.sc.dive.teste.dao.entidades.Ficha;

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

	public List<Ficha> busca(FiltroFichaDTO filtro) {
		List<Ficha> fichas = null;
		try {
			StringBuilder query = new StringBuilder("select f from Ficha f where f.flAtivo = 1");

			if (filtro != null) {
				if (filtro.getId() != null) {
					query.append(" and f.idFicha = " + filtro.getId());
				}
				if (filtro.getDtInicial() != null && filtro.getDtFinal() != null) {

					query.append(" and (f.dtCadastro between to_date(" + getDtString(filtro.getDtInicial())
							+ ", 'YYYY-MM-DD')");
					query.append(" and to_date(" + getDtString(filtro.getDtFinal()) + ", 'YYYY-MM-DD')");
				}
			}
			
			fichas = getEmPesquisa().createQuery(query.toString()).getResultList();

		} catch (Exception e) {
			logger.error("Erro ao buscar ficha por intervalo de data de cadastro", e);
			throw e;
		}
		return fichas;
	}

	private String getDtString(Date dt) {
		return dt != null ? new SimpleDateFormat("YYYY-MM-DD").format(dt) : null;
	}

	public EntityManager getEmPesquisa() {
		return new ProdutorEntityManager().criaEntityManager();
	}

	@Override
	public Class<Ficha> getEntidade() {
		return Ficha.class;
	}

}
