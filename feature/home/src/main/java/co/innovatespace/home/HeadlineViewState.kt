package co.innovatespace.home

import co.innovatespace.utility.Event

data class HeadlineViewState(
    val loading: Boolean = true,
    val failure: Event<Throwable>?,
    val query: String?
)
