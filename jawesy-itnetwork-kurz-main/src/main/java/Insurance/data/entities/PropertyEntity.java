package Insurance.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "property_insurance")
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @NotBlank(message = "Vyplňte ulici")
    @Size(max = 30, min = 3, message = "Text musí být dlouhý mezi 3 a 30 znaky")
    @Column(name = "insurance_street", nullable = false, columnDefinition = "VARCHAR(255)")
    private String insuranceStreet;

    @NotBlank(message = "Vyplňte číslo popisné/orientační")
    @Size(max = 9, min = 1, message = "Text musí být dlouhý mezi 1 a 9 znaky")
    @Column(name = "insurance_house_number", nullable = false, columnDefinition = "VARCHAR(255)")
    private String insuranceHouseNumber;

    @NotBlank(message = "Vyplňte město/obec")
    @Size(max = 30, min = 3, message = "Text musí být dlouhý mezi 3 a 30 znaky")
    @Column(name = "insurance_city", nullable = false, columnDefinition = "VARCHAR(255)")
    private String insuranceCity;

    @NotBlank(message = "Vyplňte PSČ (poštovní směrovací číslo)")
    @Pattern(regexp = "\\d{3}\\s?\\d{2}", message = "Vyplňte platné PSČ")
    @Column(name = "insurance_zip", nullable = false, columnDefinition = "VARCHAR(255)")
    private String insuranceZip;

    @NotBlank(message = "Vyberte variantu pojištění")
    @Column(name = "property_limit", nullable = false, columnDefinition = "VARCHAR(255)")
    private String propertyLimit;

    // Getters and Setters

    /**
     * Gets the ID of the property insurance entity.
     *
     * @return the property insurance ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the property insurance entity.
     *
     * @param id the property insurance ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the client associated with this property insurance.
     *
     * @return the client entity.
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Sets the client associated with this property insurance.
     *
     * @param client the client entity.
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    /**
     * Gets the street address of the insured property.
     *
     * @return the insurance street.
     */
    public String getInsuranceStreet() {
        return insuranceStreet;
    }

    /**
     * Sets the street address of the insured property.
     *
     * @param insuranceStreet the insurance street.
     */
    public void setInsuranceStreet(String insuranceStreet) {
        this.insuranceStreet = insuranceStreet;
    }

    /**
     * Gets the house number of the insured property.
     *
     * @return the insurance house number.
     */
    public String getInsuranceHouseNumber() {
        return insuranceHouseNumber;
    }

    /**
     * Sets the house number of the insured property.
     *
     * @param insuranceHouseNumber the insurance house number.
     */
    public void setInsuranceHouseNumber(String insuranceHouseNumber) {
        this.insuranceHouseNumber = insuranceHouseNumber;
    }

    /**
     * Gets the city of the insured property.
     *
     * @return the insurance city.
     */
    public String getInsuranceCity() {
        return insuranceCity;
    }

    /**
     * Sets the city of the insured property.
     *
     * @param insuranceCity the insurance city.
     */
    public void setInsuranceCity(String insuranceCity) {
        this.insuranceCity = insuranceCity;
    }

    /**
     * Gets the ZIP code of the insured property.
     *
     * @return the insurance ZIP code.
     */
    public String getInsuranceZip() {
        return insuranceZip;
    }

    /**
     * Sets the ZIP code of the insured property.
     *
     * @param insuranceZip the insurance ZIP code.
     */
    public void setInsuranceZip(String insuranceZip) {
        this.insuranceZip = insuranceZip;
    }

    /**
     * Gets the insurance limit for the property insurance.
     *
     * @return the property insurance limit.
     */
    public String getPropertyLimit() {
        return propertyLimit;
    }

    /**
     * Sets the insurance limit for the property insurance.
     *
     * @param propertyLimit the property insurance limit.
     */
    public void setPropertyLimit(String propertyLimit) {
        this.propertyLimit = propertyLimit;
    }
}
