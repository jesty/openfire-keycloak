package com.nutcore.openfirekeycloak;

import org.jivesoftware.openfire.auth.AuthProvider;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.keycloak.authorization.client.AuthzClient;

public class KeycloakAuthProvider implements AuthProvider
{

    public void authenticate(String username, String password) throws UnauthorizedException
    {
        AuthzClient authzClient = AuthzClient.create();
        try
        {
            authzClient.authorization(username, password);
        } catch (Exception e)
        {
            throw new UnauthorizedException(e);
        }
    }

    public void authenticate(String username, String token, String digest)
            throws UnauthorizedException
    {
        throw new UnauthorizedException("Digest authentication not supported.");

    }

    public String getPassword(String username) throws UserNotFoundException, UnsupportedOperationException
    {
        System.out.println("GET PASSWORD");
        return "1234";
    }

    public boolean isDigestSupported()
    {
        return false;
    }

    public boolean isPlainSupported()
    {
        return false;
    }

    public void setPassword(String username, String password)
            throws UserNotFoundException, UnsupportedOperationException
    {
        throw new UnsupportedOperationException("Unsupported Operation");

    }

    public boolean supportsPasswordRetrieval()
    {
        return false;
    }

}

