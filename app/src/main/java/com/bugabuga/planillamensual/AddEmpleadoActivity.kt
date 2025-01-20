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
            db.insertEmpleado(empleado)
            finish()
            Toast.makeText(this, "Empleado guardado", Toast.LENGTH_SHORT).show()
        }

    }
}