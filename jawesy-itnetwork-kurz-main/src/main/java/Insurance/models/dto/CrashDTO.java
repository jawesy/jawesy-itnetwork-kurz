package Insurance.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for crash insurance information.
 * Used for transferring crash insurance data between layers in the application.
 */
public class CrashDTO {

    private long clientId;

    @NotBlank(message = "Vyplňte registrační značku")
    @Size(min = 5, max = 8, message = "Registrační značka musí být dlouhá mezi 5 a 8 znaky")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Registrační značka může obsahovat pouze velká písmena a číslice")
    private String crashRz;

    @NotBlank(message = "Vyberte variantu pojištění")
    private String crashLimit;

    // Getters and Setters

    /**
     * Gets the client ID associated with the crash insurance.
     *
     * @return clientId the ID of the client.
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Sets the client ID associated with the crash insurance.
     *
     * @param clientId the ID of the client.
     */
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the crash registration number.
     *
     * @return crashRz the crash registration number.
     */
    public String getCrashRz() {
        return crashRz;
    }

    /**
     * Sets the crash registration number.
     *
     * @param crashRz the crash registration number.
     */
    public void setCrashRz(
            @NotBlank(message = "Vyplňte registrační značku")
            @Size(min = 5, max = 8, message = "Registrační značka musí být dlouhá mezi 5 a 8 znaky")
            @Pattern(regexp = "^[A-Z0-9]+$", message = "Registrační značka může obsahovat pouze velká písmena a číslice")
            String crashRz) {
        this.crashRz = crashRz;
    }

    /**
     * Gets the crash insurance limit.
     *
     * @return crashLimit the limit of the crash insurance.
     */
    public String getCrashLimit() {
        return crashLimit;
    }

    /**
     * Sets the crash insurance limit.
     *
     * @param crashLimit the limit of the crash insurance.
     */
    public void setCrashLimit(String crashLimit) {
        this.crashLimit = crashLimit;
    }
}
