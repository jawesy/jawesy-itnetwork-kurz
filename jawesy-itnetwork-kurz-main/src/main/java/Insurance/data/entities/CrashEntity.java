package Insurance.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "crash_insurances")
public class CrashEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @Column(name = "crash_limit", nullable = false, columnDefinition = "VARCHAR(255)")
    private String crashLimit;

    @Column(name = "crash_rz", nullable = false, columnDefinition = "VARCHAR(255)")
    private String crashRz;

    // Getters and Setters

    /**
     * Gets the ID of the crash insurance entity.
     *
     * @return the crash insurance ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the crash insurance entity.
     *
     * @param id the crash insurance ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the client associated with this crash insurance.
     *
     * @return the client entity.
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Sets the client associated with this crash insurance.
     *
     * @param client the client entity.
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    /**
     * Gets the crash limit of the insurance policy.
     *
     * @return the crash limit.
     */
    public String getCrashLimit() {
        return crashLimit;
    }

    /**
     * Sets the crash limit of the insurance policy.
     *
     * @param crashLimit the crash limit.
     */
    public void setCrashLimit(String crashLimit) {
        this.crashLimit = crashLimit;
    }

    /**
     * Gets the crash registration number (RZ) of the vehicle.
     *
     * @return the crash RZ.
     */
    public String getCrashRz() {
        return crashRz;
    }

    /**
     * Sets the crash registration number (RZ) of the vehicle.
     *
     * @param crashRz the crash RZ.
     */
    public void setCrashRz(String crashRz) {
        this.crashRz = crashRz;
    }
}
