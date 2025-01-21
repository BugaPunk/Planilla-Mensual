package com.bugabuga.planillamensual

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EmpleadosAdapter(
    private var empleados: List<Empleado>,
    context: Context
): RecyclerView.Adapter<EmpleadosAdapter.EmpleadoViewHolder>() {

    private val db: EmpleadoDataBaseHelper = EmpleadoDataBaseHelper(context)

    class EmpleadoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtNombresYApellidos: TextView = itemView.findViewById(R.id.txtNombresYApellidos)
        val txtHaberB: TextView = itemView.findViewById(R.id.txtHaberB)
        val txtVSoftware: TextView = itemView.findViewById(R.id.txtVSoftware)
        val txtVHardware: TextView = itemView.findViewById(R.id.txtVHardware)
        val txtLPagable: TextView = itemView.findViewById(R.id.txtLPagable)

        // actualizar - editar - update
        val btnUpdate: ImageView = itemView.findViewById(R.id.btnUpdate)
        val  btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmpleadoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.empleado_item, parent, false)
        return EmpleadoViewHolder(view)
    }

    override fun getItemCount(): Int = empleados.size

    override fun onBindViewHolder(
        holder: EmpleadoViewHolder,
        position: Int
    ) {
        val empleado = empleados[position]
        holder.txtNombresYApellidos.text = "${empleado.nombres} ${empleado.apellidos}"
        holder.txtHaberB.text = "${empleado.haberBasico}"
        holder.txtVSoftware.text = "${empleado.ventasSoftware}"
        holder.txtVHardware.text = "${empleado.ventasHardware}"
        holder.txtLPagable.text = "${empleado.liquidoPagable()}"

        holder.btnUpdate.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateEmpleadoActivity::class.java).apply {
                putExtra("empleado_id", empleado.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {
            db.deleteEmpleado(empleado.id)
            refreshData(db.getAllEmpleados())
            Toast.makeText(holder.itemView.context, "Empleado eliminado", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(newEmpleados: List<Empleado>) {
        empleados = newEmpleados
        notifyDataSetChanged()
    }

}