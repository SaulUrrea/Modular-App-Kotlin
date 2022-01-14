package com.medicina.dinamico.Dagger

import com.example.recordingapp.Dagger.ApplicationComponent
import com.medicina.dinamico.PerfilAct
import dagger.Component

@PerfilScope
@Component(dependencies = [ApplicationComponent::class],
    modules = [PerfilModule::class])
interface PerfilComponent {

    fun inject(perfil: PerfilAct)

}