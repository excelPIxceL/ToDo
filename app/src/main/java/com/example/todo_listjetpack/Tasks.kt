package com.example.todo_listjetpack

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.AssistChipDefaults.shape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.shape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults.shape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class TaskItems (
        val id : Int,
        var name : String,
        var priority : Int,
        var isEditing : Boolean = false
)

@Composable
fun Todo() {
        var sItems by remember { mutableStateOf(listOf<TaskItems>()) }
        var showDialog by remember { mutableStateOf(false) }
        var taskName by remember { mutableStateOf("") }
        var taskPriority by remember { mutableStateOf("") }
        Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Button(modifier = Modifier.padding(32.dp),
                        onClick = { showDialog = true }) {
                        Text(text = "Add Tasks")
                }
                LazyColumn (modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ){
                        items(sItems) {
                                item ->
                                if(item.isEditing){
                                        TaskEditor(item = item, onEditComplete = {
                                                editedName, editedPriority ->
                                                sItems = sItems.map{it.copy(isEditing = false)}
                                                var editedItem = sItems.find { it.id == item.id }
                                                editedItem?.let{
                                                        it.name = editedName
                                                        it.priority = editedPriority
                                                }

                                        })
                                }
                                else{
                                        TaskList(item = item, onEditClick = {
                                                //finding out which item are we editing and changing it's isEditing to true
                                                sItems = sItems.map{it.copy(isEditing = it.id == item.id)}},
                                                onDeleteClick = {sItems = sItems-item}
                                        )
                                }
                        }
                }
        }
        if(showDialog) {
                AlertDialog(onDismissRequest = { showDialog = false}, 
                        confirmButton = {
                                Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween){

                                        Button(onClick = {
                                                if(taskName.isNotBlank() && taskPriority.isNotBlank())
                                                {
                                                        val newTask = TaskItems(
                                                                id = sItems.size + 1,
                                                                name = taskName,
                                                                priority = taskPriority.toInt()
                                                        )
                                                        sItems = sItems + newTask
                                                        showDialog = false
                                                        taskName = ""
                                                        taskPriority = ""
                                                }
                                        }) {
                                                Text("Add")
                                        }
                                        Button(onClick = { showDialog = false }) {
                                                Text("Cancel")
                                        }
                                        }
                        },
                        title = {Text("Add Tasks")},
                        text = {
                                Column {
                                        OutlinedTextField(value = taskName, onValueChange =
                                        { taskName = it},
                                                singleLine = true,
                                                label = { Text("Task Name")},
                                                modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(16.dp))

                                        OutlinedTextField(value = taskPriority, onValueChange =
                                        { taskPriority = it},
                                                singleLine = true,
                                                label = { Text("Task Priority")},
                                                modifier = Modifier
                                                        .fillMaxWidth())
                                }
                        })

        }
}


@Composable
fun TaskList(
        item :TaskItems,
        onEditClick : () -> Unit,
        onDeleteClick : () -> Unit

){
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(
                                border = BorderStroke(2.dp, DarkGray)),
                        horizontalArrangement = Arrangement.SpaceBetween
        ){
                Text(text = item.name,modifier = Modifier.padding(8.dp))
                Text(text = "Priority : ${item.priority}",modifier = Modifier.padding(8.dp))
                Row(horizontalArrangement = Arrangement.End){
                        IconButton(onClick = onEditClick) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }
                        IconButton(onClick = onDeleteClick) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription =null )
                        }
                }
        }
}

@Composable
fun TaskEditor(item : TaskItems, onEditComplete : (String,Int) -> Unit){
        var editedName by remember{ mutableStateOf(item.name) }
        var editedPriority by remember{ mutableStateOf(item.priority.toString()) }
        var isEditing by remember { mutableStateOf(item.isEditing)}
        Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly)
        {
                Column(modifier = Modifier.padding(8.dp))
                {
                        BasicTextField(value = editedName,
                                onValueChange = { editedName = it },
                                singleLine = true,
                                modifier = Modifier
                                        .wrapContentSize()
                                        .padding(8.dp)
                        )

                        BasicTextField(value = editedPriority.toString(),
                                onValueChange = { editedPriority = it },
                                singleLine = true,
                                modifier = Modifier
                                        .wrapContentSize()
                                        .padding(8.dp)
                        )
                }
                Button(onClick = {
                        isEditing = false
                        onEditComplete(editedName,editedPriority.toIntOrNull() ?:  1)
                }){
                        Text("Save")
                }
        }
}

@Preview(showBackground = true)
@Composable
fun TodoPreview() {
        Todo()
}

