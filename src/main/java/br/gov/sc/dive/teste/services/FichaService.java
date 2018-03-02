package br.gov.sc.dive.teste.services;

import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import br.gov.sc.dive.teste.dao.AbstractDao;
import br.gov.sc.dive.teste.dao.FichaDao;
import br.gov.sc.dive.teste.dao.entidades.Animal;
import br.gov.sc.dive.teste.dao.entidades.Ficha;
import br.gov.sc.dive.teste.services.dto.AnimalDTO;
import br.gov.sc.dive.teste.services.dto.FichaDTO;

@Path("/ficha")
public class FichaService extends AbstractService<FichaDTO, Ficha> {

	@EJB 
	private FichaDao fichaModel;
	
	protected Ficha convertToEntity(FichaDTO postDto) throws Exception {
		Ficha ficha = super.convertToEntity(postDto);
		if(postDto.getAnimais() != null && !postDto.getAnimais().isEmpty()) {
			for(AnimalDTO a: postDto.getAnimais()) {
				ficha.getAnimais().add(modelMapper.map(a, Animal.class));
			}
		}
		
		return ficha;
	}
	@Override
	protected void preCadastro(Ficha empresaEntidade) throws Exception {
		if(empresaEntidade.getIdFicha() == null) {
			empresaEntidade.setDtCadastro(new Date());
		}
	}

	@Override
	protected void preAtualizacao(Ficha itemEntidade) throws Exception {
		preCadastro(itemEntidade);
	}


	@Override
	protected AbstractDao<Ficha> getModel() {
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
