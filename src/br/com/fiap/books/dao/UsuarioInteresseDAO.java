package br.com.fiap.books.dao;

import java.util.List;

import br.com.fiap.books.to.UsuarioInteresse;
import br.com.fiap.books.to.UsuarioTO;

public interface UsuarioInteresseDAO extends DAO<UsuarioInteresse,Integer>{

	List<UsuarioInteresse> buscarInteressesUsuario(String email);
	long buscarQtdInteressesPorEmail(String email,String interesse);
}
