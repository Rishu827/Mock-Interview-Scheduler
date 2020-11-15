package com.example.MockProject.Roles_Permission;

import java.util.Set;
import java.util.stream.Collectors;
import static com.example.MockProject.Roles_Permission.UserPermission.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;


public enum UserRole {

	USER(Sets.newHashSet(READ)), // role = 1
	ADMIN(Sets.newHashSet(READ,WRITE)); // role = 0
	
	private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
