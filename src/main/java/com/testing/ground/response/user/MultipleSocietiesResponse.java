package com.testing.ground.response.user;

import com.testing.ground.entity.user.AppUserSocietyMapping;
import com.testing.ground.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
//@AllArgsConstructor
public class MultipleSocietiesResponse {
    private List<SocietyMappingInfo> mappings;

    @Data
    @AllArgsConstructor
    public static class SocietyMappingInfo {
        private Long mappingId;
        private String societyName;
        private Set<UserRole> roles;
    }

    public MultipleSocietiesResponse(List<AppUserSocietyMapping> list) {
        this.mappings = list.stream()
                .map(m -> new SocietyMappingInfo(
                        m.getId(),
                        m.getSociety().getName(),
                        m.getRoles()))
                .collect(Collectors.toList());
    }
}

