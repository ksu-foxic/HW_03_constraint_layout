package ru.netology.nmedia_practice

data class Post (
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likedByMe: Boolean,
    var send: Boolean,
    var countLikes: Long,
    var countSend: Long,
    var countView: Long
)
