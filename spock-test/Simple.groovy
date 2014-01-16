// see the web site :
//    http://code.google.com/p/spock/
//
@Grab(group='org.spockframework', module='spock-core', version='0.7-groovy-2.0')

import spock.lang.*

class MyFirstSpec extends Specification {
  def "let's try this!"() {
    expect:
    Math.max(1, 2) == 3
  }
}
