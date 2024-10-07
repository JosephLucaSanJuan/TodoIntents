package com.alanturing.cpifp.todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityCreateToDoBinding
import com.alanturing.cpifp.todo.model.Task

class CreateToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateToDoBinding
    //private val repository = TaskLocalRepository.getInstance()
    private val viewModel:TaskViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateToDoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_create_to_do)
        setSupportActionBar(binding.topAppBar)
        binding.topAppBar.setNavigationOnClickListener{
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        binding.createButton.setOnClickListener {
            val task = Task(viewModel.getNextTaskId(),
                binding.titleInput.text.toString(),
                binding.descriptionInput.toString(),
                false)
            viewModel.add(task)
            setResult(Activity.RESULT_OK)
            finish()
        }
        binding.cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.save -> {
            val task = Task(viewModel.getNextTaskId(),
                binding.titleInput.text.toString(),
                binding.descriptionInput.text.toString(),
                false)
            viewModel.add(task)
            setResult(Activity.RESULT_OK)
            finish()
            true
        }
        else -> false
    }
}