package Insurance.models.services;

import Insurance.data.entities.ClientEntity;
import Insurance.data.entities.MandatoryEntity;
import Insurance.data.repositories.ClientRepository;
import Insurance.data.repositories.MandatoryRepository;
import Insurance.models.dto.MandatoryDTO;
import Insurance.models.dto.mappers.MandatoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class MandatoryServiceImpl implements MandatoryService {

    @Autowired
    private MandatoryMapper mandatoryMapper;

    @Autowired
    private MandatoryRepository mandatoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Checks if mandatory insurance exists for a specific client.
     *
     * @param clientId ID of the client
     * @return true if insurance exists, otherwise false
     */
    @Override
    public boolean existsByClientId(long clientId) {
        return mandatoryRepository.existsByClientId(clientId);
    }

    /**
     * Retrieves the DTO for the mandatory insurance of a client.
     *
     * @param clientId ID of the client
     * @return MandatoryDTO object representing the insurance
     */
    @Override
    public MandatoryDTO getById(long clientId) {
        MandatoryEntity entity = mandatoryRepository.findByClient_ClientId(clientId);
        if (entity == null) {
            throw new NoSuchElementException("Povinné pojištění nebylo nalezeno pro klienta s ID: " + clientId);
        }

        MandatoryDTO mandatoryDTO = mandatoryMapper.toDTO(entity);
        mandatoryDTO.setClientId(clientId);
        return mandatoryDTO;
    }

    /**
     * Deletes the mandatory insurance based on client ID.
     *
     * @param clientId ID of the client
     */
    @Transactional
    @Override
    public void remove(long clientId) {
        MandatoryEntity entity = mandatoryRepository.findByClient_ClientId(clientId);
        if (entity == null) {
            throw new NoSuchElementException("Povinné pojištění nebylo nalezeno pro klienta s ID: " + clientId);
        }

        mandatoryRepository.delete(entity);
    }

    /**
     * Creates a new mandatory insurance for a client.
     *
     * @param mandatoryDTO Data of the new insurance.
     */
    @Override
    public void create(MandatoryDTO mandatoryDTO) {
        ClientEntity client = clientRepository.findById(mandatoryDTO.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + mandatoryDTO.getClientId() + " nebyl nalezen"));

        MandatoryEntity newMandatory = mandatoryMapper.toEntity(mandatoryDTO);
        newMandatory.setClient(client);

        mandatoryRepository.save(newMandatory);
    }

    /**
     * Updates an existing mandatory insurance.
     *
     * @param mandatoryDTO Data for updating the insurance.
     */
    @Transactional
    @Override
    public void edit(MandatoryDTO mandatoryDTO) {
        MandatoryEntity entity = mandatoryRepository.findByClient_ClientId(mandatoryDTO.getClientId());
        if (entity == null) {
            throw new NoSuchElementException("Povinné pojištění nebylo nalezeno pro klienta s ID: " + mandatoryDTO.getClientId());
        }

        mandatoryMapper.updateMandatoryEntity(mandatoryDTO, entity);
        mandatoryRepository.save(entity);
    }
}



