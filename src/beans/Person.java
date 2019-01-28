package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Dependent
@Named("cdiPers")
@Entity(name = "Person")
@Table(name = "T_Person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Basic(optional = false)
	@Column(name = "first_name", length = 200)
	private String firstName;
	
	@Basic(optional = false)
	@Column(name = "last_name", length = 200)
	private String lastName;


	@Basic(optional = false)
	@Column(name = "mail", length = 200)
	private String mail;
	
	@Basic()
	@Column(name = "web_site", length = 200,nullable=true)
	private String webSite;
	
	
	@Basic()
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_day")
	private Date birthDay;


	@Basic(optional = false)
	@Column(name = "password", length = 200)
	private String password;
	
	@OneToMany(mappedBy="person", orphanRemoval=true,
			cascade= {CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.EAGER)
	private List<Activity> activities;
	
	@Version()
	private long version = 0;


	public Person() {
		super();
	}

	public Person(String firstName,String lastName,String mail, String webSite, Date birthDay,String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.webSite = webSite;
		this.birthDay = birthDay;
		this.password = password;
		this.mail = mail;
	}

	@PreUpdate
	public void beforeUpdate() {
		System.err.println("PreUpdate of " + this);
	}

	@PostUpdate
	public void afterUpdate() {
		System.err.println("PostUpdate of " + this);
	}

	@Override
	public String toString() {
		return "Person(id=" + getId() + "," + firstName + "," + birthDay + "," + ",v" + getVersion() + ")";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public void addActivity(Activity activity) {
		activities.add(activity);
	}
	
}