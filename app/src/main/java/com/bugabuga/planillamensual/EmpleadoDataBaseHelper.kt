package com.bugabuga.planillamensual

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EmpleadoDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "planilla.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "empleados"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NOMBRES = "nombres"
        private const val COLUMN_APELLIDOS = "apellidos"
        private const val COLUMN_HABER_BASICO = "haber_basico"
        private const val COLUMN_VENTAS_SOFTWARE = "ventas_software"
        private const val COLUMN_VENTAS_HARDWARE = "ventas_hardware"
        private const val COLUNN_TOTAL_VENTAS = "total_ventas"
        private const val COLUMN_COMISION_SOFTWARE = "comision_software"
        private const val COLUMN_COMISION_HARDWARE = "comision_hardware"
        private const val COLUMN_COMISION_PREMIO = "comision_premio"
        private const val COLUMN_TOTAL_COMISION = "total_comision"
        private const val COLUMN_LIQUIDO_PAGABLE = "liquido_pagable"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOMBRES TEXT, $COLUMN_APELLIDOS TEXT, " +
                "$COLUMN_HABER_BASICO REAL, " +
                "$COLUMN_VENTAS_SOFTWARE REAL, " +
                "$COLUMN_VENTAS_HARDWARE REAL, " +
                "$COLUNN_TOTAL_VENTAS REAL, " +
                "$COLUMN_COMISION_SOFTWARE REAL, " +
                "$COLUMN_COMISION_HARDWARE REAL, " +
                "$COLUMN_COMISION_PREMIO REAL, " +
                "$COLUMN_TOTAL_COMISION REAL, " +
                "$COLUMN_LIQUIDO_PAGABLE REAL)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertEmpleado(
        empleado: Empleado,
        totalVentas: Double,
        comisionSoftware: Double,
        comisionHardware: Double,
        comisionPremio: Double,
        totalComision: Double,
        liquidoPagable: Double
    ) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRES, empleado.nombres)
            put(COLUMN_APELLIDOS, empleado.apellidos)
            put(COLUMN_HABER_BASICO, empleado.haberBasico)
            put(COLUMN_VENTAS_SOFTWARE, empleado.ventasSoftware)
            put(COLUMN_VENTAS_HARDWARE, empleado.ventasHardware)
            put(COLUNN_TOTAL_VENTAS, totalVentas)
            put(COLUMN_COMISION_SOFTWARE, comisionSoftware)
            put(COLUMN_COMISION_HARDWARE, comisionHardware)
            put(COLUMN_COMISION_PREMIO, comisionPremio)
            put(COLUMN_TOTAL_COMISION, totalComision)
            put(COLUMN_LIQUIDO_PAGABLE, liquidoPagable)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllEmpleados(): List<Empleado> {
        val empleados = mutableListOf<Empleado>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val nombres = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRES))
            val apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APELLIDOS))
            val haberBasico = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_HABER_BASICO))
            val ventasSoftware = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VENTAS_SOFTWARE))
            val ventasHardware = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VENTAS_HARDWARE))

            val empleado = Empleado(id, nombres, apellidos, haberBasico, ventasSoftware, ventasHardware)
            empleados.add(empleado)
        }
        cursor.close()
        db.close()
        return empleados
    }

    fun updateEmpleado(
        empleado: Empleado,
        totalVentas: Double,
        comisionSoftware: Double,
        comisionHardware: Double,
        comisionPremio: Double,
        totalComision: Double,
        liquidoPagable: Double
    ) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRES, empleado.nombres)
            put(COLUMN_APELLIDOS, empleado.apellidos)
            put(COLUMN_HABER_BASICO, empleado.haberBasico)
            put(COLUMN_VENTAS_SOFTWARE, empleado.ventasSoftware)
            put(COLUMN_VENTAS_HARDWARE, empleado.ventasHardware)
            put(COLUNN_TOTAL_VENTAS, totalVentas)
            put(COLUMN_COMISION_SOFTWARE, comisionSoftware)
            put(COLUMN_COMISION_HARDWARE, comisionHardware)
            put(COLUMN_COMISION_PREMIO, comisionPremio)
            put(COLUMN_TOTAL_COMISION, totalComision)
            put(COLUMN_LIQUIDO_PAGABLE, liquidoPagable)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(empleado.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getEmpleadoById(empleadoId: Int): Empleado? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $empleadoId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val nombres = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRES))
        val apellidos = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APELLIDOS))
        val haberBasico = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_HABER_BASICO))
        val ventasSoftware = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VENTAS_SOFTWARE))
        val ventasHardware = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VENTAS_HARDWARE))

        cursor.close()
        db.close()
        return Empleado(id, nombres, apellidos, haberBasico, ventasSoftware, ventasHardware)
    }

    fun deleteEmpleado(empleadoId: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(empleadoId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }

}