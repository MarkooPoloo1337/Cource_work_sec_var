package ru.altmanea.edu.server.rest

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.altmanea.edu.server.model.Config.Companion.lessonsPath
import ru.altmanea.edu.server.model.lesson
import ru.altmanea.edu.server.repo.lessonsRepo


fun Route.lesson() =
    route(lessonsPath) {
        get {
            if (!lessonsRepo.isEmpty()) {
                call.respond(lessonsRepo.findAll())
            } else {
                call.respondText("No lessons found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val lessonItem =
                lessonsRepo[id] ?: return@get call.respondText(
                    "No student with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.response.etag(lessonItem.etag.toString())
            call.respond(lessonItem)
        }
        post {
            val lesson = call.receive<lesson>()
            lessonsRepo.create(lesson)
            call.respondText("Lesson stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (lessonsRepo.delete(id)) {
                call.respondText("Lesson removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
        put("{id}") {
            val id = call.parameters["id"] ?: return@put call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            lessonsRepo[id] ?: return@put call.respondText(
                "No lesson with id $id",
                status = HttpStatusCode.NotFound
            )
            val newLesson = call.receive<lesson>()
            lessonsRepo.update(id, newLesson)
            call.respondText("Lesson updates correctly", status = HttpStatusCode.Created)
        }
    }
