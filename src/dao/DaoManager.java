package dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import beans.Activity;
import beans.Person;

@Stateless
@LocalBean
public class DaoManager {

	@PersistenceContext(unitName = "myTestDatabaseUnit")
	public EntityManager em;

	@PostConstruct
	public void init() {
		System.out.println("init " + this + " with " + em);
	}

	@PermitAll
	public <T> T find(Class<T> clazz, long id) {
		return em.find(clazz, id);
	}

	//@RolesAllowed({"userConnected"})
	@PermitAll
	public <T> T add(T entity) {
		em.persist(entity);
		return entity;
	}

	@RolesAllowed({"userConnected"})
	//@PermitAll
	public <T> void remove(Class<T> clazz, Object id) {
		T entity = em.find(clazz, id);
		if (entity != null) {
			em.remove(em.merge(entity));
		}
	}
	
	@RolesAllowed({"userConnected"})
	//@PermitAll
	public void removeActivityOfPerson(Person person, Activity activity) {
		TypedQuery<Activity> query = em.createQuery("DELETE FROM Activity a WHERE a.id = :id_a AND a.person.id = :id_p", Activity.class);
		query.setParameter("id_a", (long)activity.getId());
		query.setParameter("id_p", (long)person.getId());
		query.executeUpdate();
	}

	@RolesAllowed({"userConnected"})
	//@PermitAll
	public Person update(Person person) {
		person = em.merge(person);
		return person;
	}


	@PermitAll
	public List<Person> findAllPersons() {
		TypedQuery<Person> q = em.createQuery("FROM Person", Person.class);
		return q.getResultList();
	}
	
	public List<Activity> findActivitiesOfPerson(Person person) {
		TypedQuery<Activity> query = em.createQuery(
				"SELECT a FROM Activity a WHERE a.person.id = :id", Activity.class);
		query.setParameter("id", person.getId());
		return query.getResultList();
	}


	@PermitAll
	public List<Person> findPersonsByFirsNameLike(String pattern) {
		TypedQuery<Person> query = em.createQuery("FROM Person p WHERE p.firstName LIKE :first_name_pattern",
				Person.class);
		query.setParameter("first_name_pattern", "%" + pattern + "%");
		return query.getResultList();
	}


	@PermitAll
	public Person findPersonsByFirstName(String name) {
		Query query = em.createQuery("FROM Person p WHERE p.firstName = :first_name", Person.class);
		query.setParameter("first_name", name);
		return (Person) query.getResultList().get(0);
	}


	@PermitAll
	public List<Person> findPersonsByLastNameLike(String pattern) {
		TypedQuery<Person> query = em.createQuery("FROM Person p WHERE p.lastName LIKE :last_name_pattern",
				Person.class);
		query.setParameter("last_name_pattern", "%" + pattern + "%");
		return query.getResultList();
	}

	@PermitAll
	public Person findPersonsByLastName(String lastName) {
		Query query = em.createQuery("FROM Person p WHERE p.lastName = :last_name", Person.class);
		query.setParameter("last_name", lastName);
		return (Person) query.getResultList().get(0);
	}

	@PermitAll
	public List<Person> findPersonsByTitle(String title) {
		TypedQuery<Person> query = em.createQuery(
				"SELECT p FROM Person p, Activity a WHERE a.person.id = p.id AND a.titre LIKE :title", Person.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

	@PermitAll
	public List<Activity> showActivities(Person person) {
		if (person != null) {
			TypedQuery<Activity> query = em.createQuery("SELECT a FROM Activity a WHERE a.person.idPerson = :id",
					Activity.class);
			query.setParameter("id", person.getId());
			return query.getResultList();
		}
		return null;
	}

	@PermitAll
	public Person findPersonByMail(String mail) {
		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p WHERE p.mail = :mail", Person.class);
		query.setParameter("mail", mail);
		return query.getResultList().get(0);
	}
}