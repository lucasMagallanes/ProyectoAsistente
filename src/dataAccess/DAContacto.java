package dataAccess;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelos.Contacto;

public class DAContacto {
	Session session;
	
	public DAContacto() {
		session = ConectorSingleton.getInstance().getSession();
	}
		
	/**
	 * Muestra los contactos que posee un usuario.
	 * @param id
	 * @return
	 */
	public List<Contacto> mostrarContactos(int id) {
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Contacto> criteriaQuery = cb1.createQuery(Contacto.class);
		Root<Contacto> tabla = criteriaQuery.from(Contacto.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("id"), id));
		List<Contacto> contactos = session.createQuery(criteriaQuery).getResultList();

		return contactos;
	}
	
}
