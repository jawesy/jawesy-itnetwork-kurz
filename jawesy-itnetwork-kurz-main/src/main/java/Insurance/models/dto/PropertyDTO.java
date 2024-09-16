package Insurance.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for property insurance information.
 * This class is used to transfer property insurance data between layers in the application.
 */
public class PropertyDTO {

    private long clientId;  // Client ID associated with the property insurance.

    @NotBlank(message = "Vyplňte ulici")
    @Size(max = 30, min = 3, message = "Text musí být dlouhý mezi 3 a 30 znaky")
    private String insuranceStreet;

    @NotBlank(message = "Vyplňte číslo popisné/orientační")
    @Size(max = 9, min = 1, message = "Text musí být dlouhý mezi 1 a 9 znaky")
    private String insuranceHouseNumber;

    @NotBlank(message = "Vyplňte město/obec")
    @Size(max = 30, min = 3, message = "Text musí být dlouhý mezi 3 a 30 znaky")
    private String insuranceCity;

    @NotBlank(message = "Vyplňte PSČ (poštovní směrovací číslo)")
    @Pattern(regexp = "\\d{3}\\s?\\d{2}", message = "Vyplňte platné PSČ")
    private String insuranceZip;

    @NotBlank(message = "Vyberte variantu pojištění")
    private String propertyLimit;

    // Getters and Setters

    /**
     * Gets the client ID associated with the property insurance.
     *
     * @return clientId the ID of the client.
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Sets the client ID associated with the property insurance.
     *
     * @param clientId the ID of the client.
     */
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the street for the insured property.
     *
     * @return insuranceStreet the street of the insured property.
     */
    public String getInsuranceStreet() {
        return insuranceStreet;
    }

    /**
     * Sets the street for the insured property.
     *
     * @param insuranceStreet the street of the insured property.
     */
    public void setInsuranceStreet(String insuranceStreet) {
        this.insuranceStreet = insuranceStreet;
    }

    /**
     * Gets the house number for the insured property.
     *
     * @return insuranceHouseNumber the house number of the insured property.
     */
    public String getInsuranceHouseNumber() {
        return insuranceHouseNumber;
    }

    /**
     * Sets the house number for the insured property.
     *
     * @param insuranceHouseNumber the house number of the insured property.
     */
    public void setInsuranceHouseNumber(String insuranceHouseNumber) {
        this.insuranceHouseNumber = insuranceHouseNumber;
    }

    /**
     * Gets the city for the insured property.
     *
     * @return insuranceCity the city of the insured property.
     */
    public String getInsuranceCity() {
        return insuranceCity;
    }

    /**
     * Sets the city for the insured property.
     *
     * @param insuranceCity the city of the insured property.
     */
    public void setInsuranceCity(String insuranceCity) {
        this.insuranceCity = insuranceCity;
    }

    /**
     * Gets the postal code (ZIP) for the insured property.
     *
     * @return insuranceZip the ZIP code of the insured property.
     */
    public String getInsuranceZip() {
        return insuranceZip;
    }

    /**
     * Sets the postal code (ZIP) for the insured property.
     *
     * @param insuranceZip the ZIP code of the insured property.
     */
    public void setInsuranceZip(String insuranceZip) {
        this.insuranceZip = insuranceZip;
    }

    /**
     * Gets the insurance limit for the property.
     *
     * @return propertyLimit the insurance limit.
     */
    public String getPropertyLimit() {
        return propertyLimit;
    }

    /**
     * Sets the insurance limit for the property.
     *
     * @param propertyLimit the insurance limit.
     */
    public void setPropertyLimit(String propertyLimit) {
        this.propertyLimit = propertyLimit;
    }
}

