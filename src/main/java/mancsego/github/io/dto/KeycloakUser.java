package mancsego.github.io.dto;

import mancsego.github.io.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

@Getter
@Setter
public class KeycloakUser extends AbstractUserAdapterFederatedStorage {
    private String id;
    private User user;

    public KeycloakUser(
            KeycloakSession session,
            RealmModel realm,
            ComponentModel model,
            User user
    ) {
        super(session, realm, model);
        this.user = user;
        this.id = StorageId.keycloakId(model, String.valueOf(user.getId()));

        this.setSingleAttribute("email", user.getEmail());
        this.setSingleAttribute("firstName", user.getFirstName());
        this.setSingleAttribute("lastName", user.getLastName());
    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public void setUsername(String s) {
        user.setEmail(s);
    }

    @Override
    public String getEmail(){
        return user.getEmail();
    }

    @Override
    public void setEmail(String email){
        user.setEmail(email);
    }

    @Override
    public String getFirstName() {
        return user.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        user.setFirstName(firstName);
        setSingleAttribute("firstName", firstName);
    }

    @Override
    public String getLastName() {
        return user.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        user.setLastName(lastName);
        setSingleAttribute("lastName", lastName);
    }
}
