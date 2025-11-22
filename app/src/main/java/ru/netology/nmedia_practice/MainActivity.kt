package ru.netology.nmedia_practice

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            likedByMe = false,
            send = false,
            countLikes = 10_000,
            countSend = 100,
            countView = 50
        )

        fun formatCount(count: Long): String {
            return when {
                count < 1000 -> count.toString()
                count < 10000 -> {
                    val thousands = count / 1000
                    val hundreds = (count % 1000) / 100
                    "$thousands.$hundreds K"
                }

                count < 1_000_000 -> {
                    "${count / 1000} K"
                }

                else -> {
                    val millions = count / 1000000
                    val hunThous = (count % 1000000) / 100000
                    "$millions.$hunThous M"
                }
            }
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        with(binding) {
            avatar.setImageResource(R.drawable.netology)
            countLikes.text = formatCount(post.countLikes)
            countSend.text = formatCount(post.countSend)
            countView.text = formatCount(post.countView)
            author.text = post.author
            published.text = post.published
            content.text = post.content
            send.setImageResource(R.drawable.outline_send_24)
            view.setImageResource(R.drawable.outline_visibility_24)

            favorite.setImageResource(
                if (post.likedByMe) {
                    R.drawable.baseline_favorite_24
                } else {
                    R.drawable.outline_favorite_24
                }
            )

            favorite.setOnClickListener {
                it
                post.likedByMe = !post.likedByMe
                favorite.setImageResource(
                    if (post.likedByMe) R.drawable.baseline_favorite_24
                    else R.drawable.outline_favorite_24
                )
                if (post.likedByMe) post.countLikes++ else post.countLikes--
                countLikes.text = formatCount(post.countLikes)
            }

            send.setOnClickListener {
                it
                post.send = !post.send

                if (post.send) {
                    post.countSend++
                    post.send = false
                }
                countSend.text = formatCount(post.countSend)

            }
        }


        val padding = resources.getDimensionPixelSize(R.dimen.common_spacing)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + padding,
                systemBars.top + padding,
                systemBars.right + padding,
                systemBars.bottom + padding
            )
            insets
        }
    }
}