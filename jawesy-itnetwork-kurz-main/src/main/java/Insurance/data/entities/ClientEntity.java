package Insurance.data.entities;

import jakarta.persistence.*;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String forename;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String houseNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String city;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String zip;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)", unique = true)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)", unique = true)
    private String nationalID;

    @OneToOne(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private CrashEntity crashInsurance;

    @OneToOne(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private MandatoryEntity mandatoryInsurance;

    @OneToOne(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private PropertyEntity propertyInsurance;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Getters and Setters

    /**
     * Gets the ID of the client.
     *
     * @return the client's ID.
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Sets the ID of the client.
     *
     * @param clientId the client's ID.
     */
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the first name of the client.
     *
     * @return the client's first name.
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the first name of the client.
     *
     * @param forename the client's first name.
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the last name of the client.
     *
     * @return the client's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the client.
     *
     * @param lastName the client's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the street address of the client.
     *
     * @return the client's street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address of the client.
     *
     * @param street the client's street.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets the house number of the client's address.
     *
     * @return the client's house number.
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the house number of the client's address.
     *
     * @param houseNumber the client's house number.
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Gets the city of the client.
     *
     * @return the client's city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the client.
     *
     * @param city the client's city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the zip code of the client.
     *
     * @return the client's zip code.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the zip code of the client.
     *
     * @param zip the client's zip code.
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets the phone number of the client.
     *
     * @return the client's phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the client.
     *
     * @param phone the client's phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the email address of the client.
     *
     * @return the client's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email the client's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the national ID of the client.
     *
     * @return the client's national ID.
     */
    public String getNationalID() {
        return nationalID;
    }

    /**
     * Sets the national ID of the client.
     *
     * @param nationalID the client's national ID.
     */
    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    /**
     * Gets the crash insurance details for the client.
     *
     * @return the client's crash insurance.
     */
    public CrashEntity getCrashInsurance() {
        return crashInsurance;
    }

    /**
     * Sets the crash insurance for the client.
     *
     * @param crashInsurance the crash insurance entity.
     */
    public void setCrashInsurance(CrashEntity crashInsurance) {
        this.crashInsurance = crashInsurance;
    }

    /**
     * Gets the mandatory insurance details for the client.
     *
     * @return the client's mandatory insurance.
     */
    public MandatoryEntity getMandatoryInsurance() {
        return mandatoryInsurance;
    }

    /**
     * Sets the mandatory insurance for the client.
     *
     * @param mandatoryInsurance the mandatory insurance entity.
     */
    public void setMandatoryInsurance(MandatoryEntity mandatoryInsurance) {
        this.mandatoryInsurance = mandatoryInsurance;
    }

    /**
     * Gets the property insurance details for the client.
     *
     * @return the client's property insurance.
     */
    public PropertyEntity getPropertyInsurance() {
        return propertyInsurance;
    }

    /**
     * Sets the property insurance for the client.
     *
     * @param propertyInsurance the property insurance entity.
     */
    public void setPropertyInsurance(PropertyEntity propertyInsurance) {
        this.propertyInsurance = propertyInsurance;
    }

    /**
     * Gets the user entity associated with the client.
     *
     * @return the user entity.
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Sets the user entity associated with the client.
     *
     * @param user the user entity.
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }
}

