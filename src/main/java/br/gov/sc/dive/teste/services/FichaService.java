package br.gov.sc.dive.teste.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.gov.sc.dive.teste.dao.AbstractModel;
import br.gov.sc.dive.teste.dao.FichaModel;
import br.gov.sc.dive.teste.dao.entidades.Animal;
import br.gov.sc.dive.teste.dao.entidades.Ficha;
import br.gov.sc.dive.teste.services.dto.AnimalDTO;
import br.gov.sc.dive.teste.services.dto.FichaDTO;
import br.gov.sc.dive.teste.services.dto.FiltroFichaDTO;
import br.gov.sc.dive.teste.services.dto.exception.ServiceException;

@Path("/ficha")
public class FichaService extends AbstractService<FichaDTO, Ficha> {

	@EJB
	private FichaModel fichaModel;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/busca/filtro")
	public List<FichaDTO> busca(FiltroFichaDTO filtro) {
		List<FichaDTO> fichas = new ArrayList<FichaDTO>();
		try {
			List<Ficha> fichasBd = fichaModel.busca(filtro);
			if (fichasBd != null) {
				for (Ficha a : fichasBd) {
					fichas.add(modelMapper.map(a, FichaDTO.class));
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao buscar item pelo id: ", e);
			throw new ServiceException(e.getMessage());
		}
		return fichas;
	}

	protected Ficha convertToEntity(FichaDTO postDto) throws Exception {
		Ficha ficha = super.convertToEntity(postDto);
		if (postDto.getAnimais() != null && !postDto.getAnimais().isEmpty()) {
			for (AnimalDTO a : postDto.getAnimais()) {
				ficha.getAnimais().add(modelMapper.map(a, Animal.class));
			}
		}

		return ficha;
	}

	@Override
	protected void preCadastro(Ficha empresaEntidade) throws Exception {
		if (empresaEntidade.getIdFicha() == null) {
			empresaEntidade.setDtCadastro(new Date());
		}
	}

	@Override
	protected void preAtualizacao(Ficha itemEntidade) throws Exception {
		preCadastro(itemEntidade);
	}

	@Override
	protected AbstractModel<Ficha> getModel() {
		return fichaModel;
	}

	@Override
	protected Class<Ficha> getClasseEntidade() {
		return Ficha.class;
	}

	@Override
	protected Class<FichaDTO> getClasseDTO() {
		return FichaDTO.class;
	}

}
