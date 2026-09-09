package com.rah885.kaamsetu.ui.screens.customer

data class WorkerProfileData(
    val name: String,
    val service: String,
    val rating: String,
    val distance: String,
    val available: Boolean,
    val experience: String = "5+ साल",
    val verified: Boolean = true
)
