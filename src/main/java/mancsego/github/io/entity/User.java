package mancsego.github.io.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@NamedQueries({
        @NamedQuery(name="getUserById", query="select u from User u where u.id = :id"),
        @NamedQuery(name="getUserByUsername", query="select u from User u where u.username = :username"),
        @NamedQuery(name="getUserByEmail", query="select u from User u where u.email = :email"),
        @NamedQuery(name="getUserCount", query="select count(u) from User u"),
        @NamedQuery(name="getAllUsers", query="select u from User u"),
        @NamedQuery(name="searchForUser", query="select u from User u where " +
                "( lower(u.username) like :search or u.email like :search ) order by u.username"),
})
@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @Column(name = "UserId")
    private int id;

    @Column(name = "Email")
    private String email;

    @Column(name = "isEmailVerified")
    private boolean isEmailVerified;

    @Column(name = "Username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "Firstname")
    private String firstName;

    @Column(name = "Lastname")
    private String lastName;

    @Column(name = "LoginAttempts")
    private int loginAttempts;

    @Column(name = "LastLoginAttempt")
    private Timestamp lastLoginAttempt;

}
