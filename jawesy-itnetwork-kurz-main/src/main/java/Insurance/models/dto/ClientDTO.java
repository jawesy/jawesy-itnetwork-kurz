package Insurance.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for the Client entity.
 * Used for transferring client data between layers in the application.
 */
public class ClientDTO {

    private long clientId;
    private long userId; // Field for user ID
    private CrashDTO crashInsurance; // DTO for crash insurance
    private MandatoryDTO mandatoryInsurance; // DTO for mandatory insurance
    private PropertyDTO propertyInsurance; // DTO for property insurance

    @NotBlank(message = "Vyplňte křestní jméno")
    @Size(max = 40, min = 2, message = "Text musí být dlouhý mezi 2 a 40 znaky")
    private String forename;

    @NotBlank(message = "Vyplňte příjmení")
    @Size(max = 40, min = 2, message = "Text musí být dlouhý mezi 2 a 40 znaky")
    private String lastName;

    @NotBlank(message = "Vyplňte rodné číslo")
    @Size(max = 11, min = 9, message = "Text musí být dlouhý mezi 9 a 11 znaky")
    private String nationalID;

    @NotBlank(message = "Vyplňte ulici")
    @Size(max = 30, min = 3, message = "Text musí být dlouhý mezi 3 a 30 znaky")
    private String street;

    @NotBlank(message = "Vyplňte číslo popisné/orientační")
    @Size(max = 9, min = 1, message = "Text musí být dlouhý mezi 1 a 9 znaky")
    private String houseNumber;

    @NotBlank(message = "Vyplňte město/obec")
    @Size(max = 30, min = 3, message = "Text musí být dlouhý mezi 3 a 30 znaky")
    private String city;

    @NotBlank(message = "Vyplňte PSČ (poštovní směrovací číslo)")
    @Pattern(regexp = "\\d{3}\\s?\\d{2}", message = "Vyplňte platné PSČ")
    private String zip;

    @NotBlank(message = "Vyplňte telefonní číslo")
    @Pattern(regexp = "\\+?\\d{9,13}", message = "Vyplňte platné telefonní číslo")
    private String phone;

    @NotBlank(message = "Vyplňte emailovou adresu")
    @Email(message = "Vyplňte platnou emailovou adresu")
    private String email;

    // Getters and Setters

    /**
     * Gets the ID of the client.
     *
     * @return clientId the ID of the client.
     */
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CrashDTO getCrashInsurance() {
        return crashInsurance;
    }

    public void setCrashInsurance(CrashDTO crashInsurance) {
        this.crashInsurance = crashInsurance;
    }

    public MandatoryDTO getMandatoryInsurance() {
        return mandatoryInsurance;
    }

    public void setMandatoryInsurance(MandatoryDTO mandatoryInsurance) {
        this.mandatoryInsurance = mandatoryInsurance;
    }

    public PropertyDTO getPropertyInsurance() {
        return propertyInsurance;
    }

    public void setPropertyInsurance(PropertyDTO propertyInsurance) {
        this.propertyInsurance = propertyInsurance;
    }
}
