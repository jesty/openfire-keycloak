# openfire-keycloak
A simple integration to use Keycloak with Openfire

# Setup:

* Download the JAR in dist folder or, if you prefer, build the project.
* Download the database driver that you use for Keycloak.
* Copy the 2 jars to lib/ folder in your openfire distribution.
* Create a realm named `Openfire` and a client named `test` on Keycloak.
* Download your keycloak.json from Keycloak Admin console and copy in lib/ folder. The file should be like:  
  ```json
  {
    "realm": "Openfire",
    "auth-server-url": "http://localhost:8280/auth",
    "resource": "test",
    "credentials": {
      "secret": "19b0c4f6-d7b6-4340-ad7d-35c2eefc41ec"
    }
  }
  ```  
  I used Keycloak Authorization Services, more information could be read at https://keycloak.gitbooks.io/authorization-services-guide/content/v/2.2/topics/service/client-api.html
* Open openfire.xml and configure as below:  
    ```xml
  <provider>
    <auth>
      <className>com.nutcore.openfirekeycloak.KeyloackAuthProvider</className>
    </auth>
    <user>
      <className>org.jivesoftware.openfire.user.JDBCUserProvider</className>
    </user>
  </provider>
  <jdbcProvider>
    <driver>org.postgresql.Driver</driver>
    <connectionString>jdbc:postgresql:///keycloak?user=prostgres&amp;password=admin</connectionString>
  </jdbcProvider>
  <jdbcUserProviderSELECT username, email FROM user_entity where realm_id = 'Openfire' and username=?</loadUserSQL>
     <userCountSQL>SELECT COUNT(*) FROM user_entity where realm_id = 'Openfire'</userCountSQL>
     <allUsersSQL>SELECT username FROM user_entity where realm_id = 'Openfire'</allUsersSQL>
     <searchSQL>SELECT username FROM user_entity where realm_id = 'Openfire' and</searchSQL>
     <usernameField>username</usernameField>
     <nameField>username</nameField>
     <emailField>email</emailField>
  </jdbcUserProvider>
```

  The AuthProvider connect to Keycloak and authenticate the user, while the UserProvider connect directly to Keycloak database. In future I can use the Keycloak Admin api instead of direct connection. 
  More information about JDBCUserProvider could be read at http://web.mit.edu/ghudson/dev/openfire/documentation/docs/db-integration-guide.html

Now restart Openfire and enjoy!
