package application

import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.test.http.TestHttpClient
import ratpack.test.http.TestHttpClients
import spock.lang.Specification

class SiteSmokeSpec extends Specification {

    LocalScriptApplicationUnderTest aut = new LocalScriptApplicationUnderTest()
    @Delegate
    TestHttpClient client = TestHttpClients.testHttpClient(aut)

    def "plain request results in 200 and Hello Message"() {
        when:
        get()

        then:
        response.statusCode == 200
        response.body.text == "Hello guest"
    }

}
