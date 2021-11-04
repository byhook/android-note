package com.handy.note.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.handy.note.base.BaseNoteActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
        for (index in 0..2) {
            Thread.sleep(1000)
            yield(index)
        }
    }

    fun onSequenceClick(view: View?) {
        //简单示例
        simpleSequence().forEach { value -> Log.d(TAG, "sequence $value") }
    }

    private fun simpleFlow(): Flow<Int> = flow {
        for (index in 0..2) {
            delay(1000)
            emit(index)
        }
    }

    fun onFlowClick(view: View?) {
        runBlocking {
            simpleFlow().collect { value -> Log.d(TAG, "collect $value") }
        }
    }

    fun onReduceClick(view: View?) {
        GlobalScope.async {
            val sum = (1..3).asFlow()
                .map { it * it }
                .reduce { a, b -> a + b }
            Log.d(TAG, "onReduceClick sum:$sum")
        }
    }

    fun onCollectClick(view: View?) {
        GlobalScope.async {
            (1..5).asFlow()
                .filter {
                    Log.d(TAG, "onCollectClick filter it:$it")
                    it % 2 == 0
                }
                .map {
                    Log.d(TAG, "onCollectClick map it:$it")
                    it
                }
                .collect {
                    Log.d(TAG, "onCollectClick collect it:$it")
                }

        }
    }

    private fun withContextSimple(): Flow<Int> = flow {
        Log.d(TAG, "withContextSimple started simple flow")
        for (i in 1..3) {
            delay(1000L)
            //[DefaultDispatcher-worker-1]
            Log.d(TAG, "withContextSimple [${Thread.currentThread().name}] emit:$i")
            emit(i)
        }
    }.flowOn(Dispatchers.Default)

    fun onWithContextClick(view: View?) = runBlocking {
        withContextSimple().collect { value -> Log.d(TAG, "onWithContextClick collected $value") }
    }

}