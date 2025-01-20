package com.bugabuga.planillamensual

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bugabuga.planillamensual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: EmpleadoDataBaseHelper
    private lateinit var empleadosAdapter: EmpleadosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = EmpleadoDataBaseHelper(this)
        empleadosAdapter = EmpleadosAdapter(db.getAllEmpleados(), this)

        binding.empleadosRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.empleadosRecyclerView.adapter = empleadosAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddEmpleadoActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        empleadosAdapter.refreshData(db.getAllEmpleados())
    }
}