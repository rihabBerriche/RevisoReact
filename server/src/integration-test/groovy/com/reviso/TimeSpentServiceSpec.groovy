package com.reviso

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TimeSpentServiceSpec extends Specification {

    TimeSpentService timeSpentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TimeSpent(...).save(flush: true, failOnError: true)
        //new TimeSpent(...).save(flush: true, failOnError: true)
        //TimeSpent timeSpent = new TimeSpent(...).save(flush: true, failOnError: true)
        //new TimeSpent(...).save(flush: true, failOnError: true)
        //new TimeSpent(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //timeSpent.id
    }

    void "test get"() {
        setupData()

        expect:
        timeSpentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TimeSpent> timeSpentList = timeSpentService.list(max: 2, offset: 2)

        then:
        timeSpentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        timeSpentService.count() == 5
    }

    void "test delete"() {
        Long timeSpentId = setupData()

        expect:
        timeSpentService.count() == 5

        when:
        timeSpentService.delete(timeSpentId)
        sessionFactory.currentSession.flush()

        then:
        timeSpentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TimeSpent timeSpent = new TimeSpent()
        timeSpentService.save(timeSpent)

        then:
        timeSpent.id != null
    }
}
