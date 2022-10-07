package fr.baba.mongorestapi.service.employeerequest

class EmployeeRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val companyId: String?
)