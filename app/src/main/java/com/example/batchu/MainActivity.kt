package com.example.batchu

import android.annotation.SuppressLint
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.batchu.databinding.ActivityMainBinding
import com.example.batchu.models.Question
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var question: Question
    private val suggestViews = mutableListOf<View>() // Lưu tất cả view trong viewSuggest
    private var stateAnswers = mutableListOf<Boolean>() // Lưu trạng thái của các ô viewAnswer true is filled, false is empty
    private var questionGames = mutableListOf<Question>()
    private var indexQuestion = -1
    private val party = Party(
        speed = 0f,
        maxSpeed = 30f,
        damping = 0.9f,
        spread = 360,
        colors = listOf(0xff1744, 0xff9100, 0x00e676, 0xf2979ff, 0xd500f9, 0xffea00, 0x00e5ff, 0xff4081),
        emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
        position = Position.Relative(0.5, 0.3)
    )
    private var celebration: KonfettiView? = null
    private var soundPool: SoundPool? = null
    private var winSound: Int = 0
    
    @SuppressLint("InflateParams", "MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        questionGames = questions.shuffled().toMutableList()
        soundPool = SoundPool.Builder().setMaxStreams(5).build()
        winSound = soundPool?.load(this, R.raw.pass_20, 1) ?: 0

        resetGame()

        binding?.reset?.onClickEffect {
            resetGame()
        }
    }
    
    private fun moveItemToAnswer(clickedView: View, character: String) {
        // Tạo view mới để di chuyển
        val movingView = layoutInflater.inflate(R.layout.item_suggest, binding?.main, false)
        movingView.findViewById<TextView>(R.id.txtSuggest).text = character
        
        // Lấy vị trí của view được click trong viewSuggest
        val location = IntArray(2)
        clickedView.getLocationInWindow(location)
        val parentLocation = IntArray(2)
        binding?.main?.getLocationInWindow(parentLocation)
        movingView.x = location[0] - parentLocation[0].toFloat()
        movingView.y = location[1] - parentLocation[1].toFloat()
        
        // Thêm movingView vào main layout
        binding?.main?.addView(movingView)
        
        // Lấy vị trí đích (ô trống đầu tiên trong viewAnswer)
        val indexAnswerEmpty = stateAnswers.indexOfFirst { !it }
        stateAnswers[indexAnswerEmpty] = true
        val targetView = binding?.viewAnswer?.getChildAt(indexAnswerEmpty)
        val targetLocation = IntArray(2)
        targetView?.getLocationInWindow(targetLocation)
        val destX = targetLocation[0] - parentLocation[0]
        val destY = targetLocation[1] - parentLocation[1]

        AnimationUtils.moveItem(movingView, destX, destY, start = {
            // Ẩn view gốc trong viewSuggest
            clickedView.visibility = View.INVISIBLE
        }, end = {
            // Xóa movingView khỏi main layout
            binding?.main?.removeView(movingView)

            // Tạo view mới trong viewAnswer
            val answerView =
                layoutInflater.inflate(R.layout.item_suggest, binding?.viewAnswer, false)
            answerView.findViewById<TextView>(R.id.txtSuggest).text = character

            // Thêm click listener để có thể di chuyển ngược lại
            answerView.setOnClickListener {
                moveItemBackToSuggest(answerView, character, clickedView)
            }

            // Thay thế ô trống bằng view có ký tự
            binding?.viewAnswer?.removeViewAt(indexAnswerEmpty)
            binding?.viewAnswer?.addView(answerView, indexAnswerEmpty)

            // Kiểm tra nếu đã hoàn thành
            val childCount = binding?.viewAnswer?.countTextView() ?: 0
            if (childCount >= question.answer.replace(" ", "").length) {
                checkAnswer()
            }
        })
    }

    private fun isCompleteFill(): Boolean {
        // Kiểm tra xem tất cả ô trong viewAnswer đã được điền hay chưa
        return stateAnswers.all { it }
    }

    private fun moveItemBackToSuggest(
        clickedView: View,
        character: String,
        originalView: View,
    ) {
        val answerIndex = binding?.viewAnswer?.indexOfChild(clickedView)
        if (answerIndex != null) {
            stateAnswers[answerIndex] = false
        }

        // Tạo view mới để di chuyển
        val movingView = layoutInflater.inflate(R.layout.item_suggest, binding?.main, false)
        movingView.findViewById<TextView>(R.id.txtSuggest).text = character

        // Lấy vị trí của view được click trong viewAnswer
        val location = IntArray(2)
        clickedView.getLocationInWindow(location)
        val parentLocation = IntArray(2)
        binding?.main?.getLocationInWindow(parentLocation)
        movingView.x = location[0] - parentLocation[0].toFloat()
        movingView.y = location[1] - parentLocation[1].toFloat()

        // Thêm movingView vào main layout
        binding?.main?.addView(movingView)

        // Lấy vị trí đích (vị trí ban đầu trong viewSuggest)
        val targetLocation = IntArray(2)
        originalView.getLocationInWindow(targetLocation)
        val destX = targetLocation[0] - parentLocation[0]
        val destY = targetLocation[1] - parentLocation[1]

        AnimationUtils.moveItem(movingView, destX, destY, start = {
            // Ẩn view trong viewAnswer
            clickedView.visibility = View.INVISIBLE

            // Thay thế view trong viewAnswer bằng ô trống
            val answerEmpty =
                layoutInflater.inflate(R.layout.item_answer_empty, binding?.viewAnswer, false)
            binding?.viewAnswer?.removeViewAt(answerIndex ?: 0)
            binding?.viewAnswer?.addView(answerEmpty, answerIndex ?: 0)
        }, end = {
            // Xóa movingView khỏi main layout
            binding?.main?.removeView(movingView)

            // Hiện lại view gốc trong viewSuggest
            originalView.visibility = View.VISIBLE
        })
    }
    
    private fun checkAnswer() {
        val userAnswer = StringBuilder()
        val childCount = binding?.viewAnswer?.childCount ?: 0
        for (i in 0 until childCount) {
            val child = binding?.viewAnswer?.getChildAt(i)
            if (child is TextView) {
                userAnswer.append(child.text)
            }
        }
        
//         Kiểm tra câu trả lời
        val answer = question.answer.replace(" ", "").lowercase()
        val removeDiacritics = removeDiacritics(answer)
        if (userAnswer.toString().lowercase() == removeDiacritics) {
            soundPool?.play(winSound, 1f, 1f, 1, 0, 1f)
            celebration = KonfettiView(this, null, 0)
            celebration?.setBackgroundResource(android.R.color.transparent)
            celebration?.layoutParams =
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            celebration?.start(party)
            binding?.main?.addView(celebration)
            answer.split("").filter { it.isNotEmpty() }.forEachIndexed { index, s ->
                val answerView = binding?.viewAnswer?.getChildAt(index)
                if (answerView is TextView) {
                    answerView.text = s
                }
            }
        } else {
//          Sai - có thể reset hoặc hiển thị thông báo
            binding?.viewAnswer?.shakeView()
        }
    }
    
    @SuppressLint("SetTextI18n")
    private fun resetGame() {
        if (indexQuestion >= questionGames.size - 1) {
            indexQuestion = 0
        } else {
            indexQuestion++
        }
        binding?.txtQuestionQuantity?.text = "Câu ${indexQuestion + 1} / ${questionGames.size}"
        question = questionGames[indexQuestion]

        // Reset game về trạng thái ban đầu
        stateAnswers.clear()
        // Xóa tất cả view trong viewAnswer
        binding?.viewAnswer?.removeAllViews()
        // Xóa tất cả view trong viewSuggest
        binding?.viewSuggest?.removeAllViews()
        binding?.imageDescription?.setImageResource(question.photoDescription)

        for (i in 0 until question.answer.replace(" ", "").length) {
            val answerEmpty = layoutInflater.inflate(R.layout.item_answer_empty, binding?.viewAnswer, false)
            binding?.viewAnswer?.addView(answerEmpty)
            stateAnswers.add(false)
        }

        // Tạo các gợi ý từ kết quả
        question.question.split("").shuffled().forEach { c ->
            if (c.isNotEmpty()) {
                val suggest = layoutInflater.inflate(R.layout.item_suggest, binding?.viewSuggest, false)
                suggest.findViewById<TextView>(R.id.txtSuggest).text = c
                binding?.viewSuggest?.addView(suggest)

                // Lưu view vào danh sách
                suggestViews.add(suggest)

                suggest.setOnClickListener {
                    if (!isCompleteFill()) {
                        moveItemToAnswer(suggest, c)
                    }
                }
            }
        }
        
        // Hiện lại tất cả view trong viewSuggest
        suggestViews.forEach { view ->
            view.visibility = View.VISIBLE
        }
    }
}