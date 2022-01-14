package com.example.recordingapp.repository.Volley

import android.content.Context
import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.HashMap
import kotlin.jvm.Throws

class Volley {
    var context: Context? = null
    var callback: VolleyCallBack? = null
    var requestQueue: RequestQueue? = null

    constructor(context: Context?){
        try {
            this.context = context
            requestQueue = Volley.newRequestQueue(context)
        } catch (e: Exception) {
            Log.i("Problemas","Oscar el precavido")
        }
    }

    fun executeRequestJson(method: Int, objeto: JSONObject?, url: String?, volleyCallBack: VolleyCallBack) {
        this.callback = volleyCallBack
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(method, url, objeto,
            Response.Listener { response -> callback!!.getResponse(response.toString()) },
            Response.ErrorListener { error -> callback!!.getErrorVolley(error) }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val param: MutableMap<String, String> = HashMap()
                param["Content-Type"] = "application/json"
                return param
            }
        }
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            8000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue!!.cache.clear()
        requestQueue!!.add(jsonObjectRequest)
    }
}
