package com.example.recordingapp.repository.Volley

import com.android.volley.VolleyError

interface VolleyCallBack {
    fun getResponse(response: String?)
    fun getErrorVolley(volleyError: VolleyError?)
}