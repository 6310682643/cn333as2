package com.example.ass2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.Quiz
import com.example.quizapp.QuizViewModel
import com.example.quizapp.ui.theme.QuizAppTheme


sealed class Screen(val route: String){
    object Home: Screen("home")
    object QuizScreen: Screen("quizScreen")
    object GameOver: Screen("gameOver")
}

class MainActivity : ComponentActivity() {
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val question: Quiz? by viewModel.question.observeAsState(null)
                    val score: Int? by viewModel.score.observeAsState(null)
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "home") {
                        composable(Screen.Home.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(Screen.QuizScreen.route) {
                            QuizScreen(navController = navController, question,score ?: 0){ answerIndex ->
                                viewModel.Answer(answerIndex)
                            }
                        }
                        composable(Screen.GameOver.route) {
                            GameOverScreen(navController = navController, score ?: 0)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF8F06A))
        )
        Text(
            text = stringResource(R.string.gameName),
            fontSize = 50.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                .padding(top = 200.dp),

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)

        ) {
            Button(
                onClick = { navController.navigate("quizScreen")},
                    modifier = Modifier.padding(top = 300.dp)
            ) {
                Text(
                    text = "Start Game"
                )
            }
        }
    }

    @Composable
    fun QuizScreen(navController: NavController,question: Quiz?, score: Int, modifier: Modifier = Modifier, onAnswerSelected: (Int) -> Unit) {
        var currentQuestion by remember { mutableStateOf(0) }
        val totalQuestions = 10
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF8F06A))
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color(0xFF5F5E56))
                .size(48.dp)
                .padding(8.dp),

        ) {
            Text(
                text = "Question $currentQuestion of $totalQuestions",
                fontSize = 22.sp,
                color = Color.White
            )
            Text(
                modifier = modifier
                    .padding(start = 140.dp),
                text = "Score: $score",
                fontSize = 22.sp,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            if (question != null) {
                Text(
                    text = question.question,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                val shuffledOptions = question.options.shuffled() // Shuffle the options
                shuffledOptions.forEachIndexed { index, option ->
                    AnswerOption(
                        option = option,
                        isSelected = false,
                        isEnabled = true,
                        onClick = {
                            onAnswerSelected(question.options.indexOf(option))
                            currentQuestion++
                            viewModel.click()
                            if (viewModel.gameOver()) {
                                navController.navigate("gameOver")
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun GameOverScreen(navController: NavController,score: Int) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color(0xFFF8F06A))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Game Over!",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Your score is $score",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    viewModel.resetGame()
                    navController.navigate("home")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Play Again")
            }
            Button(
                onClick = {
                    finish()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Quit")
            }
        }
    }

    @Composable
    fun AnswerOption(option: String, isSelected: Boolean, isEnabled: Boolean, onClick: () -> Unit) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = null,
                modifier = Modifier.clickable(enabled = isEnabled, onClick = onClick)
            )
            Text(
                text = option,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}


