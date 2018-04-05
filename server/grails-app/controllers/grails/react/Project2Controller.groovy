package grails.react

import com.reviso.ProjectImplService
import com.reviso.ProjectService

class Project2Controller {

	static responseFormats = ['json', 'xml']
    ProjectService projectService
    ProjectImplService projectImplService
    static allowedMethods = [save: "POST", delete: "DELETE", updateState: "PUT"]

    def index() { }


    def updateState(Long id) {

        def result = projectImplService.updateState(id)
        flash.totalTime = projectImplService.displayTotalTimeSpent(id)

        if (result.success) {
            redirect(action: "project/index")
        } else {
            render(view: '"project/index', model: [errorMessage: result.data])
        }
    }

}
