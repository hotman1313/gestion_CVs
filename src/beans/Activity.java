package beans;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Dependent
@Entity(name="Activity")
@Table(name = "T_Activity")
public class Activity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Basic(optional=false)
	@Column(name="year",length=4,nullable=false)
	private int year;
	
	@Basic(optional=false)
	@Column(name="type",length=20,nullable=false)
	private Nature nature;

	@Basic(optional=false)
	@Column(name="title",length=50,nullable=false)
	private String titre;

	@Basic()
	@Column(name="description",length=200,nullable=false)
	private String description;

	@Basic()
	@Column(name="webAddress",length=200,nullable=true)
	private String webAdresse;
	
	@ManyToOne(fetch= FetchType.EAGER,optional=false)
	@JoinColumn(name="person_id",nullable=false)
	private Person person;
	
	public Activity() {
		super();
	}
	
	public Activity(int year, Nature nature, String titre, String description, String webAdresse) {
		super();
		this.year = year;
		this.nature = nature;
		this.titre = titre;
		this.description = description;
		this.webAdresse = webAdresse;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebAdresse() {
		return webAdresse;
	}

	public void setWebAdresse(String webAdresse) {
		this.webAdresse = webAdresse;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



}