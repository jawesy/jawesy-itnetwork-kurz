package Insurance.models.services;

import Insurance.data.entities.ClientEntity;
import Insurance.data.entities.PropertyEntity;
import Insurance.data.repositories.ClientRepository;
import Insurance.data.repositories.PropertyRepository;
import Insurance.models.dto.PropertyDTO;
import Insurance.models.dto.mappers.PropertyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Checks if property insurance exists for a given client.
     *
     * @param clientId ID of the client
     * @return true if the insurance exists, otherwise false
     */
    @Override
    public boolean existsByClientId(long clientId) {
        return propertyRepository.existsByClient_ClientId(clientId);
    }

    /**
     * Retrieves the PropertyDTO of the property insurance for a given client.
     *
     * @param clientId ID of the client
     * @return PropertyDTO object of the insurance
     */
    @Override
    public PropertyDTO getById(long clientId) {
        PropertyEntity entity = propertyRepository.findByClient_ClientId(clientId);
        if (entity == null) {
            throw new NoSuchElementException("Pojištění nemovitosti nebylo nalezeno pro klienta s ID: " + clientId);
        }
        PropertyDTO propertyDTO = propertyMapper.toDTO(entity);
        propertyDTO.setClientId(clientId);
        return propertyDTO;
    }

    /**
     * Removes property insurance based on the client ID.
     *
     * @param clientId ID of the client
     */
    @Transactional
    @Override
    public void remove(long clientId) {
        PropertyEntity entity = propertyRepository.findByClient_ClientId(clientId);
        if (entity == null) {
            throw new NoSuchElementException("Pojištění nemovitosti nebylo nalezeno pro klienta s ID: " + clientId);
        }
        propertyRepository.delete(entity);
    }

    /**
     * Creates new property insurance for the client.
     *
     * @param propertyDTO Data for the new insurance
     */
    @Override
    public void create(PropertyDTO propertyDTO) {

        ClientEntity client = clientRepository.findById(propertyDTO.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + propertyDTO.getClientId() + " nebyl nalezen"));

        PropertyEntity newProperty = propertyMapper.toEntity(propertyDTO);
        newProperty.setClient(client);

        PropertyEntity savedEntity = propertyRepository.save(newProperty);
    }

    /**
     * Updates existing property insurance for the client.
     *
     * @param propertyDTO Data for updating the insurance
     */
    @Transactional
    @Override
    public void edit(PropertyDTO propertyDTO) {
        PropertyEntity entity = propertyRepository.findByClient_ClientId(propertyDTO.getClientId());
        if (entity == null) {
            throw new NoSuchElementException("Pojištění nemovitosti nebylo nalezeno pro klienta s ID: " + propertyDTO.getClientId());
        }

        propertyMapper.updatePropertyEntity(propertyDTO, entity);
        propertyRepository.save(entity);
    }
}


