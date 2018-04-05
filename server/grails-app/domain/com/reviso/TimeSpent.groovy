package com.reviso

class TimeSpent {


    Double timeSpent
    Date dateCreated
    static belongsTo = [ projectId : Project ]

    static constraints = {
    }
}
