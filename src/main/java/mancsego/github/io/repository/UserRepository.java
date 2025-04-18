package mancsego.github.io.repository;

import mancsego.github.io.entity.User;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository {
    Optional<User> findUserById(String id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Stream<User> search(String searchParam);
}
