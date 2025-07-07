package com.example.batchu

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import android.widget.TextView
import com.example.batchu.models.Question

val questions = listOf(
    Question(
        question = "BAOCAOHTEBURNY",
        photoDescription = R.drawable.image_bao_cao,
        answer = "BAOCAO"
    ),
    Question(
        question = "CADAOORUBCMSUE",
        photoDescription = R.drawable.image_ca_dao,
        answer = "CADAO"
    ),
    Question(
        question = "COLOAPEUGTY",
        photoDescription = R.drawable.image_co_loa,
        answer = "COLOA"
    ),
    Question(
        question = "LABANOEUBCLK",
        photoDescription = R.drawable.image_la_ban,
        answer = "LABAN"
    ),
    Question(
        question = "MATMANETAGE",
        photoDescription = R.drawable.image_mat_ma,
        answer = "MATMA"
    ),
    Question(
        question = "XAPHONGTENKLI",
        photoDescription = R.drawable.image_xa_phong,
        answer = "XAPHONG"
    ),
    Question(
        question = "YEUOTEMKAUOT",
        photoDescription = R.drawable.image_yeu_ot,
        answer = "YEUOT"
    )
)

fun View.shakeView() {
    val animator = ObjectAnimator.ofFloat(
        this,
        "translationX",
        0f, -25f, 25f, -20f, 20f, -15f, 15f, -10f, 10f, -5f, 5f, 0f
    )
    animator.duration = 600
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.start()
}

fun LinearLayout.countTextView(): Int {
    var textViewCount = 0

    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        if (child is TextView) {
            textViewCount++
        }
    }
    return textViewCount
}
