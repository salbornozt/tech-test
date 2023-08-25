package com.satdev.jetpackcompose.presentation.home

sealed class HomeScreenEvents {

    data class OnQueryChange(val query: String) : HomeScreenEvents()
    object OnGetSchemeClick : HomeScreenEvents()

    object OnListTablesClick : HomeScreenEvents()
}
