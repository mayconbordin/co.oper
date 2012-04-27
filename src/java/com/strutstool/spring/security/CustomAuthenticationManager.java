package com.strutstool.spring.security;

import com.cooper.model.entity.Usuario;
import com.cooper.model.repository.UsuarioRepository;
import com.cooper.model.repository.hibernate.UsuarioRepositoryHibernate;
import com.strutstool.hash.SHA256;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * A custom authentication manager that allows access if the user details
 * exist in the database and if the username and password are not the same.
 * Otherwise, throw a {@link BadCredentialsException}
 */
public class CustomAuthenticationManager implements AuthenticationManager {
    protected static final Logger logger = Logger.getLogger(CustomAuthenticationManager.class);

    // Our custom DAO layer
    private UsuarioRepository userRepository = new UsuarioRepositoryHibernate();

    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        logger.debug("Performing custom authentication");

        // Init a database user object
        Usuario user = null;

        try {
            // Retrieve user details from database
            user = userRepository.get(auth.getName());

            if (user == null) {
                logger.error("User does not exists!");
                throw new BadCredentialsException("User does not exists!");
            }
        } catch (Exception e) {
            logger.error("User does not exists!");
            throw new BadCredentialsException("User does not exists!");
        }

        // Compare passwords
        // Make sure to encode the password first before comparing
        String passw = SHA256.calculate((String) auth.getCredentials());
        if (!user.getSenha().equals(passw)) {
            logger.error("Wrong password!");
            throw new BadCredentialsException("Wrong password!");
        }

        // Here's the main logic of this custom authentication manager
        // Username and password must be the same to authenticate
        //if (auth.getName().equals(auth.getCredentials()) == true) {
        //    logger.debug("Entered username and password are the same!");
        //    throw new BadCredentialsException("Entered username and password are the same!");
        //} else {
            logger.debug("User details are good and ready to go");
            return new UsernamePasswordAuthenticationToken(
                auth.getName(),
                auth.getCredentials(),
                getAuthorities(user.getNivel()));
        //}
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