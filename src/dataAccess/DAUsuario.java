package dataAccess;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import modelos.Usuario;

public class DAUsuario {
	public Usuario obtenerUsuario(String nombre) {
		Session session = ConectorSingleton.getInstance().getSession();

		// Query para consultar si ya existe un usuario con ese nombre.
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = cb1.createQuery(Usuario.class);
		Root<Usuario> tabla = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("nombre"), nombre));
		List<Usuario> lista = session.createQuery(criteriaQuery).getResultList();
		
		

		Iterator<Usuario> iter = lista.iterator();
		
		if(iter.hasNext())
			return (Usuario) iter.next();
		
		return null;
	}
	
	public Usuario obtenerUsuario(int usuarioID) {
		Session session = ConectorSingleton.getInstance().getSession();

		// Query para consultar si ya existe un usuario con ese nombre.
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = cb1.createQuery(Usuario.class);
		Root<Usuario> tabla = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("id"), usuarioID));
		List<Usuario> lista = session.createQuery(criteriaQuery).getResultList();
		
		

		Iterator<Usuario> iter = lista.iterator();
		
		if(iter.hasNext())
			return (Usuario) iter.next();
		
		return null;
	}
}
