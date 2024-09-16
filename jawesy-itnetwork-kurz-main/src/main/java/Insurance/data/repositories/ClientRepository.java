package Insurance.data.repositories;

import Insurance.data.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    /**
     * Deletes a client by its ID.
     *
     * @param clientId the ID of the client to delete.
     */
    @Modifying
    @Query("DELETE FROM ClientEntity c WHERE c.clientId = :clientId")
    void deleteClientById(@Param("clientId") Long clientId);
}
