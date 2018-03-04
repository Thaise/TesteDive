package br.gov.sc.dive.teste.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.gov.sc.dive.teste.dao.AbstractModel;
import br.gov.sc.dive.teste.dao.AnimalModel;
import br.gov.sc.dive.teste.dao.entidades.Animal;
import br.gov.sc.dive.teste.services.dto.AnimalDTO;
import br.gov.sc.dive.teste.services.dto.exception.ServiceException;

@Path("/animal")
public class AnimalService extends AbstractService<AnimalDTO, Animal> {

	@EJB
	private AnimalModel animalModel;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/busca/todos")
	public List<AnimalDTO> buscaTodos() {
		List<AnimalDTO> animais = new ArrayList<AnimalDTO>();
		try {
			List<Animal> animaisBd = animalModel.getTodos();
			if (animaisBd != null) {
				for (Animal a : animaisBd) {
					animais.add(modelMapper.map(a, AnimalDTO.class));
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao buscar item pelo id: ", e);
			throw new ServiceException(e.getMessage());
		}
		return animais;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/busca/filtro")
	public List<AnimalDTO> busca(AnimalDTO filtro) {
		List<AnimalDTO> animais = new ArrayList<AnimalDTO>();
		try {
			List<Animal> animaisBd = animalModel.busca(filtro);
			if (animaisBd != null) {
				for (Animal a : animaisBd) {
					animais.add(modelMapper.map(a, AnimalDTO.class));
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao buscar item pelo id: ", e);
			throw new ServiceException(e.getMessage());
		}
		return animais;
	}

	@Override
	protected void preCadastro(Animal empresaEntidade) throws Exception {
	}

	@Override
	protected void preAtualizacao(Animal itemEntidade) throws Exception {
		preCadastro(itemEntidade);
	}

	@Override
	protected AbstractModel<Animal> getModel() {
		return animalModel;
	}

	@Override
	protected Class<Animal> getClasseEntidade() {
		return Animal.class;
	}

	@Override
	protected Class<AnimalDTO> getClasseDTO() {
		return AnimalDTO.class;
	}

}
