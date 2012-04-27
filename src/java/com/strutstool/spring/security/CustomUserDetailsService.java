package com.strutstool.spring.security;

import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.UsuarioRepository;
import com.cooper.model.repository.hibernate.UsuarioRepositoryHibernate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * A custom service for retrieving users from a custom datasource, such as a database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
    protected static final Logger logger = Logger.getLogger(CustomAuthenticationManager.class);

    private UsuarioRepository userRepository = new UsuarioRepositoryHibernate();

    /**
     * Retrieves a user record containing the user's credentials and access.
     */
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        // Declare a null Spring User
        UserDetails user = null;

        try {
            // Search database for a user that matches the specified username
            // You can provide a custom DAO to access your persistence layer
            // Or use JDBC to access your database
            // DbUser is our custom domain user. This is not the same as Spring's User
            Usuario dbUser = userRepository.get(username);

            if (dbUser == null) {
                throw new UsernameNotFoundException("Error in retrieving user");
            }

            // Populate the Spring User object with details from the dbUser
            // Here we just pass the username, password, and access level
            // getAuthorities() will translate the access level to the correct role type
            user =  new org.springframework.security.core.userdetails.User(
                dbUser.getCodigo(),
                dbUser.getSenha(),
                true,
                true,
                true,
                true,
                getAuthorities(dbUser.getNivel()) );
            } catch (Exception e) {
                logger.error("Error in retrieving user");
                throw new UsernameNotFoundException("Error in retrieving user");
            }

            // Return user to Spring for processing.
            // Take note we're not the one evaluating whether this user is authenticated or valid
            // We just merely retrieve a user that matches the specified username
            return user;
    }

    /**
     * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
     * Basically, this interprets the access value whether it's for a regular user or admin.
     *
     * @param access an integer value representing the access of the user
     * @return collection of granted authorities
     */
    public Collection<GrantedAuthority> getAuthorities(Integer access) {
        // Create a list of grants for this user
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        if ( access.compareTo( Usuario.NIVEL_ASSOCIADO ) == 0) {
            logger.debug("Dá NIVEL_ASSOCIADO ao usuário");
            authList.add(new SimpleGrantedAuthority("NIVEL_ASSOCIADO"));
        }

        if ( access.compareTo( Usuario.NIVEL_TECNICO ) == 0) {
            logger.debug("Dá NIVEL_TECNICO ao usuário");
            authList.add(new SimpleGrantedAuthority("NIVEL_TECNICO"));
        }

        if ( access.compareTo( Usuario.NIVEL_FUNCIONARIO ) == 0) {
            logger.debug("Dá NIVEL_FUNCIONARIO ao usuário");
            authList.add(new SimpleGrantedAuthority("NIVEL_FUNCIONARIO"));
        }

        // Return list of granted authorities
        return authList;
    }
}