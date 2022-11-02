package co.innovatespace.source

import co.innovatespace.utility.Event

data class SourceViewState(
    val isLoading: Boolean = true,
    val failure: Event<Throwable>? = null,
    val hasError: Boolean = false
)
