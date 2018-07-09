package dataAccess;

import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelos.Usuario;
import modelos.Sala;

public class DASala {
	Session session;
	
	public DASala() {
		session = ConectorSingleton.getInstance().getSession();
	}
	
	/**
	 * Obtiene una sala por el nombre.
	 * @param descripcion
	 * @return
	 */
	public Sala obtenerSala(String descripcion) {
		// Query para consultar si ya existe una sala con ese nombre.
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Sala> criteriaQuery = cb1.createQuery(Sala.class);
		Root<Sala> tabla = criteriaQuery.from(Sala.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("sala_descripcion"), descripcion));
		List<Sala> lista = session.createQuery(criteriaQuery).getResultList();
		
		Iterator<Sala> iter = lista.iterator();
		
		if(iter.hasNext())
			return (Sala) iter.next();
		
		return null;
	}
	
	/**
	 * Obtiene una sala por el id.
	 * @param id
	 * @return
	 */
	public Sala obtenerSala(int id) {
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Sala> criteriaQuery = cb1.createQuery(Sala.class);
		Root<Sala> tabla = criteriaQuery.from(Sala.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("sala_Id"), id));
		List<Sala> lista = session.createQuery(criteriaQuery).getResultList();

		Iterator<Sala> iter = lista.iterator();
		
		if(iter.hasNext())
			return (Sala) iter.next();
		
		return null;
	}
	
	/**
	 * Muestra todas las salas del chat.
	 * @return
	 */
	public List<Sala> mostrarSalas() {
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Sala> criteriaQuery = cb1.createQuery(Sala.class);
		Root<Sala> tabla = criteriaQuery.from(Sala.class);
		criteriaQuery.select(tabla);
		List<Sala> lista = session.createQuery(criteriaQuery).getResultList();

		return lista;
	}
	
	/**
	 * Muestra los usuarios que posee una sala.
	 * @param id
	 * @return
	 */
	public List<Usuario> mostrarUsuarios(int id) {
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = cb1.createQuery(Usuario.class);
		Root<Usuario> tabla = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("sala_Id"), id));
		List<Usuario> lista = session.createQuery(criteriaQuery).getResultList();

		return lista;
	}
	
	public void ingresarSala(Sala s) {
		session.getTransaction().begin();
		session.saveOrUpdate(s);
		session.getTransaction().commit();
	}
	
}
