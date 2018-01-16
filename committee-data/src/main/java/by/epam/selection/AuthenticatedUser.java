package by.epam.selection;

import by.epam.selection.entity.Role;

import java.util.Collections;
import java.util.Set;

/**
 * Created by lex on 12/27/2017.
 */
public class AuthenticatedUser {

    public static final String SESSION_ATTRIBUTE_NAME = "authenticatedUser";

    private Long id;
    private Set<Role> roles;

    public AuthenticatedUser(Long id, Set<Role> roles) {
        this.id = id;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    /**
     * Check either User have provided role or not
     * @param role that need to be checked for this AuthenticatedUser
     * @return true only if this user has provided role
     */
    public boolean isUserInRole(String role) {
        String upperCase = role.toUpperCase();
        Role enumRole = Role.valueOf(upperCase);
        return roles.contains(enumRole);
    }

    /**
     * Check either the User have any Role from the provided Set of Roles or not
     * @param roles A set of roles that need to be checked for this AuthenticatedUser
     * @return true only if this user has at least one Role from provided Set
     */
    public boolean isUserInRole(Set<Role> roles) {
        return !Collections.disjoint(this.roles, roles);
    }

}
