package com.reviso

import grails.gorm.services.Service

@Service(TimeSpent)
interface TimeSpentService {

    TimeSpent get(Serializable id)

    List<TimeSpent> list(Map args)

    Long count()

    void delete(Serializable id)

    TimeSpent save(TimeSpent timeSpent)

}