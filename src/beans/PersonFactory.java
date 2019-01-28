package beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class PersonFactory {

	@Produces
	@Named("PersonWithoutActivities")
	public Person initpersonWithoutActivities() {
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setPassword("toto");
		// Activity activity = new Activity(20012, Nature.EXPERIENCE, "Test", "Ce fut
		// assez cool", "testActivity.com");
		// person.addActivity(activity);

		return person;
	}

	@Produces
	@Named("teest")
	public Home gap() {
		Home home = new Home();
		home.setPlace("France, Gap");
		System.err.println("produces ... " + home);
		return home;
	}
}
