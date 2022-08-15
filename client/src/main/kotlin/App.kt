import component.fcContainerLessonList
import component.fcContainerLesson
import component.fcLessonList
import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import lesson.lessonImportList1
import react.createElement
import react.dom.*
import react.dom.aria.AriaRole
import react.query.QueryClient
import react.query.QueryClientProvider
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link
import react.useRef
import react.useState
import ru.altmanea.edu.server.model.lesson
import wrappers.cReactQueryDevtools


val queryClient = QueryClient()

var lessonImport: List<lesson> = listOf()


fun main() {
    render(document.getElementById("root")!!) {

        /*
        input {
            attrs.attributes += "id" to "file-selector"
            attrs.attributes += "type" to "file"
            attrs.attributes += "multiple" to "multiple"
            // attrs.attributes += "name" to "f"
            // attrs.attributes += "value" to "https://media.discordapp.net/attachments/758014856346468542/764542649956433930/ff9637f385049fbaa31a60d1f6b6cbb7cd71901d_s2_n1.png"
        }

        ul {
            attrs.attributes += "id" to "output"
        }

         */




        HashRouter {
            QueryClientProvider {
                attrs.client = queryClient

                Link {
                    attrs.to = "/lessons/29z"
                    +"Lessons"
                }

                Routes {
                    Route {
                        attrs.index = true
                        attrs.path = "/lessons/29z"
                        attrs.element =
                            createElement(fcContainerLessonList("29з"))
                    }

                    Route {
                        attrs.path = "/lessons/29m"
                        attrs.element =
                            createElement(fcContainerLessonList("29м"))
                    }
                    Route {
                        attrs.path = "/lesson/:id"
                        attrs.element =
                            createElement(fcContainerLesson())
                    }

                }
                child(cReactQueryDevtools()) {}
            }
        }

    }


/*
        p {
            form {
                attrs.attributes += "name" to "foo"
                attrs.attributes += "method" to "post"
                attrs.attributes += "enctype" to "multipart/form-data"

                input {
                    attrs.attributes += "type" to "file"
                    attrs.attributes += "name" to "image"
                    // attrs.attributes += "onchange" to "updateFilename(this.value)"

                }
            }
        }

        p {
            +"The name of the file you picked is: "
            span {
                attrs.attributes += "id" to "filename"
                +"(none)"

            }
        }

 */


}

