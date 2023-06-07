package uz.pdp.appticket.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum PermissionName implements GrantedAuthority {
    DELETE_USER,
    CREATE_USER,
    UPDATE_USER,
    READ_USER,
    DELETE_HALL,
    UPDATE_HALL,
    READ_HALL,
    CREATE_HALL,
    UPDATE_EVENT,
    CREATE_EVENT,
    DELETE_EVENT,
    READ_EVENT,
    UPDATE_TICKET,
    DELETE_TICKET,
    BUY_TICKET;

    @Override
    public String getAuthority() {
        return name();
    }
}
