package Insurance.models.dto.mappers;

import Insurance.data.entities.ClientEntity;
import Insurance.data.entities.PropertyEntity;
import Insurance.data.repositories.ClientRepository;
import Insurance.models.dto.PropertyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.NoSuchElementException;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    /**
     * Converts a PropertyDTO to a PropertyEntity.
     *
     * @param source the PropertyDTO object to be converted.
     * @return the corresponding PropertyEntity.
     */
    PropertyEntity toEntity(PropertyDTO source);

    /**
     * Converts a PropertyEntity to a PropertyDTO.
     *
     * @param source the PropertyEntity object to be converted.
     * @return the corresponding PropertyDTO.
     */
    PropertyDTO toDTO(PropertyEntity source);

    /**
     * Updates an existing PropertyDTO with data from another PropertyDTO.
     *
     * @param source the PropertyDTO containing updated data.
     * @param targetDTO the existing PropertyDTO to be updated.
     */
    void updatePropertyDTO(PropertyDTO source, @MappingTarget PropertyDTO targetDTO);

    /**
     * Updates an existing PropertyEntity with data from a PropertyDTO.
     *
     * @param source the PropertyDTO containing updated data.
     * @param targetEntity the existing PropertyEntity to be updated.
     */
    void updatePropertyEntity(PropertyDTO source, @MappingTarget PropertyEntity targetEntity);

    /**
     * Maps a PropertyDTO to a PropertyEntity, including setting the ClientEntity.
     *
     * @param source the PropertyDTO to be mapped.
     * @param clientRepository repository to find the associated ClientEntity.
     * @return the mapped PropertyEntity with the associated ClientEntity.
     */
    default PropertyEntity mapToEntityWithClient(PropertyDTO source, ClientRepository clientRepository) {
        PropertyEntity entity = toEntity(source);

        // Load the client from the database using ClientRepository
        ClientEntity client = clientRepository.findById(source.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + source.getClientId() + " nebyl nalezen"));

        entity.setClient(client);
        return entity;
    }
}
