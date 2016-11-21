import ch.qos.logback.classic.PatternLayout
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG

appender("STDOUT", ConsoleAppender) {
  layout(PatternLayout) {
    pattern = "%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n"
  }
}
logger("com.training", DEBUG, ["STDOUT"], false)
root(ERROR, ["STDOUT"])