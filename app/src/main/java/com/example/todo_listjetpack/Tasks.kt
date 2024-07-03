package com.example.todo_listjetpack

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier

data class TaskItems (
        val id : Int,
        val name : String,
        val priority : Int,
        val isEditing : Boolean = false
)

@Composable
fun Todo() {
        var sItems by remember { mutableStateOf(listOf<TaskItems>()) }
        Column(
                modifier = java.lang.reflect.Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                // Your composables inside the Column
                Button(onClick = { /*TODO*/ }) {
                        Text(text = "Add Tasks")
                }

                LazyColumn {
                        items(sItems) {
                                // Your composables inside LazyColumn
                        }
                }
        }
}

