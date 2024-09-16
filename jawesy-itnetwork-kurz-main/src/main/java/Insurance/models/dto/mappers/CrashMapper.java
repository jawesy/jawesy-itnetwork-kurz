package Insurance.models.dto.mappers;

import Insurance.data.entities.ClientEntity;
import Insurance.data.entities.CrashEntity;
import Insurance.data.repositories.ClientRepository;
import Insurance.models.dto.CrashDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.NoSuchElementException;

@Mapper(componentModel = "spring")
public interface CrashMapper {

    /**
     * Converts a CrashDTO object into a CrashEntity.
     *
     * @param source the CrashDTO object to be converted.
     * @return the corresponding CrashEntity.
     */
    CrashEntity toEntity(CrashDTO source);

    /**
     * Converts a CrashEntity object into a CrashDTO.
     *
     * @param source the CrashEntity object to be converted.
     * @return the corresponding CrashDTO.
     */
    CrashDTO toDTO(CrashEntity source);

    /**
     * Updates an existing CrashDTO with data from another CrashDTO.
     *
     * @param source the CrashDTO containing updated data.
     * @param targetDTO the existing CrashDTO to be updated.
     */
    void updateCrashDTO(CrashDTO source, @MappingTarget CrashDTO targetDTO);

    /**
     * Updates an existing CrashEntity with data from a CrashDTO.
     *
     * @param source the CrashDTO containing updated data.
     * @param targetEntity the existing CrashEntity to be updated.
     */
    void updateCrashEntity(CrashDTO source, @MappingTarget CrashEntity targetEntity);

    /**
     * Maps a CrashDTO to a CrashEntity, including setting the ClientEntity.
     *
     * @param source the CrashDTO to be mapped.
     * @param clientRepository repository to find the associated ClientEntity.
     * @return the mapped CrashEntity with the associated ClientEntity.
     */
    default CrashEntity mapToEntityWithClient(CrashDTO source, ClientRepository clientRepository) {
        CrashEntity entity = toEntity(source);
        ClientEntity client = clientRepository.findById(source.getClientId())
                .orElseThrow(() -> new NoSuchElementException("Klient s ID " + source.getClientId() + " nebyl nalezen"));
        entity.setClient(client);
        return entity;
    }
}
