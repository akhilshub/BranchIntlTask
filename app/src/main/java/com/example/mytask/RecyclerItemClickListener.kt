package com.example.mytask


import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(
    context: Context,
    recyclerView: RecyclerView,
    private val mListener: OnItemClickListener?
) : RecyclerView.OnItemTouchListener {

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onLongItemClick(view: View, position: Int)
    }

    private val mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            val child = recyclerView.findChildViewUnder(e.x, e.y)
            if (child != null && mListener != null) {
                mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child))
            }
        }
    })

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val child = view.findChildViewUnder(e.x, e.y)
        if (child != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(child, view.getChildAdapterPosition(child))
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}
