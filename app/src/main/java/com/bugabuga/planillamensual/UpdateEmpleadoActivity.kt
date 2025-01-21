package com.bugabuga.planillamensual

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bugabuga.planillamensual.databinding.ActivityUpdateEmpleadoBinding

class UpdateEmpleadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateEmpleadoBinding
    private lateinit var db: EmpleadoDataBaseHelper
    private var empleadoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = EmpleadoDataBaseHelper(this)

        empleadoId = intent.getIntExtra("empleado_id", -1)
        if (empleadoId == -1) {
            finish()
            return
        }

        val empleado = db.getEmpleadoById(empleadoId)

        binding.txtUpdateNombres.setText(empleado?.nombres)
        binding.txtUpdateApellidos.setText(empleado?.apellidos)
        binding.txtUpdateHaberB.setText(empleado?.haberBasico.toString())
        binding.txtUpdateVentaS.setText(empleado?.ventasSoftware.toString())
        binding.txtUpdateVentaH.setText(empleado?.ventasHardware.toString())

        binding.btnUpdate.setOnClickListener {
            val newNombre = binding.txtUpdateNombres.text.toString()
            val newApellido = binding.txtUpdateApellidos.text.toString()
            val newHaberB = binding.txtUpdateHaberB.text.toString().toDouble()
            val newVentaS = binding.txtUpdateVentaS.text.toString().toDouble()
            val newVentaH = binding.txtUpdateVentaH.text.toString().toDouble()

            val updatedEmpleado = Empleado(empleadoId, newNombre, newApellido, newHaberB, newVentaS, newVentaH)

            db.updateEmpleado(
                updatedEmpleado,
                updatedEmpleado.totalVentas(),
                updatedEmpleado.comisionSoftware(),
                updatedEmpleado.comisionHardware(),
                updatedEmpleado.comisionPremio(),
                updatedEmpleado.totalComision(),
                updatedEmpleado.liquidoPagable()
            )
            finish()
            Toast.makeText(this, "Cambios Guardados", Toast.LENGTH_SHORT).show()
        }
    }
}