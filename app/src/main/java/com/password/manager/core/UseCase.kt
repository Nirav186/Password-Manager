package com.password.manager.core

import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> {

    abstract suspend fun action(params: Params): Type

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Result<Type>) -> Unit = {},
    ) {
        scope.launch {
            val result = withContext(executionDispatcher) { kotlin.runCatching { action(params) } }
            onResult(result)
        }
    }
}
