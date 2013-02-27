package pandox.base.bean;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pandox.base.dao.UsuarioDAO;
import pandox.base.entity.Usuario;

@Stateless
public class UsuarioBean {
	
	@Inject
	private Logger log;

	@EJB
	private UsuarioDAO dao;

	private Usuario usuario;
	
	
	public Usuario find(){
		usuario = dao.findById(1L);
		
		if(usuario == null){
			usuario = new Usuario();
			usuario.setNome("TESTE");
			dao.save(usuario);
			
		}
		
		log.info("TESTE TESTE ");
		return dao.findById(1L); 
	}
}
