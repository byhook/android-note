package com.handy.note.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.handy.note.base.BaseNoteActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import java.lang.NullPointerException

/**
 * date: 2021-10-27
 * description:
 * 异步流
 * https://book.kotlincn.net/text/flow.html
 */
class NoteKotlinFlowActivity : BaseNoteActivity() {

    companion object {

        const val TAG = "NoteKotlinFlowPage"

        @JvmStatic
        fun intentStart(context: Context) {
            val intent = Intent(context, NoteKotlinFlowActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_kotlin_flow_layer)
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

    private fun fetchNews(): Flow<String> {
        return callbackFlow {
            trySend("hello")
            awaitClose {
                Log.e(TAG, "fetchNews close")
            }
        }
    }

    private fun transferNews(): Flow<String> {
        return flow {
            Log.d(TAG, "transferNews flow ...")
            fetchNews()
                .catch {
                    Log.e(TAG, "transferNews catch ...")
                    throw IllegalStateException("catch...")
                }
                .collect {
                    Log.d(TAG, "transferNews collect $it")
                    throw NullPointerException("xxx")
                }
        }
    }

    private fun flowRequestA(): Flow<String> {
        return callbackFlow {
            trySend("helloA")
            Log.d(TAG,"flowRequestA invoke")
            throw NullPointerException("flowRequestA exception")
            awaitClose {

            }
        }
    }

    private fun flowRequestB(): Flow<String> {
        return flow {
            flowRequestA()
                .catch {
                    Log.e(TAG,"flowRequestB catch")
                }.collect {
                    Log.d(TAG,"flowRequestB collect")
                    emit(it)
                }
        }
    }

    private fun flowRequestC(): Flow<String> {
        return flow {
            flowRequestB()
                .catch {
                    Log.e(TAG,"flowRequestC catch")
                }.collect {
                    Log.d(TAG,"flowRequestC collect")
                    emit(it)
                }
        }
    }

    fun onWithFlowClick(view: View?) {
        GlobalScope.launch {
            /*transferNews()
                .catch {
                    Log.e(TAG,"transferNews onWithFlowClick catch ...")
                }
                .collect{
                    Log.d(TAG,"transferNews onWithFlowClick collect $it")
                }*/

            flowRequestC()
                .catch {
                    Log.e(TAG,"onWithFlowClick flowRequestC catch")
                }.collect {
                    Log.d(TAG,"onWithFlowClick flowRequestC collect")
                }

        }
    }

}