package br.com.fiap.books.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.fiap.books.dao.UsuarioDAO;
import br.com.fiap.books.dao.UsuarioInteresseDAO;
import br.com.fiap.books.dao.impl.UsuarioDAOImpl;
import br.com.fiap.books.dao.impl.UsuarioInteresseDAOImpl;
import br.com.fiap.books.to.UsuarioInteresse;
import br.com.fiap.books.to.UsuarioTO;

public class UsuarioInteresseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public EntityManagerFactory f;
	public EntityManager em;
	

	
    public UsuarioInteresseServlet() {
        if(f == null){
        	f= Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        	em = f.createEntityManager();
        }
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		if (acao.equals("cadastro")) {
		
			
			String email = request.getParameter("email");
			String interesse = request.getParameter("interesses");
		
			interesse = interesse.substring(0,interesse.length() -1);
			try {
				
				UsuarioDAO dao = new UsuarioDAOImpl(em);
				
				UsuarioInteresseDAO dao2 = new UsuarioInteresseDAOImpl(em);
				
				UsuarioTO usuarioLogado = new UsuarioTO();
				
				List<UsuarioTO> usuario = dao.buscarPorEmail(email);
				
				JSONObject t1 = new JSONObject();
				
				if(usuario.size() > 0)
				{
					for (UsuarioTO usuarioTO : usuario) {
						usuarioLogado = usuarioTO;					
					}					
					try{
						String[] interesses = interesse.split(";");
						
						for (String v : interesses) {
							
							UsuarioInteresse usuarioInteresse = new UsuarioInteresse();
							
							usuarioInteresse.setInteresse(v);
							usuarioInteresse.setUsuario(usuarioLogado);
							
							System.out.println(usuarioLogado.getEmail());
							dao2.cadastrar(usuarioInteresse);
						}
						
					
					}catch(Exception e){
						
						System.out.println(e.getMessage());
					}
					t1.put("msg", "OK");				
					
				}
				else
				{
					t1.put("msg", "Usuario não encontrado com este email.");
				}
								
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(t1.toString());
			
				
			} catch (JSONException e) {
	
				e.printStackTrace();
			}			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}
