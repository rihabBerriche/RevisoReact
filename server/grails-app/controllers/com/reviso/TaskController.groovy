package com.reviso

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TaskController {

    TaskService taskService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond taskService.list(params), model:[taskCount: taskService.count()]
    }

    def show(Long id) {
        respond taskService.get(id)
    }

    def save(Task task) {
        if (task == null) {
            render status: NOT_FOUND
            return
        }

        try {
            taskService.save(task)
        } catch (ValidationException e) {
            respond task.errors, view:'create'
            return
        }

        respond task, [status: CREATED, view:"show"]
    }

    def update(Task task) {
        if (task == null) {
            render status: NOT_FOUND
            return
        }

        try {
            taskService.save(task)
        } catch (ValidationException e) {
            respond task.errors, view:'edit'
            return
        }

        respond task, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        taskService.delete(id)

        render status: NO_CONTENT
    }
}
