package fr.baba.mongorestapi.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class Employee(
    @Id
    var id: ObjectId? = null,
    var firstName: String,
    var lastName: String,
    var email: String,
    var company: Company?
)
