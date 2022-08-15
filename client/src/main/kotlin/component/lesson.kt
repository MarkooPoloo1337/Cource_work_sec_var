package component

import kotlinext.js.jso
import kotlinx.html.INPUT
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import lesson.lessonImportList1
import lessonImport
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.query.useMutation
import react.query.useQuery
import react.query.useQueryClient
import react.router.useParams
import ru.altmanea.edu.server.model.Config
import ru.altmanea.edu.server.model.Item
import ru.altmanea.edu.server.model.Student
import ru.altmanea.edu.server.model.lesson
import wrappers.AxiosResponse
import wrappers.QueryError
import wrappers.axios
import kotlin.js.json

external interface LessonProps : Props {
    var lessons: Item<lesson>
    var updateLesson: (String, String, String, String, String, String, String) -> Unit
}


fun fcLesson() = fc("Lesson") { props: LessonProps ->

    val idRef = useRef<INPUT>()
    val groupRef = useRef<INPUT>()
    val nameRef = useRef<INPUT>()
    val typeRef = useRef<INPUT>()
    val roomRef = useRef<INPUT>()
    val powerRef = useRef<INPUT>()
    val teacherRef = useRef<INPUT>()

    val (id, setId) = useState(props.lessons.elem.id)
    val (group, setGroup) = useState(props.lessons.elem.group)
    val (name, setName) = useState(props.lessons.elem.name)
    val (type, setType) = useState(props.lessons.elem.type)
    val (room, setRoom) = useState(props.lessons.elem.room)
    val (power, setPower) = useState(props.lessons.elem.power)
    val (teacher, setTeacher) = useState(props.lessons.elem.teacher)


    fun onInputEdit(setter: StateSetter<String>, ref: MutableRefObject<INPUT>) =
        { _: Event ->
            setter(ref.current?.value ?: "ERROR!")
        }


    table ("TableUpdate") {

        style {
            attrs.attributes += "type" to "text/css"
            +"TABLE {\nbackground: white;\n color: black;\n}\n TD, TH {\n padding: 5px;\n border: 1px solid grey;\n width: 400px;\nword-wrap:break-word;}\n}"
        }

        tbody {

            th {
                attrs.attributes += "valign" to "top"
                tr{
                    td{
                        attrs.attributes += "colspan" to "2"
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        h2{
                            +"Изменение занятия"
                        }
                    }
                }
                tr {

                    td {
                        +"id: "
                    }

                    td {
                        input {
                            ref = idRef
                            attrs.value = id
                            attrs.onChangeFunction = onInputEdit(setId, idRef)
                        }
                    }
                }

                tr {

                    td {
                        +"Группа: "
                    }
                    td {
                        input {
                            ref = groupRef
                            attrs.value = group
                            attrs.onChangeFunction = onInputEdit(setGroup, groupRef)
                        }
                    }
                }

                tr {

                    td {
                        +"Наименование занятия:"
                    }
                    td {
                        input {
                            ref = nameRef
                            attrs.value = name
                            attrs.onChangeFunction = onInputEdit(setName, nameRef)
                        }
                    }
                }

                tr {

                    td {
                        +"Тип занятия: "
                    }
                    td {
                        input {
                            ref = typeRef
                            attrs.value = type
                            attrs.onChangeFunction = onInputEdit(setType, typeRef)
                        }
                    }
                }

                tr {

                 td {
                     +"Аудитория: "
                 }
                    td {
                        input {
                            ref = roomRef
                            attrs.value = room
                            attrs.onChangeFunction = onInputEdit(setRoom, roomRef)
                        }
                    }
                }

                tr {

                 td {
                     +"Должность преподавателя: "
                 }
                    td {
                        input {
                            ref = powerRef
                            attrs.value = power
                            attrs.onChangeFunction = onInputEdit(setPower, powerRef)
                        }
                    }
                }

                tr {

                 td {
                     +"Преподаватель: "
                 }
                    td {
                        input {
                            ref = teacherRef
                            attrs.value = teacher
                            attrs.onChangeFunction = onInputEdit(setTeacher, teacherRef)
                        }
                    }
                }

                tr {
                     td{
                         attrs.attributes += "colspan" to "2"
                            button {
                                +"Обновить урок"
                                attrs.onClickFunction = {
                                    idRef.current?.value?.let { id ->
                                        groupRef.current?.value?.let { group ->
                                            nameRef.current?.value?.let { name ->
                                                typeRef.current?.value?.let { type ->
                                                    roomRef.current?.value?.let { room ->
                                                        powerRef.current?.value?.let { power ->
                                                            teacherRef.current?.value?.let { teacher ->
                                                                props.updateLesson(id, group, name, type, room, power, teacher)
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                     }
                }
            }

            th{
                attrs.attributes += "valign" to "top"

                tr{
                    td{
                        attrs.attributes += "colspan" to "2"
                        attrs.attributes += "bgcolor" to "#bf7efb"
                        h2{
                            +"Импортированное занятие"
                        }
                    }
                }


                lessonImport.forEachIndexed { index, _ ->

                        if (props.lessons.elem.id == lessonImport[index].id
                            && props.lessons.elem.group == lessonImport[index].group
                        ) {

                            tr {
                                td{
                                    +"id:"
                                }
                                td{
                                    +lessonImport[index].id
                                }
                            }
                            tr {
                                td{
                                    +"Группа: "
                                }
                                td{
                                    +lessonImport[index].group
                                }
                            }
                            tr {
                                td{
                                    +"Наименование занятия: "
                                }
                                td{
                                    +lessonImport[index].name
                                    if (props.lessons.elem.name != lessonImport[index].name) {
                                        attrs.attributes += "bgcolor" to "#f65656"
                                    } else attrs.attributes += "bgcolor" to "white"
                                }
                            }
                            tr {
                                td{
                                    +"Тип занятия: "
                                }
                                td{
                                    +lessonImport[index].type
                                    if (props.lessons.elem.type != lessonImport[index].type) {
                                        attrs.attributes += "bgcolor" to "#f65656"
                                    } else attrs.attributes += "bgcolor" to "white"
                                }
                            }
                            tr {
                                td{
                                    +"Аудитория: "
                                }
                                td{
                                    +lessonImport[index].room
                                    if (props.lessons.elem.room != lessonImport[index].room) {
                                        attrs.attributes += "bgcolor" to "#f65656"
                                    } else attrs.attributes += "bgcolor" to "white"
                                }
                            }
                            tr {
                                td{
                                    +"Должность преподавателя: "
                                }
                                td{
                                    +lessonImport[index].power
                                    if (props.lessons.elem.power != lessonImport[index].power) {
                                        attrs.attributes += "bgcolor" to "#f65656"
                                    } else attrs.attributes += "bgcolor" to "white"
                                }
                            }
                            tr {
                                td{
                                    +"Преподаватель: "
                                }
                                td{
                                    +lessonImport[index].teacher
                                    if (props.lessons.elem.teacher != lessonImport[index].teacher) {
                                        attrs.attributes += "bgcolor" to "#f65656"
                                    } else attrs.attributes += "bgcolor" to "white"
                                }
                            }

                        }
                    }

            }
        }


    }
}

class MutationData(
    val oldLesson: Item<lesson>,
    val newLesson: lesson,
)

fun fcContainerLesson() = fc("ContainerLesson") { _: Props ->
    val lessonParams = useParams()
    val queryClient = useQueryClient()

    val lessonId = lessonParams["id"] ?: "Route param error"

    val query = useQuery<Any, QueryError, AxiosResponse<Item<lesson>>, Any>(
        lessonId,
        {
            axios<Array<lesson>>(jso {
                url = Config.lessonsPath + lessonId
            })
        }
    )

    val updateLessonMutation = useMutation<Any, Any, MutationData, Any>(
        { mutationData ->
            axios<String>(jso {
                url = "${Config.lessonsURL}/${mutationData.oldLesson.uuid}"
                method = "Put"
                headers = json(
                    "Content-Type" to "application/json",
                )
                data = JSON.stringify(mutationData.newLesson)
            })
        },
        options = jso {
            onSuccess = { _: Any, _: Any, _: Any? ->
                queryClient.invalidateQueries<Any>(lessonId)
            }
        }
    )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val lessonItem = query.data?.data!!
        child(fcLesson()) {
            attrs.lessons = lessonItem
            attrs.updateLesson = { i, g, n, t, r, p, te ->
                updateLessonMutation.mutate(MutationData(lessonItem, lesson(i, g, n, t, r, p, te)), null)
            }
        }
    }
}
