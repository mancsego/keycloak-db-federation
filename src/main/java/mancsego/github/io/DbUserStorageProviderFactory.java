package mancsego.github.io;

import mancsego.github.io.repository.UserRepository;
import mancsego.github.io.repository.UserRepositoryAdapter;
import org.keycloak.component.ComponentModel;
import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class DbUserStorageProviderFactory implements UserStorageProviderFactory<DbUserStorageProvider> {
    private static final String PROVIDER_ID = "dgpar-user";
    private static final String PERSISTENCE_UNIT = "user-store";

    @Override
    public DbUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new DbUserStorageProvider(session, model, createUserRepository(session));
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    private UserRepository createUserRepository(KeycloakSession session) {
        return new UserRepositoryAdapter(
                session.getProvider(JpaConnectionProvider.class, PERSISTENCE_UNIT).getEntityManager()
        );
    }
}
