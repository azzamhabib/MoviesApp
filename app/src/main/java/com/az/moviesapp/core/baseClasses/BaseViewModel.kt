package com.az.moviesapp.core.baseClasses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.az.moviesapp.core.util.Event


abstract class BaseViewModel : ViewModel() {


    val loading = MutableLiveData<Boolean>()
    val isDataLoadingSuccessful = MutableLiveData<Boolean>()
    val networkErrorEvent = MutableLiveData<Event<Boolean>>()
    val lazyLoading = MutableLiveData<Event<Boolean>>()
    val searchLoading = MutableLiveData<Event<Boolean>>()
    protected fun isDataLoaded(): Boolean {
        return isDataLoadingSuccessful.value ?: false
    }

    protected fun onSearchLoadingStarted() {
        searchLoading.value = Event(true)
    }

    protected fun onSearchLoadingFinished() {
        searchLoading.value = Event(false)
    }

    protected fun onLazyLoadingStarted() {
        lazyLoading.value = Event(true)
    }

    protected fun onLazyLoadingFinished() {
        lazyLoading.value = Event(false)
    }

    protected fun onLoadingStarted() {
        loading.value = true
        networkErrorEvent.value = Event(false)
    }

    protected fun onLoadingFinished() {
        loading.value = false
    }

    protected fun onLoadingError() {
        isDataLoadingSuccessful.value = false
        networkErrorEvent.value = Event(true)
    }

    protected fun onLoadingSuccess() {
        isDataLoadingSuccessful.value = true
    }
}