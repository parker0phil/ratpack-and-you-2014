package chains

import ratpack.func.Action
import ratpack.groovy.Groovy
import ratpack.handling.Chain

/**
 * Created by danny on 2014-10-21.
 */
class ThreeFourChain implements Action<Chain> {
  @Override
  void execute(Chain chain) throws Exception {
    Groovy.chain(chain) {
      handler {
        println "3"
        next()
      }
      handler {
        println "4"
        next()
      }
    }
  }
}
