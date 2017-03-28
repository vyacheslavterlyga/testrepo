package com.training.security.service;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.transport.http.URLConnectionHTTPConduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.service.user.User;
import com.training.service.user.UserServicePortType;

@Service
public class UserDetailsService {

  @Autowired
  UserServicePortType userService;

  public UserDetails loadUserByToken(Authentication authentication) throws UsernameNotFoundException {
    org.apache.cxf.jaxws.JaxWsClientProxy proxyClient = (org.apache.cxf.jaxws.JaxWsClientProxy) Proxy.getInvocationHandler(userService);
    AuthorizationPolicy authorization = ((URLConnectionHTTPConduit) proxyClient.getClient().getConduit()).getAuthorization();
    authorization.setUserName(authentication.getName());
    authorization.setPassword(authentication.getCredentials().toString());
    User userFromDB = userService.getByLogin(authentication.getPrincipal().toString());
    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + userFromDB.getRole()));
    return new org.springframework.security.core.userdetails.User(
      authentication.getPrincipal().toString(),
      userFromDB.getPassword(),
      authorities);
  }
}
