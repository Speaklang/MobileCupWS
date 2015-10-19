package br.com.fiap.books.dao.impl;

import javax.persistence.EntityManager;
import br.com.fiap.books.dao.DAO;

public class DAOImpl<T,K> implements DAO<T,K>{

	protected EntityManager em;
	
	public DAOImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void cadastrar(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}
	
}