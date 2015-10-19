package br.com.fiap.books.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.books.dao.UsuarioDAO;
import br.com.fiap.books.to.UsuarioTO;

public class UsuarioDAOImpl 
					extends DAOImpl<UsuarioTO, String>
										implements UsuarioDAO {

	public UsuarioDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<UsuarioTO> listar() {
		Query query = em.createQuery("from UsuarioTO"); 
		return query.getResultList();
	}

	@Override
	public List<UsuarioTO> buscarPorEmail(String email) {
		TypedQuery<UsuarioTO> query = em.createQuery(
				"from UsuarioTO u where u.email like :mail",UsuarioTO.class);
			query.setParameter("mail","%" + email + "%");
			return query.getResultList();
	}

	@Override
	public long buscarQtdPorEmail(String email) {
		
		
		Query query = em.createQuery("select count(*) from UsuarioTO u where u.email = :mail");
		query.setParameter("mail", email);
		Long qtd = (Long)query.getSingleResult();

		return qtd;
	}

	@Override
	public long verificarLogin(String email, String senha) {
		Query query = em.createQuery("select count(*) from UsuarioTO u where u.email = :mail and u.senha = :senha");
		query.setParameter("mail", email);
		query.setParameter("senha", senha);
		Long qtd = (Long)query.getSingleResult();

		return qtd;
	}

}



