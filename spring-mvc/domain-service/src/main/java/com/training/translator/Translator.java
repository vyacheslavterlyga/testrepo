package com.training.translator;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface Translator {

  Object toBOM(Object BEO);

  Object toBEO(Object BOM);

  List toBOMList(List BEOList);

  List toBEOList(List BOMList);
}
