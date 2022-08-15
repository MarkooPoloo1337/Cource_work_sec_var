package ru.altmanea.edu.server

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.altmanea.edu.server.model.Config
import ru.altmanea.edu.server.repo.lessonsRepo
import ru.altmanea.edu.server.repo.lessonsRepoTestData
import ru.altmanea.edu.server.rest.lesson


fun main() {
    embeddedServer(
        Netty,
        port = Config.serverPort,
        host = Config.serverDomain,
        watchPaths = listOf("classes", "resources")
    ) {
        main()
    }.start(wait = true)
}

fun Application.main(test: Boolean = true) {
    if(test) {
        lessonsRepoTestData.forEach { lessonsRepo.create(it) }
    }
    install(ContentNegotiation) {
        json()
    }
    routing {
        lesson()
        index()
    }
}