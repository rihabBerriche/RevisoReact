package com.reviso

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TimeSpentController {

    TimeSpentService timeSpentService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond timeSpentService.list(params), model:[timeSpentCount: timeSpentService.count()]
    }

    def show(Long id) {
        respond timeSpentService.get(id)
    }

    def save(TimeSpent timeSpent) {
        if (timeSpent == null) {
            render status: NOT_FOUND
            return
        }

        try {
            timeSpentService.save(timeSpent)
        } catch (ValidationException e) {
            respond timeSpent.errors, view:'create'
            return
        }

        respond timeSpent, [status: CREATED, view:"show"]
    }

    def update(TimeSpent timeSpent) {
        if (timeSpent == null) {
            render status: NOT_FOUND
            return
        }

        try {
            timeSpentService.save(timeSpent)
        } catch (ValidationException e) {
            respond timeSpent.errors, view:'edit'
            return
        }

        respond timeSpent, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        timeSpentService.delete(id)

        render status: NO_CONTENT
    }
}
