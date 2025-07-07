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
    Question(
        question = "AOGIACUIGHBNAE",
        photoDescription = R.drawable.image_ao_giac,
        answer = "AOGIAC"
    ),
    Question(
        question = "BAOQUATOIUKJTE",
        photoDescription = R.drawable.image_bao_quat,
        answer = "BAOQUAT"
    ),
    Question(
        question = "BONGBAYTIEUHOA",
        photoDescription = R.drawable.image_bong_bay,
        answer = "BONGBAY"
    ),
    Question(
        question = "CANBANGUIGHYTE",
        photoDescription = R.drawable.image_can_bang,
        answer = "CANBANG"
    ),
    Question(
        question = "CAOBIETPIYETC",
        photoDescription = R.drawable.image_cao_biet,
        answer = "CAOBIET"
    ),
    Question(
        question = "CAOBUOCKUEUTH",
        photoDescription = R.drawable.image_cao_buoc,
        answer = "CAOBUOC"
    ),
    Question(
        question = "CHAMCONGYTEOAH",
        photoDescription = R.drawable.image_cham_cong,
        answer = "CHAMCONG"
    ),
    Question(
        question = "CHAUCHAUDAXEPOUYNEMKI",
        photoDescription = R.drawable.image_chau_chau_da_xe,
        answer = "CHAUCHAUDAXE"
    ),
    Question(
        question = "CONGBOKEPOLBA",
        photoDescription = R.drawable.image_cong_bo,
        answer = "CONGBO"
    ),
    Question(
        question = "DAMBUTOIKHUYB",
        photoDescription = R.drawable.image_dam_but,
        answer = "DAMBUT"
    ),
    Question(
        question = "DONGCAMCONGKHOPOUENHADPWI",
        photoDescription = R.drawable.image_dong_cam_cong_kho,
        answer = "DONGCAMCONGKHO"
    ),
    Question(
        question = "GIAYBACPOLKAEUM",
        photoDescription = R.drawable.image_giay_bac,
        answer = "GIAYBAC"
    ),
    Question(
        question = "HOAHAUMLUEANJWP",
        photoDescription = R.drawable.image_hoa_hau,
        answer = "HOAHAU"
    ),
    Question(
        question = "HUNGTHUPOLKEABE",
        photoDescription = R.drawable.image_hung_thu,
        answer = "HUNGTHU"
    ),
    Question(
        question = "KEDONLEOIBHTA",
        photoDescription = R.drawable.image_ke_don,
        answer = "KEDON"
    ),
    Question(
        question = "LACDANQUETAGUI",
        photoDescription = R.drawable.image_lac_dan,
        answer = "LACDAN"
    ),
    Question(
        question = "MENMOMKTHUONGI",
        photoDescription = R.drawable.image_men_mo,
        answer = "MENMO"
    ),
    Question(
        question = "MIQUANMEUKHILA",
        photoDescription = R.drawable.image_mi_quan,
        answer = "MIQUAN"
    ),
    Question(
        question = "NEMDAGIAUTAYKHONGTHETHAYNGUOI",
        photoDescription = R.drawable.image_nem_da_giau_tay,
        answer = "NEMDAGIAUTAY"
    ),
    Question(
        question = "NOITHATBAITINHKINH",
        photoDescription = R.drawable.image_noi_that,
        answer = "NOITHAT"
    ),
    Question(
        question = "SOCTRANGLOKEBHAU",
        photoDescription = R.drawable.image_soc_trang,
        answer = "SOCTRANG"
    ),
    Question(
        question = "THANBIPOLEANHTY",
        photoDescription = R.drawable.image_than_bi,
        answer = "THANBI"
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
