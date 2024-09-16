package Insurance.data.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean admin;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClientEntity client;

    // Getters and Setters

    /**
     * Gets the user ID.
     *
     * @return the user ID.
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID to set.
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets the email of the user.
     *
     * @return the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if the user is an admin.
     *
     * @return true if the user is an admin, false otherwise.
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the admin status of the user.
     *
     * @param admin the admin status to set.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Gets the client associated with the user.
     *
     * @return the client entity.
     */
    public ClientEntity getClient() {
        return client;
    }

    /**
     * Sets the client associated with the user.
     *
     * @param client the client entity to set.
     */
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    // Implementations of UserDetails interface methods

    /**
     * Gets the authorities granted to the user. Admins have the "ADMIN" role,
     * and regular users have the "USER" role.
     *
     * @return the collection of authorities granted to the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + (admin ? "ADMIN" : "USER"));
        return List.of(authority);
    }

    /**
     * Gets the username used to authenticate the user. This is the user's email.
     *
     * @return the user's email.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true if the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     *
     * @return true if the credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
