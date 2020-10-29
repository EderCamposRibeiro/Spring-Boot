package project.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "The name field cannot be null!")
	@NotEmpty(message = "The name field cannot be empty!")
	private String name;
	
	@NotNull(message = "The surname field cannot be null!")
	@NotEmpty(message = "The surname field cannot be empty!")	
	private String surname;
	
	private String cep; //Zip code
	
	private String rua; //Street
	
	private String bairro; //Neighborhood
	
	private String cidade; //City
	
	private String uf; //State
	
	private String ibge; // Brazilian Geography and Statistics Institute
	
	@Min(value = 18, message = "Invalid age!")
	private int age;
	
	@OneToMany(mappedBy = "person", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Telephone> phones;
	
	@NotNull(message = "The Gender field cannot be null!")
	@NotEmpty(message = "The Gender field cannot be empty!")
	private String sex;
	
	@ManyToOne
	private Profession profession;
	
	public void setProfession(Profession profession) {
		this.profession = profession;
	}
	
	public Profession getProfession() {
		return profession;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setPhones(List<Telephone> phones) {
		this.phones = phones;
	}
	
	public List<Telephone> getPhones() {
		return phones;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}
	
	

}
