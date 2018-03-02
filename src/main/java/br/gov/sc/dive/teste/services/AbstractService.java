package br.gov.sc.dive.teste.services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.sc.dive.teste.dao.AbstractDao;
import br.gov.sc.dive.teste.dao.entidades.Entidade;
import br.gov.sc.dive.teste.services.dto.AbstractDTO;
import br.gov.sc.dive.teste.services.dto.exception.ServiceException;

public abstract class AbstractService<T extends AbstractDTO, E extends Entidade> {
	/*@Inject
	protected Logger logger;*/
	
	protected Logger logger = LoggerFactory.getLogger(AbstractService.class);

	protected ModelMapper modelMapper = new ModelMapper();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastra")
	public Response cadastra(T item) {
		try {
			E itemEntidade = convertToEntity(item);

			preCadastro(itemEntidade);

			getModel().insere(itemEntidade);
		} catch (Exception e) {
			logger.error("Erro ao inserir item: " + item, e);
			throw new ServiceException(e.getMessage());
		}
		return null;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualiza")
	public Response atualiza(T item) {
		try {
			E itemEntidade = convertToEntity(item);

			preAtualizacao(itemEntidade);

			getModel().atualiza(itemEntidade);
		} catch (Exception e) {
			logger.error("Erro ao atualizar item: " + item, e);
			throw new ServiceException(e.getMessage());
		}
		return null;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscaPeloId/{id}")
	public T buscaPeloId(@PathParam("id") Integer id) {
		T dto = null;
		try {
			E item = getModel().getPeloId(id);
			if (item != null) {
				dto = convertToDto(item);
			}
		} catch (Exception e) {
			logger.error("Erro ao buscar item pelo id: " + id, e);
			throw new ServiceException(e.getMessage());
		}
		return dto;
	}

	protected T convertToDto(E post) {
		T postDto = modelMapper.map(post, getClasseDTO());
		return postDto;
	}

	protected E convertToEntity(T postDto) throws Exception {
		E post = modelMapper.map(postDto, getClasseEntidade());
		return post;
	}

	protected abstract void preAtualizacao(E itemEntidade) throws Exception;

	protected abstract void preCadastro(E itemEntidade) throws Exception;

	protected abstract AbstractDao<E> getModel();

	protected abstract Class<E> getClasseEntidade();

	protected abstract Class<T> getClasseDTO();
}
