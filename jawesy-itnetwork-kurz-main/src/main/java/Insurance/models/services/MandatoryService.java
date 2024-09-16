package Insurance.models.services;

import Insurance.models.dto.MandatoryDTO;

public interface MandatoryService {

    /**
     * Checks if mandatory insurance exists for a given client.
     *
     * @param clientId ID of the client
     * @return true if the insurance exists, otherwise false
     */
    boolean existsByClientId(long clientId);

    /**
     * Retrieves the DTO of mandatory insurance for a given client.
     *
     * @param clientId ID of the client
     * @return MandatoryDTO object representing the insurance
     */
    MandatoryDTO getById(long clientId);

    /**
     * Deletes the mandatory insurance by the client's ID.
     *
     * @param clientId ID of the client
     */
    void remove(long clientId);

    /**
     * Creates new mandatory insurance.
     *
     * @param mandatoryDTO Data for the new insurance.
     */
    void create(MandatoryDTO mandatoryDTO);

    /**
     * Updates an existing mandatory insurance.
     *
     * @param mandatoryDTO Data for updating the insurance.
     */
    void edit(MandatoryDTO mandatoryDTO);
}
