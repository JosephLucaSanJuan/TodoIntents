package com.alanturing.cpifp.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val viewModel:TaskViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        when (it.resultCode) {
            Activity.RESULT_OK -> {
                binding.tasks.adapter = TasksAdapter(TaskLocalRepository.getInstance().tasks,::onShareItem)
            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(this,"Se ha cancelado",Toast.LENGTH_SHORT)
                //Snackbar.make(this,binding.root,"Se ha cancelado",Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO("Recuperar el RecyclerView y asignar el adaptador")
        setContentView(R.layout.activity_main)
        binding.tasks.adapter = TasksAdapter(TaskLocalRepository.getInstance().tasks,::onShareItem)
        binding.tasks.adapter = TasksAdapter(viewModel.data.value!!,::onShareItem,::onEditItem)

        binding.createTodo.setOnClickListener{
            val createIntent = Intent(this, CreateToDoActivity::class.java)
            //startActivity(createIntent)
            getResult.launch(createIntent)
        }
        //TODO("Crear vista de detalles")
        //TODO("Crear vista de formulario de creacón")
        //TODO("Crear manejador de evento para navegar al formulario de crear")
    }

    override fun onResume() {
        super.onResume()
        binding.tasks.adapter = TasksAdapter(TaskLocalRepository.getInstance().tasks,::onShareItem)
    }

    fun onEditItem(){
        val updateIntent = Intent(this, EditItemActivity::class.java)
        updateIntent.putExtra("com.alanturingcpifp.TASK",task)
        //getResutl.launch(updateIntent)
        startActivity(updateIntent)
    }

    fun onShareItem(task:Task, view:View) {
        val statusText = if (task.isCompleted) "Completada"
                         else "pendiente"
        val shareText = getString(R.string.share_text,task.title,task.description,statusText)
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,"COMPARTIR")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }
}