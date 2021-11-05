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
 */
class NoteKotlinActivity : BaseNoteActivity() {

    private var mainScope = CoroutineScope(Dispatchers.Default)

    companion object {

        const val TAG = "NoteKotlinActivity"

        @JvmStatic
        fun intentStart(context: Context) {
            val intent = Intent(context, NoteKotlinActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_kotlin_layer)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    fun onCoroutinesClick(view: View?) {
        runBlocking {
            val job = launch {
                //delay(8000)
                doWorker()
            }
            //doWorker()
            //doWorld()
            job.join()
            Log.d(TAG,"done")
        }

    }

    private suspend fun doWorld(){
        coroutineScope {
            launch {
                delay(2000L)
                Log.d(TAG,"world 2000")
            }
            launch {
                delay(1000L)
                Log.d(TAG,"world 1000")
            }
            Log.d(TAG,"hello")
        }
    }

    private suspend fun doWorker(){
        coroutineScope {
            launch {
                delay(3000L)
                Log.d(TAG,"world")
            }
            Log.d(TAG,"hello")
        }
    }

    fun onAsyncClick(view: View?) {
        GlobalScope.launch(Dispatchers.Default) {
            runBlocking {
                val timeCost = measureTimeMillis {
                    //并行执行
                    val one = async { doSomethingUseOne() }
                    val two = async { doSomethingUseTwo() }
                    Log.d(TAG,"the result is ${one.await() + two.await()} ")
                }
                Log.d(TAG,"complete in $timeCost ms")
            }
        }
    }

    private suspend fun doSomethingUseOne(): Int {
        delay(1000L)
        return 15
    }

    private suspend fun doSomethingUseTwo(): Int {
        delay(1000L)
        return 35
    }

    fun onScopeClick(view: View?){
        repeat(100) { index ->
            mainScope.launch {
                delay((index + 1) * 200L)
                Log.d(TAG,"mainScope repeat")
            }
        }
        finish()
    }

    fun onFlowClick(view: View?){
        NoteKotlinFlowActivity.intentStart(this)
    }

    fun onChannelClick(view: View?){
        NoteKotlinChannelActivity.intentStart(this)
    }

}