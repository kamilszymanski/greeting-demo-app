package com.example.greeting.boundary

import com.example.greeting.domain.Greetings
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GreetingController)
class GreetingControllerSpec extends Specification {

    @Autowired
    MockMvc mvc

    @SpringBean
    Greetings greetings = Mock()

    def 'should greet officially'() {
        given:
            greetings.officialGreeting('World') >> 'Hello World'
        expect:
            mvc.perform(get('/greetings/hello'))
                    .andExpect(status().isOk())
                    .andExpect(content().string('Hello World'))
    }

    def 'should greet casually'() {
        given:
            greetings.casualGreeting('Joe') >> 'Hi Joe'
        expect:
            mvc.perform(get('/greetings/hi').queryParam('name', 'Joe'))
                    .andExpect(status().isOk())
                    .andExpect(content().string('Hi Joe'))
    }
}
