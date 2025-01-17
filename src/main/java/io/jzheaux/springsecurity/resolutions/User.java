package io.jzheaux.springsecurity.resolutions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity(name="users")
public class User implements Serializable {
    @OneToMany(fetch= FetchType.EAGER, cascade =CascadeType.ALL)
    Collection<UserAuthority> userAuthorities = new ArrayList<>();
    @Id
    UUID id;

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    @Column(name="subscription")
    String subscription;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Collection<User> friends = new ArrayList<>();

    public Collection<User> getFriends(){
        return  friends;
    }

    public void addFriend(User friend){
        friends.add(friend);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name="full_name")
    String fullName;

    @Column
    String username;

    @Column
    String password;

    @Column
    boolean enabled= true;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<UserAuthority> getUserAuthorities() {
        return userAuthorities;
    }

    public void grantAuthority(String authority){
        this.userAuthorities.add(new UserAuthority(this, authority));
    }


    public User() {}

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.fullName = user.fullName;
        this.password = user.password;
        this.enabled = user.enabled;
        this.subscription = user.subscription;
        this.friends = user.friends;
        this.userAuthorities = user.userAuthorities;
    }
}
