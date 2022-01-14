package com.example.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.example.pruebatecnica.utils.Constants
import com.example.recordingapp.ApplicationData
import com.example.recordingapp.repository.Volley.Volley
import com.example.recordingapp.repository.Volley.VolleyCallBack
import com.example.task.Dagger.DaggerTaskComponent
import com.example.task.Dagger.TaskModule
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import org.json.JSONObject
import javax.inject.Inject

class TasksActivity : AppCompatActivity(), OnClickListener, VolleyCallBack {

    private lateinit var tvName: TextView
    private lateinit var tvEntregas: TextView
    private lateinit var tvRecogidas: TextView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var linerLayoutManager: RecyclerView.LayoutManager
    private lateinit var btnBack: FloatingActionButton

    @Inject
    lateinit var volley: Volley

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        DaggerTaskComponent.builder().applicationComponent((this.application as ApplicationData).getApplicationComponent()).taskModule(
            TaskModule(this)
        ).build().inject(this)

        initVars()

        tvName.text = tvName.text.toString() + intent.extras!!.getString("user").toString()
        findViewById<LinearLayout>(R.id.progress).visibility = View.VISIBLE
        volley.executeRequestJson(Request.Method.POST,JSONObject(),Constants.TASK_API_URL,this)

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initVars() {
        tvName = findViewById(R.id.tvName)
        tvEntregas = findViewById(R.id.tvEntregas)
        tvRecogidas = findViewById(R.id.tvRecogidas)
        btnBack = findViewById(R.id.btnBack)
    }

    override fun onClick(task: TaskModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("guia",task.guia)
        intent.putExtra("recogida",task.recogida)
        intent.putExtra("receiver",task.destinatario)
        intent.putExtra("address",task.direccion)
        intent.putExtra("city",task.ciudad)
        intent.putExtra("phone",task.celular)
        intent.putExtra("claimant",task.solicitante)
        intent.putExtra("lat",task.posicion.latitud)
        intent.putExtra("long",task.posicion.longitud)
        startActivity(intent)
    }

    private fun sendListTask(taskList: MutableList<TaskModel>) {
        taskAdapter = TaskAdapter(taskList, this)
        linerLayoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = linerLayoutManager
            adapter = taskAdapter
        }

    }

    override fun getResponse(response: String?) {
        var taskList = mutableListOf<TaskModel>()

        Log.i("Respuesta",response.toString())

        val resp = JSONObject(response)

        val deliverys = resp.getJSONObject("response").getJSONArray("Entregas")
        val collecteds = resp.getJSONObject("response").getJSONArray("Recogidas")

        for (i in 0 until deliverys.length()){
            val jsonObject = Gson().fromJson<TaskModel>(deliverys.get(i).toString(), TaskModel::class.java)
            taskList.add(jsonObject)
        }
        for (i in 0 until collecteds.length()){
            val jsonObject = Gson().fromJson<TaskModel>(collecteds.get(i).toString(), TaskModel::class.java)
            taskList.add(jsonObject)
        }
        tvEntregas.text = deliverys.length().toString()
        tvRecogidas.text = collecteds.length().toString()
        findViewById<LinearLayout>(R.id.progress).visibility = View.GONE
        sendListTask(taskList)
    }

    override fun getErrorVolley(volleyError: com.android.volley.VolleyError?) {
        Log.i("volley",volleyError!!.message.toString())
    }

}