package Insurance.models.services;

import Insurance.data.entities.ClientEntity;
import Insurance.data.entities.CrashEntity;
import Insurance.data.repositories.ClientRepository;
import Insurance.data.repositories.CrashRepository;
import Insurance.models.dto.CrashDTO;
import Insurance.models.dto.mappers.CrashMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class CrashServiceImpl implements CrashService {

    @Autowired
    private CrashMapper crashMapper;

    @Autowired
    private CrashRepository crashRepository;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Checks if crash insurance exists for a given client ID.
     *
     * @param clientId The ID of the client
     * @return true if the insurance exists, false otherwise
     */
    @Override
    public boolean existsByClientId(long clientId) {
        return crashRepository.existsByClient_ClientId(clientId);
    }

    /**
     * Retrieves crash insurance details for a given client ID.
     *
     * @param clientId The ID of the client
     * @return CrashDTO containing the insurance details
     */
    @Override
    public CrashDTO getById(long clientId) {
        CrashEntity entity = crashRepository.findByClient_ClientId(clientId);
        if (entity == null) {
            throw new NoSuchElementException("Havarijní pojištění nebylo nalezeno pro klienta s ID: " + clientId);
        }

        CrashDTO crashDTO = crashMapper.toDTO(entity);
        crashDTO.setClientId(clientId);
        return crashDTO;
    }

    /**
     * Deletes crash insurance for a given client ID.
     *
     * @param clientId The ID of the client
     */
    @Transactional
    @Override
    public void remove(long clientId) {
        CrashEntity entity = crashRepository.findByClient_ClientId(clientId);
        if (entity == null) {
            throw new NoSuchElementException("Havarijní pojištění nebylo nalezeno pro klienta s ID: " + clientId);
        }

        crashRepository.delete(entity);
    }

    /**
     * Creates new crash insurance for a client.
     *
     * @param crashDTO Data for the new crash insurance
     */
    @Override
    public void create(CrashDTO crashDTO) {
        ClientEntity client = clientRepository.findById(crashDTO.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + crashDTO.getClientId() + " nebyl nalezen"));

        CrashEntity newCrash = crashMapper.toEntity(crashDTO);
        newCrash.setClient(client);

        crashRepository.save(newCrash);
    }

    /**
     * Updates an existing crash insurance policy.
     *
     * @param crashDTO Data for updating the crash insurance
     */
    @Transactional
    @Override
    public void edit(CrashDTO crashDTO) {
        CrashEntity entity = crashRepository.findByClient_ClientId(crashDTO.getClientId());
        if (entity == null) {
            throw new NoSuchElementException("Havarijní pojištění nebylo nalezeno pro klienta s ID: " + crashDTO.getClientId());
        }

        crashMapper.updateCrashEntity(crashDTO, entity);
        crashRepository.save(entity);
    }
}

