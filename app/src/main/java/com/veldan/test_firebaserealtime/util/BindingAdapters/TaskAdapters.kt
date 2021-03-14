package com.veldan.test_firebaserealtime.util.BindingAdapters

// FOR UPDATE
//import android.util.Log
//import android.view.inputmethod.EditorInfo
//import android.widget.EditText
//import androidx.databinding.BindingAdapter
//
//@BindingAdapter("onEditorActionListener")
//fun EditText.onEditorActionListener(block: (updateText: String) -> Unit) {
//
//    this.setOnEditorActionListener { view, actionId, event ->
//        return@setOnEditorActionListener when (actionId) {
//            EditorInfo.IME_ACTION_DONE -> {
//                Log.i("LLL", "onEditorActionListener: ")
//                block(this.text.toString())
//                true
//            }
//            else -> false
//        }
//    }
//}