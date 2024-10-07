package com.alanturing.cpifp.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.model.Task

class TaskViewModel():ViewModel() {
    private val repository = TaskLocalRepository.getInstance()
    private val database : MutableLiveData<List<Task>> = MutableLiveData()
    val data: LiveData<List<Task>> get() = database

    init {
        fetchTasks()
    }

    fun fetchTasks(){
        database.value = repository.tasks
    }

    fun add(task:Task) {
        repository.add(task)
        fetchTasks()
    }

    fun getNextTaskId() = repository.getNextTaskId()
}