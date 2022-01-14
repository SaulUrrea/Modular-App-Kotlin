package com.example.task.Dagger

import com.example.recordingapp.Dagger.ApplicationComponent
import com.example.task.TasksActivity
import dagger.Component

@TaskScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(TaskModule::class))

interface TaskComponent {
    fun inject(task: TasksActivity)
}