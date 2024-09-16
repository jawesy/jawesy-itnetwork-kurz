package Insurance.models.services;

import Insurance.models.dto.CrashDTO;

public interface CrashService {

    /**
     * Checks whether crash insurance exists for a specific client.
     *
     * @param clientId The ID of the client
     * @return true if the insurance exists, otherwise false
     */
    boolean existsByClientId(long clientId);

    /**
     * Retrieves the DTO of crash insurance for a specific client.
     *
     * @param clientId The ID of the client
     * @return CrashDTO object containing the insurance data
     */
    CrashDTO getById(long clientId);

    /**
     * Removes crash insurance based on the client's ID.
     *
     * @param clientId The ID of the client
     */
    void remove(long clientId);

    /**
     * Creates new crash insurance.
     *
     * @param crashDTO The data for the new insurance.
     */
    void create(CrashDTO crashDTO);

    /**
     * Updates existing crash insurance.
     *
     * @param crashDTO The data for updating the insurance.
     */
    void edit(CrashDTO crashDTO);
}
