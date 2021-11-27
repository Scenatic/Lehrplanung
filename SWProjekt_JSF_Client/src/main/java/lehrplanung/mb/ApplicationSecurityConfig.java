package lehrplanung.mb;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@FormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/pages/public/login.xhtml",
                errorPage = "/pages/public/loginError.xhtml"))
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:/VS2021_HA2",
//		dataSourceLookup = "java:/MySQL_VS",
        callerQuery = "select PASSWORD from SWProjekt_user where USERNAME=?",
        groupsQuery = "select ROLENAME as GROUPNAME from SWProjekt_user_roles where USERNAME=?",
//        hashAlgorithm = PlainTextPasswordHash.class
          hashAlgorithm = PlainSHA512PasswordHash.class
//        , useFor = IdentityStore.ValidationType.PROVIDE_GROUPS
)
@ApplicationScoped
@Named
public class ApplicationSecurityConfig {

}
