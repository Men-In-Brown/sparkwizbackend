package com.nighthawk.spring_portfolio.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nighthawk.spring_portfolio.mvc.user.UserJpaRepository;
import com.nighthawk.spring_portfolio.mvc.user.UserRole;
import com.nighthawk.spring_portfolio.mvc.user.UserRoleJpaRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class UserDetailsService implements UserDetailsService {

    @Autowired  // Inject PersonJpaRepository
    private UserJpaRepository userJpaRepository;
    @Autowired  // Inject RoleJpaRepository
    private UserRoleJpaRepository userRoleJpaRepository;
    // @Autowired  // Inject PasswordEncoder
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findByUsername(username); // setting variable user equal to the method finding the username in the database
        if(user==null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> { //loop through roles
            authorities.add(new SimpleGrantedAuthority(role.getName())); //create a SimpleGrantedAuthority by passed in role, adding it all to the authorities list, list of roles gets past in for spring security
        });
        // train spring security to User and Authorities
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

        /* Person Section */

    public  List<User>listAll() {
        return userJpaRepository.findAllByOrderByNameAsc();
    }

    // custom query to find match to name or email
    public  List<User>list(String name, String username) {
        return userJpaRepository.findByNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(name, username);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<User>listLike(String term) {
        return userJpaRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<User>listLikeNative(String term) {
        String like_term = String.format("%%%s%%",term);  // Like required % rappers
        return userJpaRepository.findByLikeTermNative(like_term);
    }

    // encode password prior to sava
    public void save(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userJpaRepository.save(user);
    }

    public User get(long id) {
        return (userJpaRepository.findById(id).isPresent())
                ? userJpaRepository.findById(id).get()
                : null;
    }

    public User getByUsername(String username) {
        return (userJpaRepository.findByUser(username));
    }

    public void delete(long id) {
        userJpaRepository.deleteById(id);
    }

    public void defaults(String password, String roleName) {
        for (User user: listAll()) {
            if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
                user.setPassword(passwordEncoder().encode(password));
            }
            if (user.getRoles().isEmpty()) {
                UserRole role = UserRoleJpaRepository.findByName(roleName);
                if (role != null) { // verify role
                    user.getRoles().add(role);
                }
            }
        }
    }


    /* Roles Section */

    public void saveRole(UserRole role) {
        UserRole roleObj = userRoleJpaRepository.findByName(role.getName());
        if (roleObj == null) {  // only add if it is not found
            userRoleJpaRepository.save(role);
        }
    }

    public  List<UserRole>listAllRoles() {
        return userRoleJpaRepository.findAll();
    }

    public UserRole findRole(String roleName) {
        return userRoleJpaRepository.findByName(roleName);
    }

    public void addRoleToUser(String username, String roleName) { // by passing in the two strings you are giving the user that certain role
        User user = userJpaRepository.findByUsername(username);
        if (user != null) {   // verify person
            UserRole role = userRoleJpaRepository.findByName(roleName);
            if (role != null) { // verify role
                boolean addRole = true;
                for (UserRole roleObj : user.getRoles()) {    // only add if user is missing role
                    if (roleObj.getName().equals(roleName)) {
                        addRole = false;
                        break;
                    }
                }
                if (addRole) user.getRoles().add(role);   // everything is valid for adding role
            }
        }
    }
}
