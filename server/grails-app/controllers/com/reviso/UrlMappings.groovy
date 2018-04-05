package com.reviso

class UrlMappings {

    static mappings = {
       // delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")

        "/state/$projectId" (controller: "project", action: "updateState")
        "/"(controller: 'project', action:'index')
        "/updateState/$id"(controller: 'project', action:'updateState')

        "/call/$callId" (controller: "project", action: "updateState")
        "/update/$id"(controller: 'project', action: 'updateState')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
