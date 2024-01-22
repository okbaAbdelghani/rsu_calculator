package com.example.calculatersunotes.data.local.questions

import android.content.Context
import com.example.calculatersunotes.data.models.SurveyItem
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


}