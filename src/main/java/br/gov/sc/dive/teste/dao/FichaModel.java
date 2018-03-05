package br.gov.sc.dive.teste.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.gov.sc.dive.teste.dao.conexao.ProdutorEntityManager;
import br.gov.sc.dive.teste.dao.entidades.Ficha;
import br.gov.sc.dive.teste.services.dto.FiltroFichaDTO;

@Stateless
public class FichaModel extends AbstractModel<Ficha> {

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
			CriteriaBuilder cb = getEmPesquisa().getCriteriaBuilder();
			CriteriaQuery<Ficha> cq = cb.createQuery(Ficha.class);
			Root<Ficha> f = cq.from(Ficha.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (filtro != null) {

				if (filtro.getId() != null) {
					predicates.add(cb.equal(f.get("idFicha"), filtro.getId()));
				}

				if (filtro.getDtInicial() != null && filtro.getDtFinal() != null) {
					predicates.add(cb.and(cb.greaterThanOrEqualTo(f.get("dtCadastro"), setTime(filtro.getDtInicial(), 0, 0)),
							cb.lessThanOrEqualTo(f.get("dtCadastro"), setTime(filtro.getDtFinal(), 23, 59))));
				}
			}
			cq.select(f).where(predicates.toArray(new Predicate[] {}));
			fichas = getEmPesquisa().createQuery(cq).getResultList();

		} catch (Exception e) {
			logger.error("Erro ao buscar ficha por intervalo de data de cadastro", e);
			throw e;
		}
		return fichas;
	}

	private Date setTime(Date data, int hora, int minuto) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, hora);
		c.set(Calendar.MINUTE, minuto);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	public EntityManager getEmPesquisa() {
		return new ProdutorEntityManager().criaEntityManager();
	}

	@Override
	public Class<Ficha> getEntidade() {
		return Ficha.class;
	}

}
