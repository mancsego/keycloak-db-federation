package mancsego.github.io.repository;

import mancsego.github.io.entity.User;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.storage.StorageId;

@Slf4j
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private EntityManager em;

    @Override
    public Optional<User> findUserById(String id) {
        int dbId = Integer.parseInt(StorageId.externalId(id));
        return getByQuery("getUserById", "id", dbId);
    }

    public Optional<User> findByUsername(String username) {
        return getByQuery("getUserByUsername", "username", username);
    }

    public Optional<User> findByEmail(String email) {
        return getByQuery("getUserByEmail", "email", email);
    }

    public Stream<User> search(String search) {
        return em.createNamedQuery("searchForUser", User.class)
                .setParameter("search", "%" + search + "%")
                .getResultStream();

//        return em.createQuery("select c from User c where c.email like '%" + searchParam + "%'", User.class)
//                .getResultStream();
    }

    private Optional<User> getByQuery(String query, String parameter, Object value) {
        log.info("Calling {}  w/ {}: {}", query, parameter, value);

        return em.createNamedQuery(query, User.class)
                .setParameter(parameter, value)
                .getResultStream()
                .findFirst();
    }
}
