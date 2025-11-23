package ru.netology.nmedia_practice.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia_practice.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun send()
}