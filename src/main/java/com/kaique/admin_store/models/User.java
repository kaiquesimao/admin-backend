package com.kaique.admin_store.models;

import com.kaique.admin_store.dtos.RegisterDto;
import com.kaique.admin_store.enums.UserRolesEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "User")
@Table(name = "user", schema = "admin_store")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false, insertable = false)
    private UUID id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100, columnDefinition = "varchar(100)")
    private String name;

    @Nullable
    @Size(max = 20)
    @Column(name = "tel", length = 20, columnDefinition = "varchar(20)")
    private String tel;

    @NotNull
    @Size(max = 100)
    @Column(name = "email", nullable = false, unique = true, length = 100, columnDefinition = "varchar(100)")
    private String email;

    @NotNull
    @Column(name = "password", nullable = false, columnDefinition = "varchar(255)")
    private String password;

    @Nullable
    @Size(max = 50)
    @Column(name = "status", length = 50, columnDefinition = "varchar(50)")
    private String status;

    @ElementCollection(targetClass = UserRolesEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", schema = "admin_store", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "ENUM('ADMIN', 'USER')")
    private Collection<UserRolesEnum> roles;

    public User(RegisterDto registerDto) {
        this.name = registerDto.name();
        this.tel = registerDto.tel();
        this.email = registerDto.email();
        this.password = registerDto.password();
        this.roles = registerDto.roles();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles.contains(UserRolesEnum.ADMIN))
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
