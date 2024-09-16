package Insurance.data.repositories;

import Insurance.data.entities.PropertyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

    /**
     * Checks if a PropertyEntity exists for a specific client.
     *
     * @param clientId the ID of the client.
     * @return true if a PropertyEntity exists for the client, otherwise false.
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PropertyEntity p WHERE p.client.clientId = :clientId")
    boolean existsByClient_ClientId(@Param("clientId") Long clientId);

    /**
     * Finds a PropertyEntity by client ID.
     *
     * @param clientId the ID of the client.
     * @return the PropertyEntity associated with the client ID.
     */
    PropertyEntity findByClient_ClientId(long clientId);

    /**
     * Deletes a PropertyEntity based on the client ID.
     *
     * @param clientId the ID of the client whose PropertyEntity will be deleted.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM PropertyEntity p WHERE p.client.clientId = :clientId")
    void deleteByClient_ClientId(@Param("clientId") Long clientId);
}
