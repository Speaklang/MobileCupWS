package br.com.fiap.books.singleton;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class EMFactory {

	private static EntityManager em;

	 private EMFactory() {
	 }

	 public static EntityManager getInstance() {
		  if (em == null) {
			  em = Persistence
		     .createEntityManagerFactory("CLIENTE_ORACLE")
		     .createEntityManager();
		  }
	  	return em;
	 }
	
}
