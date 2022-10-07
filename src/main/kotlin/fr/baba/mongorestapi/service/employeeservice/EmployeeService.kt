package fr.baba.mongorestapi.service.employeeservice

import fr.baba.mongorestapi.model.Employee
import fr.baba.mongorestapi.repository.EmployeeRepository
import fr.baba.mongorestapi.service.companyservice.CompanyService
import fr.baba.mongorestapi.service.employeerequest.EmployeeRequest
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

}