package fr.baba.mongorestapi.service

import fr.baba.mongorestapi.exception.NotFoundException
import fr.baba.mongorestapi.model.Company
import fr.baba.mongorestapi.repository.CompanyRepository
import fr.baba.mongorestapi.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(

    private val companyRepository: CompanyRepository,
    private val employeeRepository: EmployeeRepository
) {

    fun createCompany(request: CompanyRequest): Company? {
        if (request.name != null && request.address != null) {
            companyRepository.save(
                return Company(
                    name = request.name,
                    address = request.address
                )
            )
        }
        return null
    }

    fun findAll(): List<Company> {
        return companyRepository.findAll();
    }

    fun findById(id: String): Company {
        return companyRepository.findById(id).orElseThrow{NotFoundException("Company with id: $id not found")}
    }

    fun updateCompany(id: String, request: CompanyRequest): Company? {
        val companyToUpdate = findById(id)
        var updateCompany: Company? = null
        if(request.name != null && request.address != null) {
            updateCompany = companyRepository.save(
                companyToUpdate.apply {
                    name = request.name
                    address = request.address
                }
            )
        }

        if (updateCompany != null) {
            updateCompanyEmployees(updateCompany)
        }

        return updateCompany
    }

    fun deleteById(id: String) {
        val companyToDelete = findById(id)

         companyRepository.delete(companyToDelete)
    }

    private fun updateCompanyEmployees(updatedCompany: Company) {
        employeeRepository.saveAll(
            employeeRepository.findByCompanyId(updatedCompany.id!!)
                .map {
                    it.apply {
                        updatedCompany
                    }
                }
        )
    }

}