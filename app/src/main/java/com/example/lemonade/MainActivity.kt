package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonApp(
                        Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center))
                }
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier){
    var currentStep by remember { mutableStateOf(1) }
    var squeeze by remember{ mutableStateOf(1) }
    var selectImage = when(currentStep) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }

    var desc = when(currentStep){
            1-> R.string.desc_1
            2-> R.string.desc_2
            3-> R.string.desc_3
            else-> R.string.desc_4
        }
    var contentDesc = when(currentStep){
        1-> R.string.lemon_tree
        2-> R.string.lemon
        3-> R.string.glass_lemon
        else-> R.string.empty_glass
    }

    squeeze = (2..4).random()
    var tapCount = 0

    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = stringResource(desc),
        fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(selectImage),
            contentDescription = stringResource(contentDesc),
            modifier = Modifier.clickable {
                tapCount++
                if(currentStep == 2 ) {
                    if(tapCount == squeeze) currentStep++
                    else return@clickable
                } else if (currentStep == 4 ) {
                    currentStep = 1
                } else {
                    currentStep++
                }
            }.border(2.dp, Color(105,205,216))
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
    }

}