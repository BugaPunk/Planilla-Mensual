package com.bugabuga.planillamensual

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bugabuga.planillamensual.databinding.ActivityAddEmpleadoBinding

class AddEmpleadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEmpleadoBinding
    private lateinit var db: EmpleadoDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = EmpleadoDataBaseHelper(this)
        binding.saveButton.setOnClickListener {
            val empleado = Empleado(
                0,
                binding.txtInputNombres.text.toString(),
                binding.txtInputApellidos.text.toString(),
                binding.txtInputHaberB.text.toString().toDouble(),
                binding.txtInputVSoftware.text.toString().toDouble(),
                binding.txtInputVHardware.text.toString().toDouble()
            )

            /*val totalVentas = empleado.totalVentas()
            val comisionSoftware = empleado.comisionSoftware()
            val comisionHardware = empleado.comisionHardware()
            val comisionPremio = empleado.comisionPremio()
            val totalComision = empleado.totalComision()
            val liquidoPagable = empleado.liquidoPagable()*/

            db.insertEmpleado(
                empleado,
                empleado.totalVentas(),
                empleado.comisionSoftware(),
                empleado.comisionHardware(),
                empleado.comisionPremio(),
                empleado.totalComision(),
                empleado.liquidoPagable()
            )

            finish()
            Toast.makeText(this, "Empleado guardado", Toast.LENGTH_SHORT).show()
        }

    }
}