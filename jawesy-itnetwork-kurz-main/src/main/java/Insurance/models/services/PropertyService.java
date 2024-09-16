package Insurance.models.services;

import Insurance.models.dto.PropertyDTO;

public interface PropertyService {

    /**
     * Checks if property insurance exists for a specific client.
     *
     * @param clientId ID of the client
     * @return true if insurance exists, otherwise false
     */
    boolean existsByClientId(long clientId);

    /**
     * Retrieves the DTO for the property insurance of a client.
     *
     * @param clientId ID of the client
     * @return PropertyDTO object representing the insurance
     */
    PropertyDTO getById(long clientId);

    /**
     * Deletes property insurance based on client ID.
     *
     * @param clientId ID of the client
     */
    void remove(long clientId);

    /**
     * Creates a new property insurance for a client.
     *
     * @param propertyDTO Data for the new insurance.
     */
    void create(PropertyDTO propertyDTO);

    /**
     * Updates existing property insurance.
     *
     * @param propertyDTO Data for updating the insurance.
     */
    void edit(PropertyDTO propertyDTO);
}


