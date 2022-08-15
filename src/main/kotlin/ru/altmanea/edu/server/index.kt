package ru.altmanea.edu.server

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.index() {
    get("/") {
        call.respondHtml(HttpStatusCode.OK) {
            head {
                meta {
                    attributes += "charset" to "UTF-8"
                }
                title {
                    +"Web App Client"
                }


            }
            body {

                p {
                    input {
                        attributes += "id" to "file-selector"
                        attributes += "type" to "file"
                        // attributes += "multiple" to "multiple"
                        // attrs.attributes += "name" to "f"

                    }
                }

                p {
                    ul {
                        attributes += "id" to "output"
                    }
                }

                div {
                    attributes += "id" to "root"
                }

                script () {
                    unsafe {
                        +"const output = document.getElementById('output'); \n"
                        +"if (window.FileList && window.File) { \n"
                        +"document.getElementById('file-selector').addEventListener('change', event => { \n"
                        +"output.innerHTML = ''; \n"
                        +" for (const file of event.target.files) { \n"
                        +"const name = file.name ? file.name : 'NOT SUPPORTED'; \n"
                        +" output.textContent = `\${name}`; \n"
                        +"} \n"
                        +"}); \n"
                        +"} \n"
                    }
                }
                script ("text/javascript", "client.js") { }
            }
        }
    }
    static {
        resource("/client.js","client.js")
        resource("/client.js.map","client.js.map")
    }
}