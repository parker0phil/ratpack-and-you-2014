package application

import ratpack.groovy.test.LocalScriptApplicationUnderTest
import ratpack.test.http.TestHttpClient
import ratpack.test.http.TestHttpClients
import spock.lang.Specification

class LogoutSpec extends Specification {

    LocalScriptApplicationUnderTest aut = new LocalScriptApplicationUnderTest()
    @Delegate
    TestHttpClient client = TestHttpClients.testHttpClient(aut)

    def "user logging out should clear cookie"() {
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

        when:
        get('logout')

        then:
        response.headers.get('Set-Cookie').startsWith('email=; Expires=')
        response.statusCode == 200
        response.body.text == 'Hello guest'

    }

}
