package Insurance.models.services;

import Insurance.data.entities.ClientEntity;
import Insurance.data.entities.UserEntity;
import Insurance.data.repositories.ClientRepository;
import Insurance.data.repositories.UserRepository;
import Insurance.models.dto.ClientDTO;
import Insurance.models.dto.mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service implementation for managing client-related operations in the insurance system.
 * Handles client creation, updating, deletion, and retrieval of client profiles.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    /**
     * Prepares a new client with the email of the currently authenticated user.
     *
     * @return ClientDTO containing the pre-filled email.
     */
    @Override
    public ClientDTO prepareNewClient() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new RuntimeException("Uživatel není ověřen.");
        }

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setEmail(userEntity.getEmail());
        return clientDTO;
    }

    /**
     * Retrieves the profile of the currently logged-in user.
     *
     * @return ClientDTO profile of the user, or null if the client does not exist.
     */
    @Override
    public ClientDTO getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            return null;
        }

        if (userEntity.getClient() == null) {
            return null;
        }

        ClientEntity clientEntity = userEntity.getClient();
        return clientMapper.toDTO(clientEntity);
    }

    /**
     * Creates a new client and assigns them to the currently logged-in user.
     *
     * @param clientDTO Data about the client.
     */
    @Override
    @Transactional
    public void create(ClientDTO clientDTO) {
        UserEntity userEntity = userRepository.findByEmail(clientDTO.getEmail());
        if (userEntity == null) {
            throw new RuntimeException("Uživatel dle emailu nedohledán: " + clientDTO.getEmail());
        }

        ClientEntity clientEntity = clientMapper.toEntity(clientDTO);
        clientEntity.setUser(userEntity);
        userEntity.setClient(clientEntity);

        clientRepository.save(clientEntity);
    }

    /**
     * Updates an existing client while preserving existing insurance policies.
     *
     * @param clientDTO Data for updating the client.
     */
    @Override
    @Transactional
    public void edit(ClientDTO clientDTO) {
        ClientEntity existingClient = clientRepository.findById(clientDTO.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + clientDTO.getClientId() + " nenalezen"));

        clientMapper.updateClientEntity(clientDTO, existingClient);

        clientRepository.save(existingClient);
    }

    /**
     * Deletes a client by their ID, including associated insurance and user account.
     *
     * @param clientId ID of the client to be deleted.
     */
    @Override
    @Transactional
    public void remove(long clientId) {
        Optional<ClientEntity> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isPresent()) {
            clientRepository.delete(clientOpt.get());
        } else {
            throw new NoSuchElementException("Klient s ID " + clientId + " nenalezen");
        }
    }

    /**
     * Returns a list of all clients.
     *
     * @return A list of clients as ClientDTO.
     */
    @Override
    public List<ClientDTO> getAll() {
        List<ClientEntity> clientEntities = (List<ClientEntity>) clientRepository.findAll();
        return clientMapper.toDTOList(clientEntities);
    }

    /**
     * Retrieves a specific client by their ID.
     *
     * @param clientId ID of the client.
     * @return ClientDTO with the client's data.
     */
    @Override
    public ClientDTO getById(long clientId) {
        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + clientId + " nenalezen"));

        return clientMapper.toDTO(clientEntity);
    }

    /**
     * Retrieves a client entity by their ID.
     *
     * @param clientId ID of the client.
     * @return ClientEntity.
     */
    @Override
    public ClientEntity findById(long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + clientId + " nenalezen"));
    }
}

