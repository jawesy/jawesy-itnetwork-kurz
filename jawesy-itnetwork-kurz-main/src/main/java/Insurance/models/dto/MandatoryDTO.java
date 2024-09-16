package Insurance.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for mandatory insurance information.
 * This class is used to transfer mandatory insurance data between layers in the application.
 */
public class MandatoryDTO {

    private long clientId;

    @NotBlank(message = "Vyplňte registrační značku")
    @Size(max = 8, min = 5, message = "Registrační značka musí být dlouhá mezi 5 a 8 znaky")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Registrační značka může obsahovat pouze velká písmena a číslice")
    private String rz;

    @NotBlank(message = "Vyberte variantu pojištění")
    private String mandatoryLimit;

    // Getters and Setters

    /**
     * Gets the client ID associated with the mandatory insurance.
     *
     * @return clientId the ID of the client.
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Sets the client ID associated with the mandatory insurance.
     *
     * @param clientId the ID of the client.
     */
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the registration number (RZ) for the mandatory insurance.
     *
     * @return rz the registration number.
     */
    public String getRz() {
        return rz;
    }

    /**
     * Sets the registration number (RZ) for the mandatory insurance.
     *
     * @param rz the registration number.
     */
    public void setRz(String rz) {
        this.rz = rz;
    }

    /**
     * Gets the insurance limit for the mandatory insurance.
     *
     * @return mandatoryLimit the insurance limit.
     */
    public String getMandatoryLimit() {
        return mandatoryLimit;
    }

    /**
     * Sets the insurance limit for the mandatory insurance.
     *
     * @param mandatoryLimit the insurance limit.
     */
    public void setMandatoryLimit(String mandatoryLimit) {
        this.mandatoryLimit = mandatoryLimit;
    }
}
