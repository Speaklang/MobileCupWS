package br.com.fiap.books.dao;

public interface DAO<T,K> {

	void cadastrar(T entity);
	
}
