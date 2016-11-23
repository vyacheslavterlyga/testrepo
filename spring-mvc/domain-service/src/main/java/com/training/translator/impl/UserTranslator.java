package com.training.translator.impl;

import com.training.persistence.model.User;

public class UserTranslator extends AbstractTranslator<com.training.service.user.User, com.training.persistence.model.User> {

  @Override
  public com.training.persistence.model.User toBEO(com.training.service.user.User userBOM) {
    User beo = super.toBEO(userBOM);
    if (beo == null)
      return null;
    beo.getPerson().setUser(beo);
    return beo;
  }
}
