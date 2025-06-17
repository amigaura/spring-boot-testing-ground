//// src/main/java/com/testing/ground/entity/user/AppUserRole.java
//package com.testing.ground.entity.user;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
////@Entity
////@Table(name = "app_user_roles")
////@Data
//public class AppUserRole {
//
//    @EmbeddedId
//    private AppUserRoleId id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("appUser")
//    @JoinColumn(name = "app_user_id", nullable = false)
//    private AppUser appUser;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("userRole")
//    @JoinColumn(name = "role_id", nullable = false)
//    private UserRole userRole;
//}