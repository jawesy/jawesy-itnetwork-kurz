package Insurance.data.repositories;

import Insurance.data.entities.MandatoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MandatoryRepository extends CrudRepository<MandatoryEntity, Long> {

    /**
     * Checks if a MandatoryEntity exists for a specific client.
     *
     * @param clientId the ID of the client.
     * @return true if a MandatoryEntity exists for the client, otherwise false.
     */
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MandatoryEntity m WHERE m.client.clientId = :clientId")
    boolean existsByClientId(@Param("clientId") Long clientId);

    /**
     * Finds a MandatoryEntity by client ID.
     *
     * @param clientId the ID of the client.
     * @return the MandatoryEntity associated with the client ID.
     */
    MandatoryEntity findByClient_ClientId(long clientId);

    /**
     * Deletes a MandatoryEntity based on the client ID.
     *
     * @param clientId the ID of the client whose MandatoryEntity will be deleted.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM MandatoryEntity m WHERE m.client.clientId = :clientId")
    void deleteByClient_ClientId(@Param("clientId") Long clientId);
}
