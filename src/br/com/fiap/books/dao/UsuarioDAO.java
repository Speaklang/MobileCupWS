package br.com.fiap.books.dao;
import java.util.List;

import br.com.fiap.books.to.UsuarioTO;

public interface UsuarioDAO extends DAO<UsuarioTO,String>{

	List<UsuarioTO> listar();
	List<UsuarioTO> buscarPorEmail(String email);
	long buscarQtdPorEmail(String email);
	long verificarLogin(String email,String senha);
	
}
