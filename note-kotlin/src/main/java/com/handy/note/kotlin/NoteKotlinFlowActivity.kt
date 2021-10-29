package com.handy.note.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.handy.note.base.BaseNoteActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * date: 2021-10-27
 * description:
 * 异步流
 * https://book.kotlincn.net/text/flow.html
 */
class NoteKotlinFlowActivity : BaseNoteActivity() {

    companion object {

        const val TAG = "NoteKotlinFlowActivity"

        @JvmStatic
        fun intentStart(context: Context) {
            val intent = Intent(context, NoteKotlinFlowActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activigty_note_kotlin_flow_layer)
    }

    private fun simple(): List<Int> = listOf(0, 1, 2)

    fun onSimpleClick(view: View?) {
        //简单示例
        simple().forEach { value -> Log.d(TAG, "hello $value") }
    }

    private fun simpleSequence(): Sequence<Int> = sequence {
         for (index in 0..2){
             Thread.sleep(1000)
             yield(index)
         }
    }

    fun onSequenceClick(view: View?) {
        //简单示例
        simpleSequence().forEach { value -> Log.d(TAG, "sequence $value") }
    }

}