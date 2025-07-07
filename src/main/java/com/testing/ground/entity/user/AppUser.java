package com.testing.ground.entity.user;

import com.testing.ground.constant.user.UserStatus;
import com.testing.ground.entity.society.AppUserUnitMapping;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Data
@ToString(exclude = {"societies", "userDetail"})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long societyId;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private UserCredential credential;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Set<AppUserUnitMapping> units;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_user_roles",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Set<AppUserSocietyMapping> societies;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

}

