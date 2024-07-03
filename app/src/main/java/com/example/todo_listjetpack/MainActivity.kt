package com.example.todo_listjetpack

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_listjetpack.ui.theme.TODOListJetpackTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOListJetpackTheme {
                Surface(modifier =  Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Todo()
                }
            }
        }
    }
}

//@Composable
//fun Todo(){
//    var sItems by remember { mutableStateOf(listOf<TaskItems>()) }
//    Column (modifier = Modifier.fillMaxSize(),
//        verticalArrangement =  Arrangement.Center,
//        horizontalAlignment =  Alignment.CenterHorizontally
//    )
//    {
//        Button({ /*TODO*/ },) {
//            Text(text = "Add Tasks")
//        }
//
//        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)){
//            items(sItems){
//
//            }
//        }
//    }
//}





@Preview (showBackground = true)
@Composable
fun TodoPreview() {
    Todo()
}

