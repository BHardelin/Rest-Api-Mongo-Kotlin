package fr.baba.mongorestapi.repository

import fr.baba.mongorestapi.model.Company
import org.springframework.data.mongodb.repository.MongoRepository

interface CompanyRepository : MongoRepository<Company, String>