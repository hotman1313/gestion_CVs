package dao;

import javax.annotation.PostConstruct;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import beans.Activity;
import beans.Person;

@Stateful
@RunAs("userConnected")
@SessionScoped
public class User {

	@EJB
	DaoManager daoManager;

	private Person user = null;

	@PostConstruct
	public void init() {
		System.out.println("init " + this);
	}

	public Person login(String mail, String password) {
		if ((user = daoManager.findPersonByMail(mail)) != null) {
			if (user.getPassword().equals(password)) {
				System.out.println("----------------" + user.getPassword().equals(password));
				return user;
			}
		}
		user = null;
		return user;
	}

	// pour les tests
	public Person boss() {
		user = new Person();
		user.setFirstName("root");
		user.setPassword("root");
		return user;
	}

	public Person updatePerson(Person person) {
		if (user != null) {
			return daoManager.update(person);
		}
		return null;
	}

	public void removePerson(Person person) {
		if (user != null) {
			daoManager.remove(Person.class, (long) person.getId());
		}
	}

	public void removeActivityOfPerson(Activity activity) {
		if (user != null) {
			Activity a = daoManager.find(Activity.class, activity.getId());
			daoManager.remove(Activity.class, a.getId());
		}
	}
}
