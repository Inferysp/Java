package app_jdbc;

import java.io.Serializable;

public class Kontrahent implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	///////////////////////Pola klasy./////////////////////////
	private String firstName;
	private String lastName;
    private String sex;
	private String personalPhone;
	private String companyPhone;
    private String email;
    private String company;
    private String nip;
    private String city;
    private String street;
    private String streetNumber;
    private String zipCode;

//////////////////////////////////////Gettery i Settery///////////////////////////////////
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPersonalPhone() {
		return personalPhone;
	}
	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}	
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public Kontrahent(String firstName,
					String lastName,
					String sex,
					String personalPhone,
					String companyPhone,
					String email, 
					String company,
					String nip,
					String city,
					String street,
					String streetNumber,
					String zipCode)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.personalPhone = personalPhone;
		this.companyPhone = companyPhone;
		this.email = email;
		this.company = company;
		this.nip = nip;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		this.zipCode = zipCode;
	}
	//Serialization
	public void dataReturn() {
		System.out.println(firstName + " "
						+ lastName + " "
						+ sex + " "
						+ street + " "
						+ streetNumber + " "
						+ personalPhone + " "
						+ companyPhone + " "
						+ email + " "
						+ company + " "
						+ nip + " "
						+ city + " "
						+ street + " "
						+ streetNumber + " "
						+ zipCode);
	}
}