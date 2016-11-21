package com.training.service.user

import java.util.concurrent.ThreadLocalRandom

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification

@ContextConfiguration(locations = "classpath:application-context.xml")
class UserServiceFunctionalSpec extends Specification {

  @Autowired
  UserServicePortType userServicePortType;

  def "getById"(){
    when:
    int threadCnt = 500
    int tryCount = 100
    List<Thread> threads = []
    (1..threadCnt).each {
      threads << Thread.start {
        (1..tryCount).each {
          def start = System.currentTimeMillis();
          long id = ThreadLocalRandom.current().nextLong(1, 2000)*100;
          def user = userServicePortType.getById(id)
          println id+'|'+new Date().format('dd/MM/yyyy hh:mm:ss')+'|'+(System.currentTimeMillis()-start)
        }
      }
    }
    threads.each { it.join() }
    then:
    threads.size==threadCnt
  }
}