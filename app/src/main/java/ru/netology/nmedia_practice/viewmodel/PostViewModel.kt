package ru.netology.nmedia_practice.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia_practice.activity.MainActivity
import ru.netology.nmedia_practice.repository.PostRepository
import ru.netology.nmedia_practice.repository.PostRepositoryInMemoryImpl

class PostViewModel: ViewModel (){
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() {
        repository.like()
    }
    fun send() {
        repository.send()
    }
}