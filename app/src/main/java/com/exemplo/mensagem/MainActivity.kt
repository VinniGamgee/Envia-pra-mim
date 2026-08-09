package com.exemplo.mensagem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.exemplo.mensagem.ui.theme.MensagemTheme

data class Message(val text: String, val isMine: Boolean)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MensagemTheme {
                ChatScreen()
            }
        }
    }
}

@Composable
fun ChatScreen() {
    var messages by remember {
        mutableStateOf(
            listOf(
                Message("Oi! Tudo bem?", false),
                Message("Tudo sim, e você?", true),
                Message("Beleza. Esse é o chat de teste 👋", false)
            )
        )
    }
    var input by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Header
        Surface(tonalElevation = 2.dp) {
            Text(
                "Chat",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Messages
        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(messages) { msg ->
                MessageBubble(msg)
            }
        }

        // Input
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Mensagem...") },
                singleLine = true
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        messages = messages + Message(input.trim(), true)
                        input = ""
                    }
                }
            ) {
                Text("Enviar")
            }
        }
    }
}

@Composable
fun MessageBubble(msg: Message) {
    val bg = if (msg.isMine) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val align = if (msg.isMine) Alignment.CenterEnd else Alignment.CenterStart

    Box(Modifier.fillMaxWidth(), contentAlignment = align) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = bg,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                msg.text,
                modifier = Modifier.padding(12.dp),
                color = if (msg.isMine) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
