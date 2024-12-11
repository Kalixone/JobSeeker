package com.cvenjoyer.cv_enjoyer.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"roles", "badges", "programmingLanguages", "frameworks", "experienceLevel", "favourite"})
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String city;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    private String englishLevel;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_frameworks", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "framework")
    private Set<String> frameworks;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_languages", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "language")
    private Set<String> programmingLanguages;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_experience_levels", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "experience_level")
    private Set<String> experienceLevel;
    private Integer dailyGoal;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_favourite_jobs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private List<Job> favourite;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_badges",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id"))
    private Set<Badge> badges;
    private int cvSent;
    @ToStringExclude
    private boolean isDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isDeleted;
    }

    public void decrementDailyGoal() {
        if (dailyGoal != null && dailyGoal > 0) {
            this.dailyGoal -= 1;
        }
    }
    public void incrementCvSent() {
        this.cvSent += 1;
    }
}
