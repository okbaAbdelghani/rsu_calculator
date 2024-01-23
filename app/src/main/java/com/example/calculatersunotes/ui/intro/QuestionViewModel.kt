package com.example.calculatersunotes.ui.intro

import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.local.questions.QuestionRepository
import com.example.calculatersunotes.data.models.SurveyItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuestionViewModel(private val questionRepository: QuestionRepository): ViewModel() {

    fun getQuestions(fileName: String): String?{
        return questionRepository.readJsonFromAssets(fileName)
    }


}