package com.wahyu.filmskuy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.wahyu.filmskuy.data.remote.models.MovieResult
import com.wahyu.filmskuy.utils.AppExecutors
import com.wahyu.filmskuy.vo.Resources

/**
 * Created by wahyu_septiadi on 14, November 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

abstract class NetworkBoundResource<ResultType, RequestType>(private val appExecutor: AppExecutors) {

    private val result = MediatorLiveData<Resources<ResultType>>()

    init {
        result.value = Resources.loading(null)

        @Suppress("LeakingThis") val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                createCall()
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resources.success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<MutableList<MovieResult>>

    protected abstract fun saveCallResult(data: RequestType)

//    protected abstract fun fetchDataFromCall(data: RequestType, dbData: ResultType?): ResultType?

//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//
//        val apiResponse = createCall()
//
//        result.addSource(dbSource) { newData ->
//            result.value = Resources.loading(newData)
//        }
//        result.addSource(apiResponse) { response ->
//            result.removeSource(apiResponse)
//            result.removeSource(dbSource)
//            when (response.status) {
//                StatusResponse.SUCCESS ->
//                    appExecutor.diskIO().execute {
//                        saveCallResult(response.body)
//                        appExecutor.mainThread().execute {
//                            result.addSource(loadFromDB()) { newData ->
//                                result.value = Resources.success(newData)
//                            }
//                        }
//                    }
//                StatusResponse.EMPTY -> appExecutor.mainThread().execute {
//                    result.addSource(loadFromDB()) { newData ->
//                        result.value = Resources.success(newData)
//                    }
//                }
//                StatusResponse.ERROR -> {
//                    onFetchFailed()
//                    result.addSource(dbSource) { newData ->
//                        result.value = Resources.error(response.message, newData)
//                    }
//                }
//            }
//        }
//    }

//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//        val apiResponse = createCall()
//
//        result.addSource(dbSource) { newData ->
//            result.value = Resources.loading(newData)
//        }
//
//        apiResponse.let { apiResponses ->
//            result.addSource(apiResponses) { response ->
//                result.removeSource(dbSource)
//                result.removeSource(apiResponses)
//
//                when (response.status) {
//                    StatusResponse.SUCCESS -> {
//
//                        if (needSaveToDB()) {
//                            CoroutineScope(Dispatchers.IO).launch {
//                                response.body?.let { saveCallResult(it) }
//                                withContext(Dispatchers.Main) {
//                                    result.addSource(loadFromDB()) { newData ->
//                                        result.value = Resources.success(newData)
//                                    }
//                                    Log.e(
//                                        NetworkBoundResource::class.java.simpleName,
//                                        "save to db"
//                                    )
//                                }
//                            }
//                        } else {
//                            Log.e(
//                                NetworkBoundResource::class.java.simpleName,
//                                "not save to db"
//                            )
//
//                            result.addSource(loadFromDB()) { newData ->
//                                result.removeSource(loadFromDB())
//
//                                val data = response.body?.let { fetchDataFromCall(it, newData) }
//                                val test = MutableLiveData<ResultType>()
//                                test.value = data
//                                result.addSource(test) {
//                                    result.value = Resources.success(it)
//                                }
//                            }
//                        }
//                    }
//
//                    StatusResponse.EMPTY -> {
//                        result.addSource(loadFromDB()) { newData ->
//                            result.value = Resources.success(newData)
//                        }
//                        Log.e(NetworkBoundResource::class.java.simpleName, "Empty")
//                    }
//
//                    StatusResponse.ERROR -> {
//                        onFetchFailed()
//                        result.addSource(dbSource) { newData ->
//                            result.value = Resources.error(response.message, newData)
//                        }
//                        Log.e(NetworkBoundResource::class.java.simpleName, "Error")
//                    }
//                }
//            }
//        }
//    }

    fun asLiveData(): LiveData<Resources<ResultType>> = result
}