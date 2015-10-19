package br.com.fiap.books.to;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO_INTERESSE")
@SequenceGenerator(name="SEQ_INTERESSE_USUARIO",sequenceName="SEQ_INTERESSE_USUARIO",allocationSize=1)
public class UsuarioInteresse {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_INTERESSE_USUARIO")
	private int id;
	@ManyToOne
	private UsuarioTO usuario;
	private String interesse;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public UsuarioTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}
	public String getInteresse() {
		return interesse;
	}
	public void setInteresse(String interesse) {
		this.interesse = interesse;
	}
	
	
	
}

