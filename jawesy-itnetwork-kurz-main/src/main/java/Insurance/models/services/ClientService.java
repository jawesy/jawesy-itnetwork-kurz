package Insurance.models.services;

import Insurance.data.entities.ClientEntity;
import Insurance.models.dto.ClientDTO;

import java.util.List;

/**
 * Service interface for managing client-related operations in the insurance system.
 * It includes methods for creating, editing, retrieving, and deleting clients, as well as managing client profiles.
 */
public interface ClientService {

    /**
     * Prepares a new client DTO with a pre-set email.
     * This method is typically used when a new client is being registered in the system.
     *
     * @return A pre-populated ClientDTO object for the new client.
     */
    ClientDTO prepareNewClient();

    /**
     * Retrieves the profile of the currently logged-in user.
     *
     * @return The ClientDTO object representing the profile of the logged-in client.
     */
    ClientDTO getProfile();

    /**
     * Creates a new client in the system.
     * The client's data is passed through a ClientDTO object.
     *
     * @param clientDTO The data transfer object containing the client's information.
     */
    void create(ClientDTO clientDTO);

    /**
     * Edits an existing client's information.
     * The client's updated data is passed through a ClientDTO object.
     *
     * @param client The data transfer object containing the updated client information.
     */
    void edit(ClientDTO client);

    /**
     * Removes a client from the system based on their ID.
     *
     * @param clientId The ID of the client to be removed.
     */
    void remove(long clientId);

    /**
     * Retrieves a list of all clients in the system.
     *
     * @return A list of ClientDTO objects representing all the clients.
     */
    List<ClientDTO> getAll();

    /**
     * Retrieves a client by their ID.
     *
     * @param clientId The ID of the client to retrieve.
     * @return A ClientDTO object representing the requested client.
     */
    ClientDTO getById(long clientId);

    /**
     * Retrieves a client entity by their ID.
     * This method returns the actual entity object as opposed to a DTO.
     *
     * @param clientId The ID of the client entity to retrieve.
     * @return The ClientEntity object representing the requested client entity.
     */
    ClientEntity findById(long clientId);
}
