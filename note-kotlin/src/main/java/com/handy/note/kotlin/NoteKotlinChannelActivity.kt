package com.handy.note.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.handy.note.base.BaseNoteActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * date: 2021-10-27
 * description:
 * 异步流
 * https://book.kotlincn.net/text/channels.html
 * 一个 Channel 是一个和 BlockingQueue 非常相似的概念。其中一个不同是它代替了阻塞的 put 操作并提供了挂起的 send，还替代了阻塞的 take 操作并提供了挂起的 receive。
 */
class NoteKotlinChannelActivity : BaseNoteActivity() {

    companion object {

        const val TAG = "NoteKotlinChannelPage"

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

    fun onSendReceiveClick(view: View?) {
        val channel = Channel<Int>()
        GlobalScope.launch(Dispatchers.Default) {
            //这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做5次整数的平方并发送
            Log.d(TAG, "[${Thread.currentThread().name}] send start")
            for (x in 1..5) channel.send(x * x)
        }
        //这里我们打印了5次被接收的整数：
        GlobalScope.launch(Dispatchers.Main) {
            repeat(5) { Log.d(TAG, "[${Thread.currentThread().name}] receive:${channel.receive()}") }
        }
    }

    fun onChannelCloseClick(view: View?){
        val channel = Channel<Int>()
        GlobalScope.launch(Dispatchers.Default) {
            Log.d(TAG, "[${Thread.currentThread().name}] send start")
            for (x in 1..5) channel.send(x * x)
            //关闭通道
            channel.close()
        }
        GlobalScope.launch(Dispatchers.Main) {
            for (index in channel) {
                Log.d(TAG, "[${Thread.currentThread().name}] index:$index")
            }
        }
    }

}