package com.soft.crownedjester.programmingquotesapp.presentation.util

sealed class DraggableEvent(id: String) {

    data class OnExpand(val id: String) : DraggableEvent(id)
    data class OnCollapse(val id: String) : DraggableEvent(id)

}
