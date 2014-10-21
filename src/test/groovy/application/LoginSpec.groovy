package application

import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.test.http.TestHttpClient
import ratpack.test.http.TestHttpClients
import spock.lang.Specification

class LoginSpec extends Specification {

    LocalScriptApplicationUnderTest aut = new LocalScriptApplicationUnderTest()
    @Delegate
    TestHttpClient client = TestHttpClients.testHttpClient(aut)

    def "login should set cookie and welcome user"() {
        given:
        def email = 'real@email.com'

        when:
        get()

        then:
        response.statusCode == 200
        response.body.text == 'Hello guest'

        when:
        get("login?email=${email}")

        then:
        response.headers.get('Set-Cookie') == /email="${email}"/
        response.statusCode == 200
        response.body.text == "Hello ${email}"
    }

    def "user with email cookie sees correct message"() {
        given:
        def email = 'real@email.com'
        requestSpec { spec ->
            spec.headers { headers ->
                headers.set('Cookie', "email=${email}")
            }
        }

        when:
        get()

        then:
        response.statusCode == 200
        response.body.text == "Hello ${email}"
    }

}
