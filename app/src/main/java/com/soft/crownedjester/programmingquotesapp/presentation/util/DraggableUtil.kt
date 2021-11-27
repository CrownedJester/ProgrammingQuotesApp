package com.soft.crownedjester.programmingquotesapp.presentation.util

import android.content.res.Resources
import kotlinx.coroutines.flow.MutableStateFlow

fun Float.dp(): Float = this * density * 0.5f

val density: Float
    get() = Resources.getSystem().displayMetrics.density

fun onItemExpanded(cardId: String, idsState: MutableStateFlow<List<String>>) {
    if (idsState.value.contains(cardId)) return
    idsState.value = idsState.value.toMutableList().also { list ->
        list.add(cardId)
    }
}

fun onItemCollapsed(cardId: String, idsState: MutableStateFlow<List<String>>) {
    if (!idsState.value.contains(cardId)) return
    idsState.value = idsState.value.toMutableList().also { list ->
        list.remove(cardId)
    }
}