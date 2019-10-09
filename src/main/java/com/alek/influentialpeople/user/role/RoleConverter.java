package com.alek.influentialpeople.user.role;

import com.alek.influentialpeople.user.role.entity.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role.Roles, String> {

    @Override
    public String convertToDatabaseColumn(Role.Roles role) {
        if (role == null) {
            return null;
        }
        return role.name();
    }

    @Override
    public Role.Roles convertToEntityAttribute(String role) {
        if (role == null) {
            return null;
        }

        return Stream.of(Role.Roles.values())
                .filter(c -> c.name().equals(role))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}