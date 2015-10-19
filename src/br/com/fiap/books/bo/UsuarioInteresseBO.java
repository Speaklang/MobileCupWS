package br.com.fiap.books.bo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.books.dao.UsuarioInteresseDAO;
import br.com.fiap.books.dao.impl.UsuarioInteresseDAOImpl;
import br.com.fiap.books.exception.UsuarioException;
import br.com.fiap.books.to.UsuarioInteresse;

public class UsuarioInteresseBO {

	private UsuarioInteresseDAO dao;
	
	public UsuarioInteresseBO() {
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		EntityManager em = factory.createEntityManager();
		dao = new UsuarioInteresseDAOImpl(em);
	}
	
	public UsuarioInteresse cadastrar(UsuarioInteresse interesse) throws UsuarioException{
		if(dao.buscarQtdInteressesPorEmail(interesse.getUsuario().getEmail(),interesse.getInteresse()) == 0)
		{
			dao.cadastrar(interesse);
			return interesse;
		}
		else			
		{			
			throw new UsuarioException("Esse interrese já foi cadastrado nesse usuario.");
		}		
	}
}
