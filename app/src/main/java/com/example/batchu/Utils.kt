package com.example.batchu

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.GridLayout
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
    ),
    Question(
        question = "BAOTHUCUREMOHA",
        photoDescription = R.drawable.image_bao_thuc,
        answer = "BAOTHUC"
    ),
    Question(
        question = "CATINHIQIOLP",
        photoDescription = R.drawable.image_ca_tinh,
        answer = "CATINH"
    ),
    Question(
        question = "CUNGCAULENHUOP",
        photoDescription = R.drawable.image_cung_cau,
        answer = "CUNGCAU"
    ),
    Question(
        question = "DAUTHUMAEYREB",
        photoDescription = R.drawable.image_dau_thu,
        answer = "DAUTHU"
    ),
    Question(
        question = "GOKIENLKTERAP",
        photoDescription = R.drawable.image_go_kien,
        answer = "GOKIEN"
    ),
    Question(
        question = "HAMAOLIYEEMMHO",
        photoDescription = R.drawable.image_ha_ma,
        answer = "HAMA"
    ),
    Question(
        question = "KIENTHUCKAUOTI",
        photoDescription = R.drawable.image_kien_thuc,
        answer = "KIENTHUC"
    ),
    Question(
        question = "KINHDOCAUNGOIP",
        photoDescription = R.drawable.image_kinh_do,
        answer = "KINHDO"
    ),
    Question(
        question = "NOIGIANDIEPMIH",
        photoDescription = R.drawable.image_noi_gian,
        answer = "NOIGIAN"
    ),
    Question(
        question = "TATYEUTAYTHANH",
        photoDescription = R.drawable.image_tat_yeu,
        answer = "TATYEU"
    ),
    Question(
        question = "THANTHANODENIM",
        photoDescription = R.drawable.image_than_than,
        answer = "THANTHAN"
    ),
    Question(
        question = "TIENCHAMHAUTAUMEPOIM",
        photoDescription = R.drawable.image_tien_cham_hau_tau,
        answer = "TIENCHAMHAUTAU"
    ),
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

fun GridLayout.countTextView(): Int {
    var textViewCount = 0

    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        if (child is TextView) {
            textViewCount++
        }
    }
    return textViewCount
}
