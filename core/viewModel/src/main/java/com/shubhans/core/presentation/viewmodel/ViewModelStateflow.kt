package com.shubhans.core.presentation.viewmodel

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ViewModelStateFlow<T>(
    private val key: ViewModelKey, value: T
) : MutableStateFlow<T> {
    private val mutableStateFlow: MutableStateFlow<Map<ViewModelKey, T>> = MutableStateFlow(
        mapOf(key to value),
    )
    override val subscriptionCount: StateFlow<Int>
        get() = mutableStateFlow.subscriptionCount

    suspend fun emit(key: ViewModelKey, value: T) {
        if (key != this.key) {
            throw IllegalArgumentException(
                "Used different key to emit new value: $value!" +
                        "Don't manipulate key value or try to emit out of ViewModels",
            )
        }
        mutableStateFlow.emit(mapOf(key to value))
    }

    @RestrictedApi
    override suspend fun emit(value: T) =
        throw IllegalAccessError("Use `emitValue` function instead of this")

    fun tryEmit(key: ViewModelKey, value: T): Boolean {
        if (key != this.key) {
            throw IllegalArgumentException(
                "Used different key to emit new value: $value!" +
                        "Don't manipulate key value or try to emit out of ViewModels",
            )
        }

        return mutableStateFlow.tryEmit(mapOf(key to value))
    }

    @RestrictedApi
    override fun tryEmit(value: T): Boolean =
        throw IllegalAccessError("Use `tryEmitValue` function instead of this")

    override fun resetReplayCache() = mutableStateFlow.resetReplayCache()

    override var value: T
        get() = mutableStateFlow.value.getValue(key)
        set(value) {
            mutableStateFlow.value = mapOf(key to value)
        }

    override fun compareAndSet(expect: T, update: T): Boolean =
        mutableStateFlow.compareAndSet(mapOf(key to expect), mapOf(key to update))

    override val replayCache: List<T>
        get() = mutableStateFlow.replayCache.map { value }

    override suspend fun collect(collector: FlowCollector<T>): Nothing = mutableStateFlow.collect {
        collector.emit(it.getValue(key))
    }
}