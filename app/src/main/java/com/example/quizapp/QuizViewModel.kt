package com.example.quizapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Quiz(val question: String, val options: List<String>, val answer: Int)

val questionSet = listOf(
    Quiz("Where is Thammasat University?", listOf("Thailand", "USA", "Japan", "Korea"), 0),
    Quiz("What color of Thammasat engineering?", listOf("Blue-Green", "Red-yellow", "Black-Pink", "Rainbow"), 1),
    Quiz("Which company owns the Apple?", listOf("Google", "Apple", "Nokia", "Samsung"), 1),
    Quiz("Name of the Thammasat Engineering Camp", listOf("Larngear", "Laodinsor", "Poohbear", "Varasarn"), 1),
    Quiz("Who draw mona lisa?", listOf("Jackson", "Leonardo da Vinci", "Bambam", "Tu"), 1),
    Quiz("What animal is pikachu?", listOf("Pig", "Mouse", "Cat", "Bird"), 1),
    Quiz("The capital of brazil?", listOf("Oslo", "Brasilia", "Oompa loompa", "Moscow "), 1),
    Quiz("How many reindeer does santa have?", listOf("5", "7", "9", "11"), 2),
    Quiz("Who is the villain in Harry Potter?", listOf("Ratthana", "Lord Voldemort", "Darkshadow", "Faii"), 1),
    Quiz("What color of cloud", listOf("red", "white", "blue", "green"), 1),
)

class QuizViewModel : ViewModel() {
    val scoreNum = MutableLiveData<Int>()
    val score: LiveData<Int> = scoreNum
    val quiz = MutableLiveData<Quiz>()
    val question: LiveData<Quiz> = quiz
    val quizCurrent = mutableSetOf<Int>()
    var questionConut = 0
    var count = 0;

    init {
        resetGame()
        nextQuestion()
    }
    fun click(){
        count++
    }
    fun nextQuestion() {
        val unansweredQuestions = questionSet.filter { ! quizCurrent.contains(questionSet.indexOf(it)) }
        if (unansweredQuestions.isNotEmpty()) {
            val randomIndex = (unansweredQuestions.indices ).random()
            quiz.value = unansweredQuestions[randomIndex]
            questionConut++
            quizCurrent.add(questionSet.indexOf(quiz.value))
               }
    }
    fun resetGame() {
        scoreNum.value = 0
        questionConut = 0
        count = 0
        quizCurrent.clear()
    }
    fun Answer(answer: Int) {
        if (answer == quiz.value?.answer) {
            scoreNum.value = (scoreNum.value ?: 0).plus(1)
        }
        nextQuestion()
    }
    fun gameOver(): Boolean {
        return questionSet.size == count;
    }
}
