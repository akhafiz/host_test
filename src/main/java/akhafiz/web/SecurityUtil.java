package akhafiz.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class SecurityUtil {

    public static boolean isAllowRole(String role) {

        if (role == null || role.isEmpty()) {
            return false;
        }

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            return false;
        }

        Collection<? extends GrantedAuthority> availableRoles = currentUser.getAuthorities();

        if (availableRoles != null &&  !availableRoles.isEmpty()) {
            for (GrantedAuthority availableRole : availableRoles) {
                if (role.equals(availableRole.getAuthority())) {
                    return true;
                }
            }

        }

        return false;
    }

}
