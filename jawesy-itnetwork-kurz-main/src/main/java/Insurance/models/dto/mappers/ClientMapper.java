package Insurance.models.dto.mappers;

import Insurance.data.entities.ClientEntity;
import Insurance.models.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    /**
     * Converts a ClientDTO object into a ClientEntity.
     *
     * @param source the ClientDTO object to be converted.
     * @return the corresponding ClientEntity.
     */
    ClientEntity toEntity(ClientDTO source);

    /**
     * Converts a ClientEntity object into a ClientDTO.
     *
     * @param source the ClientEntity object to be converted.
     * @return the corresponding ClientDTO.
     */
    ClientDTO toDTO(ClientEntity source);

    /**
     * Converts a list of ClientEntity objects into a list of ClientDTOs.
     *
     * @param source the list of ClientEntity objects to be converted.
     * @return the corresponding list of ClientDTOs.
     */
    List<ClientDTO> toDTOList(List<ClientEntity> source);

    /**
     * Updates an existing ClientEntity with data from a ClientDTO.
     *
     * @param source the ClientDTO containing updated data.
     * @param target the existing ClientEntity to be updated.
     */
    void updateClientEntity(ClientDTO source, @MappingTarget ClientEntity target);
}
