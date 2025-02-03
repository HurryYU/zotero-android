package org.zotero.android.pdf.reader.translate.repository

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.zotero.android.pdf.reader.translate.bean.TranslateResultBean
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
class TranslateRepository {
    private val httpClient by lazy {
        OkHttpClient.Builder().readTimeout(1,TimeUnit.MINUTES).build()
    }

    suspend fun getTranslateText(sourceText: String): String = withContext(Dispatchers.IO) {
        val gson = Gson()
        val requestMap = mapOf("target_lang" to "ZH", "text" to sourceText)
        val requestBody = gson.toJson(requestMap).toRequestBody("application/json".toMediaType())
        val request =
            Request.Builder().url("http://192.168.31.5:1188/translate").post(requestBody).build()
        try {
            val response = httpClient.newCall(request).execute()
            if (response.code == 200) {
                val result = response.body?.string() ?: ""
                val resultBean = gson.fromJson(result, TranslateResultBean::class.java)
                if (resultBean.code == 200) {
                    resultBean.data
                } else {
                    resultBean.message
                }
            } else {
                ""
            }
        } catch (ex: Exception) {
            ex.message ?: ""
        }
    }
}