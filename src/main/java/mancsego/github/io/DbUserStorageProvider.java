package mancsego.github.io;

import mancsego.github.io.dto.KeycloakUser;
import mancsego.github.io.entity.User;
import mancsego.github.io.repository.UserRepository;
import java.util.Map;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

@Slf4j
@AllArgsConstructor
public class DbUserStorageProvider implements UserStorageProvider, UserLookupProvider, UserQueryProvider {
    private final KeycloakSession session;
    private final ComponentModel model;
    private final UserRepository userRepository;

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String id) {
        try {
            return mapUser(
                    realmModel,
                    userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id))
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String username) {
        try {
            return mapUser(
                    realmModel,
                    userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username))
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String email) {
        try {
            return mapUser(
                    realmModel,
                    userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with email: " + email))
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, Map<String, String> params, Integer integer, Integer integer1) {
        try {
            String searchParam = params.getOrDefault(
                    "keycloak.session.realm.users.query.search",
                    params.getOrDefault(
                            "email",
                            params.getOrDefault("username", "")
                    )
            );
            return userRepository
                    .search("%" + searchParam + "%")
                    .map(u -> mapUser(realmModel, u));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer integer, Integer integer1) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String s, String s1) {
        return Stream.empty();
    }

    private UserModel mapUser(RealmModel realm, User user) {
        return new KeycloakUser(session, realm, model, user);
    }
}
