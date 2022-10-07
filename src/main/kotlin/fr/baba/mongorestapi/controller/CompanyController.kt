package fr.baba.mongorestapi.controller

import fr.baba.mongorestapi.response.CompanyResponse
import fr.baba.mongorestapi.service.request.CompanyRequest
import fr.baba.mongorestapi.service.servcie.CompanyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/company")
class CompantController(
    private val companyService: CompanyService
) {

    @PostMapping
    fun createCompany(@RequestBody request: CompanyRequest): ResponseEntity<CompanyResponse> {
        val createdCompany = companyService.createCompany(request)

        return ResponseEntity.ok(createdCompany?.let { CompanyResponse.fromEntity(it) })
    }

    @GetMapping
    fun findAllCompanies(): ResponseEntity<List<CompanyResponse>> {
        val companies = companyService.findAll()

        return ResponseEntity.ok(companies.map { CompanyResponse.fromEntity(it) })
    }

    @GetMapping("/{id}")
    fun findCompanyById(@PathVariable id: String): ResponseEntity<CompanyResponse> {
        val company = companyService.findById(id)

        return ResponseEntity
            .ok(
                CompanyResponse.fromEntity(company)
            )
    }
}