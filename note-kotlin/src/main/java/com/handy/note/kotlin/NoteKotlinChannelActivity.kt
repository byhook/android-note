package com.handy.note.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.handy.note.base.BaseNoteActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * date: 2021-10-27
 * description:
 * 异步流
 * https://book.kotlincn.net/text/flow.html
 */
class NoteKotlinChannelActivity : BaseNoteActivity() {

    companion object {

        const val TAG = "NoteKotlinFlowActivity"

        @JvmStatic
        fun intentStart(context: Context) {
            val intent = Intent(context, NoteKotlinChannelActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_kotlin_channel_layer)
    }

}