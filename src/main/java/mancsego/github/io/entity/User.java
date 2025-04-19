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
        @NamedQuery(name="getUserByEmail", query="select u from User u where u.email = :email"),
        @NamedQuery(name="getUserCount", query="select count(u) from User u"),
        @NamedQuery(name="getAllUsers", query="select u from User u"),
        @NamedQuery(name="searchForUser", query="select u from User u where u.email like :search order by u.email"),
})
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
