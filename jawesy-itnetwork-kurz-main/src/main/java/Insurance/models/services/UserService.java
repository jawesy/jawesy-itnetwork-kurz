package Insurance.models.services;

import Insurance.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    /**
     * Creates a new user with the given data.
     * If isAdmin is true, the user will have admin privileges.
     *
     * @param user Data Transfer Object containing the user's data.
     * @param isAdmin Indicates whether the user should have admin privileges.
     */
    void create(UserDTO user, boolean isAdmin);

    /**
     * Creates a regular user without admin privileges.
     *
     * @param userDTO Data Transfer Object containing the user's data.
     */
    void createUser(UserDTO userDTO);
}
