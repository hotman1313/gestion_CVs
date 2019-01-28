package test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.Activity;
import beans.Nature;
import beans.Person;
import dao.DaoManager;
import dao.User;

public class TestDaoManager {

	static EJBContainer container;
	static DaoManager dao;

	User u;
	@BeforeClass
	public static void beforeAll() throws NamingException {
		final String name = "java:global/Archi-App_Projet-CV/DaoManager";
		container = EJBContainer.createEJBContainer();
		dao = (DaoManager) container.getContext().lookup(name);
	}

	@AfterClass
	public static void afterAll() {
		container.close();
	}

	@Before
	public void beforeTest() throws NamingException{
		u = (User) container.getContext().lookup("java:global/Archi-App_Projet-CV/User");
		u.boss();
	}

	@After
	public void afterTest() throws NamingException{
		List<Person> persons = dao.findAllPersons();
		
		for (Person person : persons) {
			u.removePerson(person);
		}
		
	}
/*
	@Inject
	@Named("teest")
	Home personTest;
	
	@Test
	public void testCdi() {
		assertEquals(personTest.getPlace(), "Éric");
	}*/
	
	
/*	@Test
	public void testAddPersonWithoutActivity() throws ParseException, NamingException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");		
		dao.add(person);
		
		person = (Person) dao.find(Person.class, 1);
		System.out.println("-----------"+person);
		person.setFirstName("Bauer");
		Activity a = new Activity(2012, Nature.EXPERIENCE, "Test", "coll", "toto");
		
		a.setPerson(person);
		dao.add(a);
		
		//dao.updat(person);
		User uzer = (User) container.getContext().lookup("java:global/Archi-App_Projet-CV/User");
		uzer.login("aa@aa.com", "toto");
		uzer.updatePerson(person);
		person = (Person) dao.find(Person.class, 1);
		assertEquals(person.getVersion() , 1);
		
		Assert.assertNotNull(dao);
		uzer.removePersonCoop(person);
		person = (Person) dao.find(Person.class, 1);
	}*/

	
	@Test
	public void testFindAllPersons() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		
		//person 2
		Date datePers2 = dateFormat.parse("07/11/1988");
		Person person2 = new Person();
		person2.setFirstName("Pierre");
		person2.setLastName("Deschamps");
		person2.setMail("aa@aa.com");
		person2.setWebSite("test.net");
		person2.setBirthDay(datePers2);
		person2.setPassword("toto");
		
		//person 3
		Date datePers3 = dateFormat.parse("04/05/1974");
		Person person3 = new Person();
		person3.setFirstName("Laurent");
		person3.setLastName("Vincent");
		person3.setMail("aa@aa.com");
		person3.setWebSite("test.net");
		person3.setBirthDay(datePers3);
		person3.setPassword("toto");

		dao.add(person);
		dao.add(person2);
		dao.add(person3);
		
		assertEquals(dao.findAllPersons().size(), 3);
	}

	@Test
	public void testFindPersonByFirstNameLike() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		
		//person 2
		Date datePers2 = dateFormat.parse("07/11/1988");
		Person person2 = new Person();
		person2.setFirstName("Pierre");
		person2.setLastName("Deschamps");
		person2.setMail("aa@aa.com");
		person2.setWebSite("test.net");
		person2.setBirthDay(datePers2);
		person2.setPassword("toto");

		//person 3
		Date datePers3 = dateFormat.parse("04/05/1974");
		Person person3 = new Person();
		person3.setFirstName("Laurent");
		person3.setLastName("Vincent");
		person3.setMail("aa@aa.com");
		person3.setWebSite("test.net");
		person3.setBirthDay(datePers3);
		person3.setPassword("toto");
		//person 3
		Date datePers4 = dateFormat.parse("04/05/1974");
		Person person4 = new Person();
		person4.setFirstName("Laura");
		person4.setLastName("Vincent");
		person4.setMail("aa@aa.com");
		person4.setWebSite("test.net");
		person4.setBirthDay(datePers4);
		person4.setPassword("toto");

		dao.add(person);
		dao.add(person2);
		dao.add(person3);
		dao.add(person4);

		u.removePerson(person);
		List<Person> personsByNameLike = dao.findPersonsByFirsNameLike("aur");
		
		assertEquals(personsByNameLike.size(), 2);
	}

	@Test
	public void testFindPersonByFirstName() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		
		//person 2
		Date datePers2 = dateFormat.parse("07/11/1988");
		Person person2 = new Person();
		person2.setFirstName("Pierre");
		person2.setLastName("Deschamps");
		person2.setMail("aa@aa.com");
		person2.setWebSite("test.net");
		person2.setBirthDay(datePers2);
		person2.setPassword("toto");
		
		//person 3
		Date datePers3 = dateFormat.parse("04/05/1974");
		Person person3 = new Person();
		person3.setFirstName("Laurent");
		person3.setLastName("Vincent");
		person3.setMail("aa@aa.com");
		person3.setWebSite("test.net");
		person3.setBirthDay(datePers3);
		person3.setPassword("toto");

		dao.add(person);
		dao.add(person2);
		dao.add(person3);
		
		Person personByName = (Person) dao.findPersonsByFirstName("Pierre");
		
		assertEquals(personByName.getBirthDay().toString(), "1988-11-07");
	}	

	@Test
	public void testAddActivityWithPerson() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		dao.add(person);
		Activity activity = new Activity(2012, Nature.EXPERIENCE, "test", "ce fut assez cool", "activity1.com");
		activity.setPerson(person);
		dao.add(activity);
		
		List<Person> personActi = dao.findPersonsByTitle("tes");
		
		assertEquals(personActi.get(0).getActivities().get(0).getTitre(), "test");
	}
	@Test
	public void testAddActivitiesToAPerson() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		
				
		//activity
		Activity activity = new Activity(2012, Nature.EXPERIENCE, "test", "ce fut assez cool", "activity1.com");
		Activity activity2 = new Activity(2016, Nature.FORMATION, "test2", "ce fut assez cool", "activity2.com");
		Activity activity3 = new Activity(2011, Nature.EXPERIENCE, "tes3", "ce fut assez cool", "activity3.com");
		Activity activity4 = new Activity(2015, Nature.AUTRE, "test4", "ce fut assez cool", "activity4.com");
		
		activity.setPerson(person);
		activity2.setPerson(person);
		activity3.setPerson(person);
		activity4.setPerson(person);
		
		dao.add(person);
		dao.add(activity);
		dao.add(activity2);
		dao.add(activity3);
		dao.add(activity4);
		
		List<Activity> activities =  dao.findActivitiesOfPerson(person);
		person.setActivities(activities);
		assertEquals(person.getActivities().size(), 4);
	}
	
	@Test
	public void removeActivityOfPerson() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		

		
		//activity
		Activity activity = new Activity(2012, Nature.EXPERIENCE, "test", "ce fut assez cool", "activity1.com");
		Activity activity2 = new Activity(2016, Nature.FORMATION, "test2", "ce fut assez cool", "activity2.com");
		Activity activity3 = new Activity(2011, Nature.EXPERIENCE, "tes3", "ce fut assez cool", "activity3.com");
		Activity activity4 = new Activity(2015, Nature.AUTRE, "test4", "ce fut assez cool", "activity4.com");
		
		activity.setPerson(person);
		activity2.setPerson(person);
		activity3.setPerson(person);
		activity4.setPerson(person);
		
		dao.add(person);
		dao.add(activity);
		dao.add(activity2);
		dao.add(activity3);
		dao.add(activity4);
		
		List<Activity> activities = dao.findActivitiesOfPerson(person);
		
		person.setActivities(activities);
		
		assertEquals(person.getActivities().size(), 4);
		
		u.removeActivityOfPerson(activity2);;

		activities = dao.findActivitiesOfPerson(person);
		
		
		assertEquals(activities.size(), 3);
		
	}
	
	@Test
	public void removeActivity() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		

		
		//activity
		Activity activity = new Activity(2012, Nature.EXPERIENCE, "test", "ce fut assez cool", "activity1.com");
		Activity activity2 = new Activity(2016, Nature.FORMATION, "test2", "ce fut assez cool", "activity2.com");
		Activity activity3 = new Activity(2011, Nature.EXPERIENCE, "tes3", "ce fut assez cool", "activity3.com");
		Activity activity4 = new Activity(2015, Nature.AUTRE, "test4", "ce fut assez cool", "activity4.com");
		
		activity.setPerson(person);
		activity2.setPerson(person);
		activity3.setPerson(person);
		activity4.setPerson(person);
		
		dao.add(person);
		dao.add(activity);
		dao.add(activity2);
		dao.add(activity3);
		dao.add(activity4);
		
		List<Activity> activities = dao.findActivitiesOfPerson(person);
		
		person.setActivities(activities);
		
		assertEquals(person.getActivities().size(), 4);
		
		u.removeActivityOfPerson(activity4);
		
		activities=dao.findActivitiesOfPerson(person);
		person.setActivities(activities);
		assertEquals(person.getActivities().size(), 3);;
		
	}
	
	@Test
	public void testRemovePerson() throws ParseException{
		String patternDate = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
		
		//person 1
		Date datePers = dateFormat.parse("20/08/1991");
		Person person = new Person();
		person.setFirstName("Éric");
		person.setLastName("Lacroix");
		person.setMail("aa@aa.com");
		person.setWebSite("test.net");
		person.setBirthDay(datePers);
		person.setPassword("toto");
		

		
		//activity
		Activity activity = new Activity(2012, Nature.EXPERIENCE, "test", "ce fut assez cool", "activity1.com");
		Activity activity2 = new Activity(2016, Nature.FORMATION, "test2", "ce fut assez cool", "activity2.com");
		Activity activity3 = new Activity(2011, Nature.EXPERIENCE, "tes3", "ce fut assez cool", "activity3.com");
		Activity activity4 = new Activity(2015, Nature.AUTRE, "test4", "ce fut assez cool", "activity4.com");
		
		activity.setPerson(person);
		activity2.setPerson(person);
		activity3.setPerson(person);
		activity4.setPerson(person);
		
		dao.add(person);
		dao.add(activity);
		dao.add(activity2);
		dao.add(activity3);
		dao.add(activity4);
		
		List<Activity> activities = dao.findActivitiesOfPerson(person);
		
		person.setActivities(activities);
		
		assertEquals(person.getActivities().size(), 4);
		
		u.removePerson(person);
		
		activities=dao.findActivitiesOfPerson(person);
		assertEquals(activities.size(), 0);
		
	}
}