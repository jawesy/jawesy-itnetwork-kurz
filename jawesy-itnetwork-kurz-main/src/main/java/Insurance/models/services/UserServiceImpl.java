package Insurance.models.services;

import Insurance.data.entities.UserEntity;
import Insurance.data.repositories.UserRepository;
import Insurance.models.dto.UserDTO;
import Insurance.models.exceptions.DuplicateEmailException;
import Insurance.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // Dependency injection for UserRepository and PasswordEncoder
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection for UserRepository and PasswordEncoder
    public UserServiceImpl(UserRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user in the system. If passwords do not match, throws an exception.
     * Also handles saving the user with encrypted password.
     *
     * @param user Data Transfer Object containing the user's data.
     * @param isAdmin Boolean indicating whether the user should have admin privileges.
     * @throws PasswordsDoNotEqualException If the passwords do not match.
     * @throws DuplicateEmailException If a user with the given email already exists.
     */
    @Override
    public void create(UserDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new PasswordsDoNotEqualException();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAdmin(isAdmin);

        try {
            usersRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    /**
     * Creates a regular user in the system without admin privileges.
     *
     * @param userDTO Data Transfer Object containing the user's data.
     */
    @Override
    public void createUser(UserDTO userDTO) {
        // Implementation can be added here.
    }

    /**
     * Loads user details based on the provided username (email).
     *
     * @param username Email of the user to load.
     * @return UserDetails object containing user data.
     * @throws UsernameNotFoundException If the user with the given email is not found.
     */
    @Primary
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = usersRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Uživatel s emailem " + username + " nebyl nalezen.");
        }
        return user;
    }
}
