package com.example.diceroller

import android.os.Bundle
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}
@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf( 1) }
    var DadoUsuario by remember { mutableStateOf("") }
    var ResultadoDaAposta by remember { mutableStateOf("") }
    val context = LocalContext.current


    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(imageResource), contentDescription = result.toString())



        Button(
            onClick = { result = (1..6).random()
                ResultadoDaAposta = verificar(
                    DadoUsuario.toInt(),
                    result)
                Toast.makeText(
                    context,
                    ResultadoDaAposta,
                    Toast.LENGTH_SHORT).show()
            },
        ) {
            Text(text = stringResource(R.string.roll), fontSize = 24.sp)
        }




        OutlinedTextField(
            value = DadoUsuario,
            onValueChange = {DadoUsuario = it},
            label = { Text("Digite sua tentativa:") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )


    }
}

fun verificar(chute: Int, numCerto: Int): String {
    if(chute in 1..6) {
        if(chute == numCerto) {
            return  "Parabéns, você acertou o número!"
        } else {
            return "Infelizmento você errou!"
        }
    } else {
        return "Digite um número de 1 a 6!"
    }
}