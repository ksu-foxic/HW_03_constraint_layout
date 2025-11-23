package ru.netology.nmedia_practice.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia_practice.R
import ru.netology.nmedia_practice.databinding.ActivityMainBinding
import ru.netology.nmedia_practice.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fun Long.formatCount(): String {
            return when {
                this < 1_000 -> "$this"
                this < 10_000 -> "${this / 1000}.${(this % 1000) / 100}K"
                this < 1_000_000 -> "${this / 1_000}K"
                else -> "${this / 1_000_000}.${(this % 1_000_000) / 100_000}M"
            }
        }

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                avatar.setImageResource(R.drawable.netology)
                countLikes.text = post.countLikes.formatCount()
                countSend.text = post.countSend.formatCount()
                countView.text = post.countView.formatCount()
                author.text = post.author
                published.text = post.published
                content.text = post.content
                send.setImageResource(R.drawable.outline_send_24)
                view.setImageResource(R.drawable.outline_visibility_24)

                if (post.likedByMe) {
                    like.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    like.setImageResource(R.drawable.outline_favorite_24)
                }
                countLikes?.text = post.countLikes.formatCount()
                countSend?.text = post.countSend.formatCount()
                countView?.text = post.countView.formatCount()
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
        binding.like?.setOnClickListener {
            it
            viewModel.like()
        }
        binding.send?.setOnClickListener {
            it
            viewModel.send()
        }
    }
}