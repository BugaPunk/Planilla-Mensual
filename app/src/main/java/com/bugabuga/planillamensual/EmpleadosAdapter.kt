package com.bugabuga.planillamensual

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmpleadosAdapter(
    private var empleados: List<Empleado>,
    context: Context
): RecyclerView.Adapter<EmpleadosAdapter.EmpleadoViewHolder>() {

    class EmpleadoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtNombresYApellidos: TextView = itemView.findViewById(R.id.txtNombresYApellidos)
        val txtHaberB: TextView = itemView.findViewById(R.id.txtHaberB)
        val txtVSoftware: TextView = itemView.findViewById(R.id.txtVSoftware)
        val txtVHardware: TextView = itemView.findViewById(R.id.txtVHardware)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmpleadoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.empleado_item, parent, false)
        return EmpleadoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: EmpleadoViewHolder,
        position: Int
    ) {
        val empleado = empleados[position]
        holder.txtNombresYApellidos.text = "${empleado.nombres} ${empleado.apellidos}"
        holder.txtHaberB.text = "${empleado.haberBasico}"
        holder.txtVSoftware.text = "${empleado.ventasSoftware}"
        holder.txtVHardware.text = "${empleado.ventasHardware}"
    }

    override fun getItemCount(): Int = empleados.size

    fun refreshData(newEmpleados: List<Empleado>) {
        empleados = newEmpleados
        notifyDataSetChanged()
    }

}