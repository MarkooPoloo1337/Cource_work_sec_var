package component


import kotlinx.serialization.Serializable

import react.dom.*
import react.fc
import react.useRef
import kotlin.js.Json
import kotlinext.js.jso
import kotlinx.browser.document
import kotlinx.html.INPUT
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.html.onChange
import lesson.lessonImportList1
import lesson.lessonImportList2
import lessonImport
import org.w3c.files.File
import react.Props
import react.dom.*
import react.dom.aria.AriaRole
import react.fc
import react.query.useMutation
import react.query.useQuery
import react.query.useQueryClient
import react.router.dom.Link
import react.useRef
import ru.altmanea.edu.server.model.Config.Companion.lessonsURL
import ru.altmanea.edu.server.model.Config.Companion.studentsURL
import ru.altmanea.edu.server.model.Item
import ru.altmanea.edu.server.model.Student
import ru.altmanea.edu.server.model.lesson
import wrappers.AxiosResponse
import wrappers.QueryError
import wrappers.axios
import kotlin.js.json



external interface lessonListProps : Props {

    var lessons: List<Item<lesson>>
    var addLesson: (String, String, String, String, String, String, String) -> Unit
    var deleteLesson: (Int) -> Unit
}

/*
fun fcInputFile() = fc("InputFile") { _ : lessonListProps ->

form {
   attrs.attributes += "enctype" to "multipart/form-data"
   attrs.attributes += "method" to "post"
}
   p {
       input {
           attrs.attributes += "id" to "fileImport"
           attrs.attributes += "type" to "file"
           attrs.attributes += "name" to "f"
       }
   }

input {
   attrs.attributes += "type" to "submit"
   attrs.attributes += "value" to "Отправить"
}


}
 */

fun fcLessonList(groupName: String) = fc("lessonList") { props: lessonListProps ->


    val idRef = useRef<INPUT>()
    val groupRef = useRef<INPUT>()
    val nameRef = useRef<INPUT>()
    val typeRef = useRef<INPUT>()
    val roomRef = useRef<INPUT>()
    val powerRef = useRef<INPUT>()
    val teacherRef = useRef<INPUT>()


    val dayWeek: List<String> = listOf("Пнд", "Втр", "Срд", "Чтв", "Птн", "Сбт")



    // +document.getElementById("file-selector")!!.getAttribute("id")!!
    // +document.getElementById("output")!!.getAttribute("id")!!

    /*

    script {
        +"const fileSelector = document.getElementById('file-selector');\n"
        +"fileSelector.addEventListener('change', (event) => {\n"
        +"const fileList = event.target.files;\n"
        +" console.log(fileList);\n"
        +"});\n"
    }



    script {

        +"for (const file of fileList) { \n"

        +"const name = file.name ? file.name : 'NOT SUPPORTED'; \n"
        +"const type = file.type ? file.type : 'NOT SUPPORTED'; \n"

        +"const size = file.size ? file.size : 'NOT SUPPORTED'; \n"
        +"console.log({file, name, type, size}); \n"
        +"}"

    }

     */


            if (document.getElementById("output")?.textContent == "lessonImportList1.kt") {
                lessonImport = lessonImportList1
            }

            /*
            else {
                document.body?.style?.color = "green"
                //lessonImport.drop(lessonImport.size)
                lessonImport = lessonImportList2
            }
             */

            if (document.getElementById("output")?.textContent == "lessonImportList2.kt") {
                lessonImport = lessonImportList2
            }






    table ("MainTable") {

        tr {

            th {
                attrs.attributes += "valign" to "top"
                tr {
                    th {
                        attrs.attributes += "colspan" to "2"
                        +"Выбор расписания"
                    }
                }

                tr {
                    th {
                        attrs.attributes += "rowspan" to "2"
                        +"Группа:"

                    }
                    th {
                        Link {
                            attrs.to = "/lessons/29z"
                            +"29з"
                        }
                    }
                }
                tr{
                    th {
                        Link {
                            attrs.to = "/lessons/29m"
                            +"29м"
                        }
                    }
                }
            }


            th{
                tr{
                    th{
                        attrs.attributes += "colspan" to "2"
                        attrs.attributes += "bgcolor" to "#bfbfbf"

                        h2{
                            +"Добавление занятий"
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        +"id: "
                    }
                    th{
                        input {
                            ref = idRef
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        +"Группа: "
                    }
                    th{
                        input {
                            ref = groupRef
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        +"Наменование занятия: "
                    }
                    th{
                        input {
                            ref = nameRef
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        +"Тип занятия: "
                    }
                    th{
                        input {
                            ref = typeRef
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        +"Аудитория: "
                    }
                    th{
                        input {
                            ref = roomRef
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        +"Должность преподователя: "
                    }
                    th{
                        input {
                            ref = powerRef
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "bgcolor" to "#bfbfbf"
                        +"Преподаватель: "
                    }
                    th{
                        input {
                            ref = teacherRef
                        }
                    }
                }
                tr{
                    th{
                        attrs.attributes += "colspan" to "2"
                        button {
                            +"Добавить урок"
                            attrs.onClickFunction = {
                                idRef.current?.value?.let { id ->
                                    groupRef.current?.value?.let { group ->
                                        nameRef.current?.value?.let { name ->
                                            typeRef.current?.value?.let { type ->
                                                roomRef.current?.value?.let { room ->
                                                    powerRef.current?.value?.let { power ->
                                                        teacherRef.current?.value?.let { teacher ->
                                                            props.addLesson(id, group, name, type, room, power, teacher)
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
        }

        tr {
            th {

                table("TableServer") {

                    style {
                        attrs.attributes += "type" to "text/css"
                        +"TABLE {\nbackground: white;\n color: black;\n}\n TD, TH {\n padding: 5px;\n border: 1px solid grey;\n width: 1100px;\nword-wrap:break-word;}\n}"
                    }
                    style {
                        attrs.attributes += "type" to "text/css"
                        +".TableServer tr:nth-child(1) {\n background: #5ae55a\n }"
                        for (i in 11..16) {
                            +".TableServer tr:nth-child($i) {\n color: blue\n }"
                        }
                        for (i in 5..16) {
                            +".TableServer tr:nth-child($i) {\n height: 195px\n }"
                        }
                    }

                    thead() {

                    }
                    tbody {

                        tr {
                            td {
                                attrs.attributes += "colspan" to "6"
                                +"Расписание на сервере"
                            }
                        }

                        tr {
                            td {
                                attrs.attributes += "colspan" to "6"
                                +"Расписание группы $groupName"
                            }
                        }
                        tr {
                            th { +"Пары" }
                            th { +"1-ая" }
                            th { +"2-ая" }
                            th { +"3-ая" }
                            th { +"4-ая" }
                            th { +"5-ая" }
                        }
                        tr {
                            th { +"Время" }
                            th { +"08.00 - 09.30" }
                            th { +"09.45 - 11.15" }
                            th { +"11.30 - 13.00" }
                            th { +"13.55 - 15.25" }
                            th { +"15.40 - 17.10" }
                        }


                        for (i in 0..5) {

                            tr {
                                td ("$i") { +dayWeek[i] }
                                for (j in 0..4){
                                    td ("$i$j") {

                                        props.lessons.mapIndexed { index, lessonItem ->

                                                val lesson = lesson(
                                                    lessonItem.elem.id,
                                                    lessonItem.elem.group,
                                                    lessonItem.elem.name,
                                                    lessonItem.elem.type,
                                                    lessonItem.elem.room,
                                                    lessonItem.elem.power,
                                                    lessonItem.elem.teacher)


                                                if (lesson.id == "$i$j" && lesson.group == groupName) {
                                                    +lesson.fullNamelesson


                                                    p {
                                                        Link {
                                                            attrs.to = "/lesson/${lessonItem.uuid}"
                                                            button {
                                                                +"Изм-ть"
                                                            }
                                                        }

                                                        button {
                                                            +"Удалить"
                                                            attrs.onClickFunction = {
                                                                props.deleteLesson(index)
                                                            }
                                                        }
                                                    }

                                                        var lessonsRepoTemp: lesson


                                                    lessonImport.forEachIndexed { index1, _ ->

                                                            if (lessonImport[index1].id.contains("$i$j")
                                                                && lessonImport[index1].group.contains(groupName)) {
                                                                lessonsRepoTemp = lessonImport[index1]
                                                                if (lessonsRepoTemp.fullNamelesson == lesson.fullNamelesson
                                                                    && lessonsRepoTemp.id == "$i$j"
                                                                ) attrs.attributes += "bgcolor" to "white"
                                                               else {
                                                                    attrs.attributes += "bgcolor" to "#f65656"
                                                                }
                                                            }
                                                        }
                                                }
                                        }
                                    }
                                }
                            }
                        }

                        for (i in 6..11) {


                            tr {
                                td ("$i") { +dayWeek[i-6] }
                                for (j in 0..4){
                                    td ("$i$j") {

                                        props.lessons.mapIndexed { index, lessonItem ->

                                            val lesson = lesson(
                                                lessonItem.elem.id,
                                                lessonItem.elem.group,
                                                lessonItem.elem.name,
                                                lessonItem.elem.type,
                                                lessonItem.elem.room,
                                                lessonItem.elem.power,
                                                lessonItem.elem.teacher)

                                            if (lesson.id == "$i$j" && lesson.group == groupName) {
                                                +lesson.fullNamelesson

                                                p {
                                                    Link {
                                                        attrs.to = "/lesson/${lessonItem.uuid}"
                                                        button {
                                                            +"Изм-ть"
                                                        }
                                                    }

                                                    button {
                                                        +"Удалить"
                                                        attrs.onClickFunction = {
                                                            props.deleteLesson(index)
                                                        }
                                                    }
                                                }

                                                var lessonsRepoTemp: lesson

                                                lessonImport.forEachIndexed { index1, _ ->

                                                    if (lessonImport[index1].id.contains("$i$j")
                                                        && lessonImport[index1].group.contains(groupName)) {
                                                        lessonsRepoTemp = lessonImport[index1]

                                                        if (lessonsRepoTemp.fullNamelesson == lesson.fullNamelesson
                                                            && lessonsRepoTemp.id == "$i$j"
                                                        ) attrs.attributes += "bgcolor" to "white"
                                                        else {
                                                            attrs.attributes += "bgcolor" to "#f65656"
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

            th {

                attrs.attributes += "valign" to "top"



                table("TableImport") {

                    style {
                        attrs.attributes += "type" to "text/css"
                        +"TABLE {background: white;\n color: black;\n}\n TD, TH {\n padding: 5px;\n border: 1px solid grey;\n width: 1000px;  \n word-wrap:break-word;}\n "
                    }
                    style {
                        attrs.attributes += "type" to "text/css"
                        +".TableImport tr:nth-child(1) {\n background: #bf7efb\n }"
                        for (i in 11..16) {
                            +".TableImport tr:nth-child($i) {\n color: blue\n }"
                        }
                        for (i in 5..16) {
                            +".TableImport tr:nth-child($i) {\n height: 195px\n }"
                        }
                    }

                    thead() {

                    }
                    tbody {

                        tr {
                            td {
                                attrs.attributes += "colspan" to "6"
                                +"Импортированное расписание"
                            }
                        }

                        tr {
                            td {
                                attrs.attributes += "colspan" to "6"
                                +"Расписание группы"
                            }
                        }
                        tr {
                            th { +"Пары" }
                            th { +"1-ая" }
                            th { +"2-ая" }
                            th { +"3-ая" }
                            th { +"4-ая" }
                            th { +"5-ая" }
                        }
                        tr {
                            th { +"Время" }
                            th { +"08.00 - 09.30" }
                            th { +"09.45 - 11.15" }
                            th { +"11.30 - 13.00" }
                            th { +"13.55 - 15.25" }
                            th { +"15.40 - 17.10" }
                        }


                        for (i in 0..5) {

                            tr {
                                td ("$i") { +dayWeek[i] }
                                for (j in 0..4){
                                    td ("$i$j") {
                                        lessonImport.forEachIndexed { index, _ ->
                                            //+"${ lessonsRepo[index].id.takeIf { it == "$i$j" } }"
                                            if (lessonImport[index].id == "$i$j" && lessonImport[index].group == groupName) {
                                                +lessonImport[index].fullNamelesson

                                            }
                                        }
                                    }
                                }
                            }
                        }

                        for (i in 6..11) {


                            tr {
                                td ("$i") { +dayWeek[i-6] }
                                for (j in 0..4){
                                    td ("$i$j") {
                                        lessonImport.forEachIndexed { index, _ ->
                                            //+"${ lessonsRepo[index].id.takeIf { it == "$i$j" } }"
                                            if (lessonImport[index].id == "$i$j" && lessonImport[index].group == groupName) {
                                                +lessonImport[index].fullNamelesson

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




fun fcContainerLessonList(groupName: String) = fc("QueryLessonList") { _: Props ->
    val queryClient = useQueryClient()
    val query = useQuery<Any, QueryError, AxiosResponse<Array<Item<lesson>>>, Any>(
        "lessonList",
        {
            axios<Array<lesson>>(jso {
                url = lessonsURL
            })
        }
    )

    val addLessonMutation = useMutation<Any, Any, Any, Any>(
        { Lesson: lesson ->
            axios<String>(jso {
                url = lessonsURL
                method = "Post"
                headers = json(
                    "Content-Type" to "application/json"
                )
                data = JSON.stringify(Lesson)
            })
        },
        options = jso {
            onSuccess = { _: Any, _: Any, _: Any? ->
                queryClient.invalidateQueries<Any>("lessonList")
            }
        }
    )

    val deleteLessonMutation = useMutation<Any, Any, Any, Any>(
        { lessonItem: Item<lesson> ->
            axios<String>(jso {
                url = "$lessonsURL/${lessonItem.uuid}"
                method = "Delete"
            })
        },
        options = jso {
            onSuccess = { _: Any, _: Any, _: Any? ->
                queryClient.invalidateQueries<Any>("lessonList")
            }
        }
    )

    if (query.isLoading) div { +"Loading .." }
    else if (query.isError) div { +"Error!" }
    else {
        val items = query.data?.data?.toList() ?: emptyList()

        child(fcLessonList(groupName)) {
            attrs.lessons = items
            attrs.addLesson = { i, g, n, t, r, p, te ->
                addLessonMutation.mutate(lesson(i, g, n, t, r, p, te), null)
            }
            attrs.deleteLesson = {
                deleteLessonMutation.mutate(items[it], null)
            }
        }
    }
}


