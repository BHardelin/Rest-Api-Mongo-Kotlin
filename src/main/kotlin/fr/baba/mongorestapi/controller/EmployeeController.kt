package fr.baba.mongorestapi.controller

import fr.baba.mongorestapi.response.EmployeeResponse
import fr.baba.mongorestapi.service.request.EmployeeRequest
import fr.baba.mongorestapi.service.servcie.EmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/employee")
class EmployeeController(
    private val employeeService: EmployeeService
) {
    @PostMapping
    fun createEmployee(@RequestBody request: EmployeeRequest): ResponseEntity<EmployeeResponse> {
        val createEmployee = employeeService.createEmployee(request)

        return ResponseEntity
            .ok(
                EmployeeResponse.fromEntity(createEmployee)
            )
    }

    @GetMapping
    fun findAllEmployees(): ResponseEntity<List<EmployeeResponse>> {
        val employees = employeeService.findAll()

        return ResponseEntity
            .ok(
                employees.map { EmployeeResponse.fromEntity(it) }
            )
    }
}