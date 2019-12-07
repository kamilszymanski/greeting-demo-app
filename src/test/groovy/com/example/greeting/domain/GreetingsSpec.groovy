package com.example.greeting.domain

import spock.lang.Specification

class GreetingsSpec extends Specification {

    Greetings greetings = new Greetings()

    def 'should prepare official greeting'() {
        expect:
            greetings.officialGreeting('Anna') == 'Hello Anna'
    }
}
