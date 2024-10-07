package com.alanturing.cpifp.todo.data

import com.alanturing.cpifp.todo.model.Task

class TaskLocalRepository() {
    companion object {
        private var _INSTANCE:TaskLocalRepository? = null
        fun getInstance():TaskLocalRepository {
            if (_INSTANCE == null) {
                return _INSTANCE ?: TaskLocalRepository()
            }
            return _INSTANCE!!
        }
    }
    private val contador:Int = 0
    private val _tasks = mutableListOf<Task>()
    init {

        TODO("Borrar cuando el repositorio este listo")
        _tasks.add(Task(1,"Comprar leche","Leche desnatada",false))
        _tasks.add(Task(2,"Hacer práctica Android","Completar todos los TODOS",false))

    }


    val tasks:List<Task>
        get() = _tasks

    fun add(task:Task) {
        TODO("Código crear tarea")
        _tasks.add(task)
    }
    fun delete(id:Int) {
        TODO("Código eliminar tarea por id")
    }
    fun update(task:Task) {
        TODO("Código actualizar tarea con id==id")
    }
    fun getNextTaskId() = _tasks.size + 1
}