package com.example.calculatersunotes.data.local.questions

import android.content.Context
import com.example.calculatersunotes.data.models.SurveyItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class QuestionRepository(private  val context: Context) {
    private val _questionsData = mutableListOf<SurveyItem>()
    val questionsList: List<SurveyItem> get() = _questionsData


     fun readJsonFromAssets(fileName: String): String? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun parseJsonToModel(jsonString: String?): MutableList<SurveyItem> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<MutableList<SurveyItem>>() {}.type)
    }


}