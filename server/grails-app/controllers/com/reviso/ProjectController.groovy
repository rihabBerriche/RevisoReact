package com.reviso

class ProjectController {

    ProjectService projectService
    ProjectImplService projectImplService
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", delete: "DELETE", updateState: "PUT"]




    def index(Integer max) {
        log.info("you have been here ")
        params.max = Math.min(max ?: 10, 100)
        respond projectService.list(params), model:[projectCount: projectService.count()]
     }

    def updateState(Long id) {

        def result = projectImplService.updateState(id)
        flash.totalTime = projectImplService.displayTotalTimeSpent(id)

        if (result.success) {
            redirect(action: "index")
        } else {
            render(view: 'index', model: [errorMessage: result.data])
        }
    }

    def show(Long id) {

        respond projectService.get(id)
    }
//
//    def save(Project project) {
//        if (project == null) {
//            render status: NOT_FOUND
//            return
//        }
//
//        try {
//            projectService.save(project)
//        } catch (ValidationException e) {
//            respond project.errors, view:'create'
//            return
//        }
//
//        respond project, [status: CREATED, view:"show"]
//    }
//
//    def update(Project project) {
//        if (project == null) {
//            render status: NOT_FOUND
//            return
//        }
//
//        try {
//            projectService.save(project)
//        } catch (ValidationException e) {
//            respond project.errors, view:'edit'
//            return
//        }
//
//        respond project, [status: OK, view:"show"]
//    }
//
//    def delete(Long id) {
//        if (id == null) {
//            render status: NOT_FOUND
//            return
//        }
//
//        projectService.delete(id)
//
//        render status: NO_CONTENT
//    }
}
