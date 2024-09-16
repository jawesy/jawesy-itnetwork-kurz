package Insurance.data.repositories;

import Insurance.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a UserEntity by email.
     *
     * @param email the email of the user to be found.
     * @return the UserEntity associated with the given email, or null if not found.
     */
    UserEntity findByEmail(String email);
}
