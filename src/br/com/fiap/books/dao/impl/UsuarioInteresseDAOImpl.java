package br.com.fiap.books.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.books.dao.UsuarioDAO;
import br.com.fiap.books.dao.UsuarioInteresseDAO;
import br.com.fiap.books.to.UsuarioInteresse;
import br.com.fiap.books.to.UsuarioTO;

public class UsuarioInteresseDAOImpl 
		extends DAOImpl<UsuarioInteresse, Integer>
							implements UsuarioInteresseDAO{

	public UsuarioInteresseDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<UsuarioInteresse> buscarInteressesUsuario(String email) {
		TypedQuery<UsuarioInteresse> query = em.createQuery(
				"from USUARIO_INTERESSE i where i.usuario.email like :mail",UsuarioInteresse.class);
			query.setParameter("mail","%" + email + "%");
			return query.getResultList();
	}

	@Override
	public long buscarQtdInteressesPorEmail(String email,String interesse) {		
		
		Query query = em.createQuery("select count(*) from USUARIO_INTERESSE i where i.usuario.email = :mail"
				+ " and i.interesse = :interesse");
		query.setParameter("mail", email);
		query.setParameter("interesse", interesse);
		Long qtd = (Long)query.getSingleResult();

		return qtd;
	}
}
