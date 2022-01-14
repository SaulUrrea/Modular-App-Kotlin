package com.example.task

data class TaskModel(
    val celular: String,
    val ciudad: String,
    val destinatario: String? = null,
    val direccion: String,
    val guia: String? = null,
    val recogida: String? = null,
    val solicitante: String? = null,
    val posicion: Posicion,
    val unidades: String
)

data class Posicion(
    val latitud: Double,
    val longitud: Double
)
