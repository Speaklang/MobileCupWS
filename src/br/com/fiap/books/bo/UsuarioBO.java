package br.com.fiap.books.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.books.dao.impl.UsuarioDAOImpl;
import br.com.fiap.books.dao.UsuarioDAO;
import br.com.fiap.books.exception.UsuarioException;
import br.com.fiap.books.to.UsuarioTO;

public class UsuarioBO {
	
	private UsuarioDAO dao;

	public UsuarioBO() {
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
		EntityManager em = factory.createEntityManager();
		dao = new UsuarioDAOImpl(em);
	}
	
	public UsuarioTO cadastrar(UsuarioTO usuario) throws UsuarioException{
		if(dao.buscarQtdPorEmail(usuario.getEmail()) == 0)
		{
			dao.cadastrar(usuario);
			return usuario;
		}
		else			
		{			
			throw new UsuarioException("Já existe um usuario utilizando este email.");
		}		
	}
	
	public List<UsuarioTO> listar(){
		return dao.listar();
	}
	
	public List<UsuarioTO> buscarPorEmail(String email){
		return dao.buscarPorEmail(email);
	}
	
}
