package br.com.fiap.books.android;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.fiap.books.dao.UsuarioDAO;
import br.com.fiap.books.dao.impl.UsuarioDAOImpl;
import br.com.fiap.books.to.UsuarioTO;

public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public EntityManagerFactory f;
	public EntityManager em;
	
    public UsuarioServlet() {
        
        if(f == null){
        	f= Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
        	em = f.createEntityManager();
        }

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao.equals("cadastro")) {
			
			String email = request.getParameter("email");
			String nome = request.getParameter("nome");
			String senha = request.getParameter("senha");
			
			try {
				
				UsuarioDAO dao = new UsuarioDAOImpl(em);
				
				long qtd = dao.buscarQtdPorEmail(email);
				
				
				
				JSONObject t1 = new JSONObject();
				
				if(qtd > 0){
					
					t1.put("msg", "Já existe uma conta cadastrada com este email.");
				}else{
					
					UsuarioTO usuario = new UsuarioTO();
					
					usuario.setNome(nome);
					usuario.setSenha(senha);
					usuario.setEmail(email);
	
					try{
					
						System.out.println(usuario.getEmail());
						dao.cadastrar(usuario);
					
					}catch(Exception e){
						
						System.out.println(e.getMessage());
					}
					t1.put("msg", "Cadastrado com sucesso.");
				}
				
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(t1.toString());
			
				
			} catch (JSONException e) {
	
				e.printStackTrace();
			}
		}		
		if (acao.equals("login")) {
			
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			try {
				
				UsuarioDAO dao = new UsuarioDAOImpl(em);
				
				long qtd = dao.verificarLogin(email, senha);
				
				
				
				JSONObject t1 = new JSONObject();
				
				if(qtd > 0){
					
					t1.put("msg", "OK");	
					
				}else{
					t1.put("msg", "Usúario ou senha inválido.");
					
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
