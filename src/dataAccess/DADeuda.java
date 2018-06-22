package dataAccess;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelos.Deuda;

public class DADeuda {
	Session session;

	public DADeuda() {
		session = ConectorSingleton.getInstance().getSession();
	}

	public List<Deuda> listarDeudas(int deudorID) {

		// Query para consultar si ya existe un usuario con ese nombre.
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Deuda> criteriaQuery = cb1.createQuery(Deuda.class);
		Root<Deuda> tabla = criteriaQuery.from(Deuda.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("deudorID"), deudorID));
		List<Deuda> lista = session.createQuery(criteriaQuery).getResultList();

		return lista;

	}
	
	public List<Deuda> mostrarDeudas(int prestamistaID, int deudorID) {
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Deuda> criteriaQuery = cb1.createQuery(Deuda.class);
		Root<Deuda> tabla = criteriaQuery.from(Deuda.class);
		criteriaQuery.select(tabla).where(cb1.and(cb1.equal(tabla.get("deudorID"), deudorID),
				cb1.equal(tabla.get("prestamistaID"), prestamistaID)));
		List<Deuda> lista = session.createQuery(criteriaQuery).getResultList();

		return lista;
	}

	public void ingresarDeuda(Deuda deuda) {
		List<Deuda> deudas = mostrarDeudas(deuda.getPrestamistaID(),deuda.getDeudorID());
		
		if(deudas.size() == 0) {
			session.getTransaction().begin();
			session.saveOrUpdate(deuda);
			session.getTransaction().commit();
		} else {
			Deuda reg = deudas.get(0);
			reg.setImporte(reg.getImporte() + deuda.getImporte());
			
			session.getTransaction().begin();
			session.saveOrUpdate(reg);
			session.getTransaction().commit();
		}
	}
	
	public void actualizarDeuda(Deuda deuda) {
		session.getTransaction().begin();
		session.saveOrUpdate(deuda);
		session.getTransaction().commit();
	}
	
	public void eliminarDeuda(Deuda deuda) {
		session.getTransaction().begin();
		CriteriaBuilder cb1  = session.getCriteriaBuilder();
		CriteriaDelete<Deuda> query = cb1.createCriteriaDelete(Deuda.class);
		Root<Deuda> tabla = query.from(Deuda.class);
		query.where(cb1.equal(tabla.get("deudaID"), deuda.getDeudaID()));
		
		session.createQuery(query).executeUpdate();
		session.getTransaction().commit();
	}
	
	public void eliminarTodo() {
		session.getTransaction().begin();

		CriteriaBuilder cb1  = session.getCriteriaBuilder();
		CriteriaDelete<Deuda> query = cb1.createCriteriaDelete(Deuda.class);
		query.from(Deuda.class);
		session.createQuery(query).executeUpdate();
		
		session.getTransaction().commit();
	}

	public List<Deuda> listarPrestamosRealizados(int pretamistaID) {

		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Deuda> criteriaQuery = cb1.createQuery(Deuda.class);
		Root<Deuda> tabla = criteriaQuery.from(Deuda.class);
		criteriaQuery.select(tabla).where(cb1.equal(tabla.get("prestamistaID"), pretamistaID));
		List<Deuda> lista = session.createQuery(criteriaQuery).getResultList();

		return lista;
	}
}
