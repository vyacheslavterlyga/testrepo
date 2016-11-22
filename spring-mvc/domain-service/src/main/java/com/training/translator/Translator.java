package com.training.translator;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface Translator<BOM, BEO> {

  BOM toBOM(BEO BEO);

  BEO toBEO(BOM BOM);

  List<BOM> toBOMList(List<BEO> userBEOList);

  List<BEO> toBEOList(List<BOM> userBOMList);
}
