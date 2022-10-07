package fr.baba.mongorestapi.repository

import fr.baba.mongorestapi.model.Company
import fr.baba.mongorestapi.model.Employee
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface EmployeeRepository : MongoRepository<Employee, ObjectId> {

    fun findByCompanyId(id: String): List<Company>
}