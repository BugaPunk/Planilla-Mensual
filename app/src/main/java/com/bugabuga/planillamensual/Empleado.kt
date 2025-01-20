package com.bugabuga.planillamensual

data class Empleado (
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val haberBasico: Double,
    val ventasHardware: Double,
    val ventasSoftware: Double
)