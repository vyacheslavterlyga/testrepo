package com.training.translator.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.translator.Translator;

public abstract class AbstractTranslator<BOM, BEO> implements Translator<BOM, BEO> {

  @Autowired
  DozerBeanMapper dozerBeanMapper;

  protected Class<BOM> getGenericBOMClass() {
    return (Class<BOM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  protected Class<BEO> getGenericBEOClass() {
    return (Class<BEO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
  }

  @Override
  public BOM toBOM(BEO userBEO) {
    if (userBEO == null)
      return null;
    return dozerBeanMapper.map(userBEO, getGenericBOMClass());
  }

  @Override
  public BEO toBEO(BOM userBOM) {
    if (userBOM == null)
      return null;
    BEO userMaped = dozerBeanMapper.map(userBOM, getGenericBEOClass());
    return userMaped;
  }

  @Override
  public List<BOM> toBOMList(List<BEO> userBEOList) {
    List<BOM> result = new ArrayList<>();
    if (userBEOList == null)
      return result;
    for (BEO beo : userBEOList) {
      result.add(toBOM(beo));
    }
    return result;
  }

  @Override
  public List<BEO> toBEOList(List userBOMList) {
    if (userBOMList == null)
      return null;
    return dozerBeanMapper.map(userBOMList, List.class);
  }
}
