package Insurance.data.repositories;

import Insurance.data.entities.CrashEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CrashRepository extends CrudRepository<CrashEntity, Long> {

    /**
     * Checks if a CrashEntity exists for a specific client.
     *
     * @param clientId the ID of the client.
     * @return true if a CrashEntity exists for the client, otherwise false.
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CrashEntity c WHERE c.client.clientId = :clientId")
    boolean existsByClient_ClientId(@Param("clientId") Long clientId);

    /**
     * Finds a CrashEntity by client ID.
     *
     * @param clientId the ID of the client.
     * @return the CrashEntity associated with the client ID.
     */
    CrashEntity findByClient_ClientId(long clientId);

    /**
     * Deletes a CrashEntity based on the client ID.
     *
     * @param clientId the ID of the client whose CrashEntity will be deleted.
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM CrashEntity c WHERE c.client.clientId = :clientId")
    void deleteByClient_ClientId(@Param("clientId") Long clientId);
}
