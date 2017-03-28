package com.training.security.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  private com.training.security.service.UserDetailsService userDetailsService;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    if (authentication.getCredentials() == null) {
      logger.debug("Authentication failed: no credentials provided");

      throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
    }
  }

  @Override
  protected void doAfterPropertiesSet() throws Exception {
    Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
  }

  @Override
  protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    UserDetails loadedUser;

    try {
      loadedUser = userDetailsService.loadUserByToken(authentication);
    } catch (Exception repositoryProblem) {
      throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
    }

    if (loadedUser == null) {
      throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
    }
    return loadedUser;
  }

  public void setUserDetailsService(com.training.security.service.UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

}
