package Insurance.models.dto.mappers;

import Insurance.data.entities.ClientEntity;
import Insurance.data.entities.MandatoryEntity;
import Insurance.data.repositories.ClientRepository;
import Insurance.models.dto.MandatoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.NoSuchElementException;

@Mapper(componentModel = "spring")
public interface MandatoryMapper {

    /**
     * Converts a MandatoryDTO to a MandatoryEntity.
     *
     * @param source the MandatoryDTO object to be converted.
     * @return the corresponding MandatoryEntity.
     */
    MandatoryEntity toEntity(MandatoryDTO source);

    /**
     * Converts a MandatoryEntity to a MandatoryDTO.
     *
     * @param source the MandatoryEntity object to be converted.
     * @return the corresponding MandatoryDTO.
     */
    MandatoryDTO toDTO(MandatoryEntity source);

    /**
     * Updates an existing MandatoryDTO with data from another MandatoryDTO.
     *
     * @param source the MandatoryDTO containing updated data.
     * @param targetDTO the existing MandatoryDTO to be updated.
     */
    void updateMandatoryDTO(MandatoryDTO source, @MappingTarget MandatoryDTO targetDTO);

    /**
     * Updates an existing MandatoryEntity with data from a MandatoryDTO.
     *
     * @param source the MandatoryDTO containing updated data.
     * @param targetEntity the existing MandatoryEntity to be updated.
     */
    void updateMandatoryEntity(MandatoryDTO source, @MappingTarget MandatoryEntity targetEntity);

    /**
     * Maps a MandatoryDTO to a MandatoryEntity, including setting the ClientEntity.
     *
     * @param source the MandatoryDTO to be mapped.
     * @param clientRepository repository to find the associated ClientEntity.
     * @return the mapped MandatoryEntity with the associated ClientEntity.
     */
    default MandatoryEntity mapToEntityWithClient(MandatoryDTO source, ClientRepository clientRepository) {
        MandatoryEntity entity = toEntity(source);

        // Load the client from the database using ClientRepository
        ClientEntity client = clientRepository.findById(source.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + source.getClientId() + " nebyl nalezen"));

        entity.setClient(client);
        return entity;
    }
}
