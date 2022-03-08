package com.az.moviesapp.core.data.network.connectivity

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "internet_error"
}