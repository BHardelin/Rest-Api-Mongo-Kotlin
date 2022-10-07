package fr.baba.mongorestapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongorestapiApplication

fun main(args: Array<String>) {
	runApplication<MongorestapiApplication>(*args)
}
