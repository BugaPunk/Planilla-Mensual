package com.bugabuga.planillamensual

data class Empleado (
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val haberBasico: Double,
    val ventasHardware: Double,
    val ventasSoftware: Double
) {
    fun totalVentas(): Double {
        return ventasSoftware + ventasHardware
    }

    fun comisionSoftware(): Double {
        return ventasSoftware * 0.03
    }

    fun comisionHardware(): Double {
        return ventasHardware * 0.025
    }

    fun comisionPremio(): Double {
        return totalVentas() * 0.012
    }

    fun totalComision(): Double {
        return comisionSoftware() + comisionHardware() + comisionPremio()
    }

    fun liquidoPagable(): Double {
        return totalComision() + haberBasico
    }
}