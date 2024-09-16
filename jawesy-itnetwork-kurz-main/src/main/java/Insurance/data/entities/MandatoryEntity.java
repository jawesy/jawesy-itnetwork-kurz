package Insurance.data.entities;

import Insurance.data.entities.ClientEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "mandatory_insurances")
public class MandatoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @NotBlank(message = "Vyplňte registrační značku")
    @Column(name = "rz", nullable = false, columnDefinition = "VARCHAR(255)")
    private String rz;

    @NotBlank(message = "Vyberte variantu pojištění")
    @Column(name = "mandatory_limit", nullable = false, columnDefinition = "VARCHAR(255)")
    private String mandatoryLimit;

    // Getters and Setters

    /**
     * Gets the ID of the mandatory insurance entity.
     *
     * @return the mandatory insurance ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the mandatory insurance entity.
     *
     * @param id the mandatory insurance ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the client associated with this mandatory insurance.
     *
     * @return the client entity.
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Sets the client associated with this mandatory insurance.
     *
     * @param client the client entity.
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    /**
     * Gets the registration number (RZ) of the vehicle.
     *
     * @return the RZ.
     */
    public String getRz() {
        return rz;
    }

    /**
     * Sets the registration number (RZ) of the vehicle.
     *
     * @param rz the RZ.
     */
    public void setRz(String rz) {
        this.rz = rz;
    }

    /**
     * Gets the mandatory insurance limit.
     *
     * @return the mandatory limit.
     */
    public String getMandatoryLimit() {
        return mandatoryLimit;
    }

    /**
     * Sets the mandatory insurance limit.
     *
     * @param mandatoryLimit the mandatory limit.
     */
    public void setMandatoryLimit(String mandatoryLimit) {
        this.mandatoryLimit = mandatoryLimit;
    }
}
