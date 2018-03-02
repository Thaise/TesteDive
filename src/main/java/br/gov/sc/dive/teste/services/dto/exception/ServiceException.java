package br.gov.sc.dive.teste.services.dto.exception;

public class ServiceException extends RuntimeException {

	public ServiceException(String mensagem) {
		super(mensagem);
	}
}
