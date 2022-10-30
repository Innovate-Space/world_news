package co.innovatespace.source

import co.innovatespace.utility.Event

data class SourceViewState(
    val loading: Boolean = true,
    val failure: Event<Throwable>?,
    val query: String?
)
