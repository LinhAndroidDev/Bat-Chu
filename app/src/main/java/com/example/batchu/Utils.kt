package com.example.batchu

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.GridLayout
import android.widget.TextView
import com.example.batchu.models.Question
import java.text.Normalizer
import java.util.regex.Pattern

val questions = listOf(
    Question(
        question = "BAOCAOHTEBURNY",
        photoDescription = R.drawable.image_bao_cao,
        answer = "BÁO CÁO"
    ),
    Question(
        question = "CADAOORUBCMSUE",
        photoDescription = R.drawable.image_ca_dao,
        answer = "CA DAO"
    ),
    Question(
        question = "COLOAPEUGTY",
        photoDescription = R.drawable.image_co_loa,
        answer = "CỔ LOA"
    ),
    Question(
        question = "LABANOEUBCLK",
        photoDescription = R.drawable.image_la_ban,
        answer = "LA BÀN"
    ),
    Question(
        question = "MATMANETAGE",
        photoDescription = R.drawable.image_mat_ma,
        answer = "MẬT MÃ"
    ),
    Question(
        question = "XAPHONGTENKLI",
        photoDescription = R.drawable.image_xa_phong,
        answer = "XÀ PHÒNG"
    ),
    Question(
        question = "YEUOTEMKAUOT",
        photoDescription = R.drawable.image_yeu_ot,
        answer = "YẾU ỚT"
    ),
    Question(
        question = "BAOTHUCUREMOHA",
        photoDescription = R.drawable.image_bao_thuc,
        answer = "BÁO THỨC"
    ),
    Question(
        question = "CATINHIQIOLP",
        photoDescription = R.drawable.image_ca_tinh,
        answer = "CÁ TÍNH"
    ),
    Question(
        question = "CUNGCAULENHUOP",
        photoDescription = R.drawable.image_cung_cau,
        answer = "CUNG CẦU"
    ),
    Question(
        question = "DAUTHUMAEYREB",
        photoDescription = R.drawable.image_dau_thu,
        answer = "ĐẦU THÚ"
    ),
    Question(
        question = "GOKIENLKTERAP",
        photoDescription = R.drawable.image_go_kien,
        answer = "GÕ KIẾN"
    ),
    Question(
        question = "HAMAOLIYEEMMHO",
        photoDescription = R.drawable.image_ha_ma,
        answer = "HÀ MÃ"
    ),
    Question(
        question = "KIENTHUCKAUOTI",
        photoDescription = R.drawable.image_kien_thuc,
        answer = "KIẾN THỨC"
    ),
    Question(
        question = "KINHDOCAUNGOIP",
        photoDescription = R.drawable.image_kinh_do,
        answer = "KINH ĐỘ"
    ),
    Question(
        question = "NOIGIANDIEPMIH",
        photoDescription = R.drawable.image_noi_gian,
        answer = "NỘI GIÁN"
    ),
    Question(
        question = "TATYEUTAYTHANH",
        photoDescription = R.drawable.image_tat_yeu,
        answer = "TẤT YẾU"
    ),
    Question(
        question = "THANTHANODENIM",
        photoDescription = R.drawable.image_than_than,
        answer = "THAN THÂN"
    ),
    Question(
        question = "TIENCHAMHAUTAUMEPOIM",
        photoDescription = R.drawable.image_tien_cham_hau_tau,
        answer = "TIỀN CHẢM HẬU TẤU"
    ),
    Question(
        question = "AOGIACUIGHBNAE",
        photoDescription = R.drawable.image_ao_giac,
        answer = "ẢO GIÁC"
    ),
    Question(
        question = "BAOQUATOIUKJTE",
        photoDescription = R.drawable.image_bao_quat,
        answer = "BAO QUÁT"
    ),
    Question(
        question = "BONGBAYTIEUHOA",
        photoDescription = R.drawable.image_bong_bay,
        answer = "BÓNG BAY"
    ),
    Question(
        question = "CANBANGUIGHYTE",
        photoDescription = R.drawable.image_can_bang,
        answer = "CÂN BẰNG"
    ),
    Question(
        question = "CAOBIETPIYETC",
        photoDescription = R.drawable.image_cao_biet,
        answer = "CÁO BIỆT"
    ),
    Question(
        question = "CAOBUOCKUEUTH",
        photoDescription = R.drawable.image_cao_buoc,
        answer = "CÁO BUỘC"
    ),
    Question(
        question = "CHAMCONGYTEOAH",
        photoDescription = R.drawable.image_cham_cong,
        answer = "CHẤM CÔNG"
    ),
    Question(
        question = "CHAUCHAUDAXEPOUYNEMKI",
        photoDescription = R.drawable.image_chau_chau_da_xe,
        answer = "CHÂU CHẤU ĐÁ XE"
    ),
    Question(
        question = "CONGBOKEPOLBA",
        photoDescription = R.drawable.image_cong_bo,
        answer = "CÔNG BỐ"
    ),
    Question(
        question = "DAMBUTOIKHUYB",
        photoDescription = R.drawable.image_dam_but,
        answer = "DÂM BỤT"
    ),
    Question(
        question = "DONGCAMCONGKHOPOUENHADPWI",
        photoDescription = R.drawable.image_dong_cam_cong_kho,
        answer = "ĐỒNG CAM CỘNG KHỔ"
    ),
    Question(
        question = "GIAYBACPOLKAEUM",
        photoDescription = R.drawable.image_giay_bac,
        answer = "GIẤY BẠC"
    ),
    Question(
        question = "HOAHAUMLUEANJWP",
        photoDescription = R.drawable.image_hoa_hau,
        answer = "HOA HẬU"
    ),
    Question(
        question = "HUNGTHUPOLKEABE",
        photoDescription = R.drawable.image_hung_thu,
        answer = "HỨNG THÚ"
    ),
    Question(
        question = "KEDONLEOIBHTA",
        photoDescription = R.drawable.image_ke_don,
        answer = "KÊ ĐƠN"
    ),
    Question(
        question = "LACDANQUETAGUI",
        photoDescription = R.drawable.image_lac_dan,
        answer = "LẠC ĐÀN"
    ),
    Question(
        question = "MENMOMKTHUONGI",
        photoDescription = R.drawable.image_men_mo,
        answer = "MẾN MỘ"
    ),
    Question(
        question = "MIQUANMEUKHILA",
        photoDescription = R.drawable.image_mi_quan,
        answer = "MĨ QUAN"
    ),
    Question(
        question = "NEMDAGIAUTAYKHONGTHETHAY",
        photoDescription = R.drawable.image_nem_da_giau_tay,
        answer = "NÉM ĐÁ GIẤU TAY"
    ),
    Question(
        question = "NOITHATBAITINHKINH",
        photoDescription = R.drawable.image_noi_that,
        answer = "NỘI THẤT"
    ),
    Question(
        question = "SOCTRANGLOKEBHAU",
        photoDescription = R.drawable.image_soc_trang,
        answer = "SÓC TRĂNG"
    ),
    Question(
        question = "THANBIPOLEANHTY",
        photoDescription = R.drawable.image_than_bi,
        answer = "THẦN BÍ"
    ),
    Question(
        question = "NEODONPOLEJETAO",
        photoDescription = R.drawable.image_neo_don,
        answer = "NEO ĐƠN"
    ),
    Question(
        question = "PHICONGHUEIAUMOL",
        photoDescription = R.drawable.image_phi_cong,
        answer = "PHI CÔNG"
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

fun removeDiacritics(input: String): String {
    var result = Normalizer.normalize(input, Normalizer.Form.NFD)
    val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
    result = pattern.matcher(result).replaceAll("")

    // Thay thế thủ công Đ/đ vì Normalizer không xử lý được
    result = result.replace('Đ', 'D')
    result = result.replace('đ', 'd')

    return result
}

@SuppressLint("ClickableViewAccessibility")
fun View.onClickEffect(click: () -> Unit) {
    setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                v.animate().scaleX(0.85f).scaleY(0.85f).setDuration(100).start()
            }

            MotionEvent.ACTION_UP -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }

            MotionEvent.ACTION_CANCEL -> {
                // Nếu người dùng kéo ra ngoài button
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).start()
            }
        }
        false // return true để xử lý toàn bộ touch và tránh gọi onClick tự động
    }

    setOnClickListener {
        click.invoke()
    }
}
