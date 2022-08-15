package ru.altmanea.edu.server.model

import kotlinx.serialization.*

@Serializable
class lesson(
    var id: String,
    var group: String,
    var name: String,
    var type: String,
    var room: String,
    var power: String,
    var teacher: String
){
    val fullNamelesson: String
        get() = "$type $name $power $teacher ауд. $room"
}