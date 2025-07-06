package com.example.batchu

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

object AnimationUtils {
    private const val DURATION = 300L

    fun moveItem(movingView: View, destX: Int, destY: Int, start: () -> Unit, end: () -> Unit) {
        // Tạo animator để di chuyển view
        val animatorX = ObjectAnimator.ofFloat(movingView, "x", destX.toFloat())
        val animatorY = ObjectAnimator.ofFloat(movingView, "y", destY.toFloat())
        val animatorScaleX = ObjectAnimator.ofFloat(movingView, "scaleX", 1f, 0.8f, 1f)
        val animatorScaleY = ObjectAnimator.ofFloat(movingView, "scaleY", 1f, 0.8f, 1f)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animatorX, animatorY, animatorScaleX, animatorScaleY)
        animatorSet.duration = DURATION

        animatorSet.addListener(object : android.animation.Animator.AnimatorListener {
            override fun onAnimationStart(animation: android.animation.Animator) {
                start.invoke()
            }

            override fun onAnimationEnd(animation: android.animation.Animator) {
                end.invoke()
            }

            override fun onAnimationCancel(animation: android.animation.Animator) {}
            override fun onAnimationRepeat(animation: android.animation.Animator) {}
        })

        animatorSet.start()
    }
}