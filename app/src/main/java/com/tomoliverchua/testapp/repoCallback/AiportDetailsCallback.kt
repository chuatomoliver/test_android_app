package com.tomoliverchua.testapp.repoCallback

import com.tomoliverchua.testapp.models.DataResponse

interface AiportDetailsCallback {
    interface OnAirportDetailsCallback {
        fun onSuccess(dataResponse: DataResponse)
        fun onFail(errMessage: String)
    }

    interface OnUpdateAirportDetailsStatusCallback {
        fun onSuccess()
        fun onFail()
    }
}