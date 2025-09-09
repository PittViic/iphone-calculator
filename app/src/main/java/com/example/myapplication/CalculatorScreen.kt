package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.lifecycle.viewmodel.compose.viewModel

// Cores para os botões
val sideFunctions = Color(0xFFFF9F0A)
val superiorFunctions = Color(0xFFA5A5A5)
val numbersColors = Color(0xFF333333)

@Composable
fun CalculatorScreen() {
    val viewModel: CalculatorViewModel = viewModel()
    val state = viewModel.state
    val buttonSpacing = 12.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            // --- DISPLAY ---
            Text(
                text = state,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontWeight = FontWeight.Light,
                fontSize = 80.sp,
                color = Color.White,
                maxLines = 1
            )

            // --- LINHA 1 ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
                CalculatorButton(symbol = "AC", color = superiorFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Clear) }
                CalculatorButton(symbol = "+/-", color = superiorFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Clear) }
                CalculatorButton(symbol = "%", color = superiorFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Clear) }
                CalculatorButton(symbol = "/", color = sideFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Operator("/")) }
            }

            // --- LINHA 2 ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
                CalculatorButton(symbol = "7", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("7")) }
                CalculatorButton(symbol = "8", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("8")) }
                CalculatorButton(symbol = "9", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("9")) }
                CalculatorButton(symbol = "x", color = sideFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Operator("*")) }
            }

            // --- LINHA 3 ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
                CalculatorButton(symbol = "4", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("4")) }
                CalculatorButton(symbol = "5", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("5")) }
                CalculatorButton(symbol = "6", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("6")) }
                CalculatorButton(symbol = "-", color = sideFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Operator("-")) }
            }

            // --- LINHA 4 ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
                CalculatorButton(symbol = "1", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("1")) }
                CalculatorButton(symbol = "2", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("2")) }
                CalculatorButton(symbol = "3", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Number("3")) }
                CalculatorButton(symbol = "+", color = sideFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Operator("+")) }
            }

            // --- LINHA 5 ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(buttonSpacing)) {
                CalculatorButton(symbol = "0", modifier = Modifier.weight(2f)) { viewModel.onAction(CalculatorAction.Number("0")) }
                CalculatorButton(symbol = ",", modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Decimal) }
                CalculatorButton(symbol = "=", color = sideFunctions, modifier = Modifier.weight(1f)) { viewModel.onAction(CalculatorAction.Calculate) }
            }
        }
    }
}


@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    color: Color = numbersColors,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .background(color)
            .clickable { onClick() }
            .aspectRatio(if (symbol == "0") 2f else 1f)
    ) {
        Text(
            text = symbol,
            fontSize = 32.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    MyApplicationTheme {
        CalculatorScreen()
    }
}