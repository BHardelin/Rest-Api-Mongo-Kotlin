package fr.baba.mongorestapi.service.servcie

import fr.baba.mongorestapi.exception.NotFoundException
import fr.baba.mongorestapi.model.Company
import fr.baba.mongorestapi.model.Employee
import fr.baba.mongorestapi.repository.EmployeeRepository
import fr.baba.mongorestapi.service.request.EmployeeRequest
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val companyService: CompanyService,
    private val employeeRepository: EmployeeRepository
) {

    fun createEmployee(request: EmployeeRequest): Employee {
        val company = request.companyId?.let { companyService.findById(it) }

        return employeeRepository.save(
            Employee(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                company = company
            )
        )
    }

    fun findAll(): List<Employee> {
        return employeeRepository.findAll()
    }

    fun findAllByCompanyId(id: String): List<Company> {
        return employeeRepository.findByCompanyId(id)
    }

    fun findById(id: ObjectId): Employee {
        return employeeRepository.findById(id)
            .orElseThrow{ NotFoundException("Employee with id $id not found") }
    }

    fun updateEmployee(id: ObjectId, request: EmployeeRequest): Employee {
        val employeeToUpdate: Employee = findById(id)
        val foundCompany: Company? = request.companyId?.let { companyService.findById(it) }

        return employeeRepository.save(
            employeeToUpdate.apply {
                firstName = request.firstName
                lastName = request.lastName
                email = request.email
                company = foundCompany
            }
        )
    }

    fun deleteById(id: ObjectId) {
        val employeeToDelete = findById(id)

        employeeRepository.delete(employeeToDelete)
    }

}