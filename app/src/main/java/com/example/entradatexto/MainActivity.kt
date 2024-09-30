package com.example.entradatexto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.entradatexto.ui.theme.EntradaTextoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Contenido()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Contenido() {
    var texto by remember { mutableStateOf("") }
    var st by remember { mutableStateOf("") }
    var isr by remember { mutableStateOf("") }
    var iisr: Double=0.0
    Column(
        modifier = Modifier.fillMaxSize(),
        ) {
        Image(painter = painterResource(id = R.drawable.isr2),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                value = texto,
                onValueChange = { texto = it },
                label = { Text("Introduce tu sueldo mensual") }
            )

        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            ElevatedButton(
                onClick = { iisr = Calcular(texto)
                            isr=String.format("%.2f",iisr)
                            st=String.format("%.2f",texto.toDouble()-isr.toDouble())
                }) {
                Text("Calcular")

            }
            Spacer(modifier = Modifier.padding(10.dp))
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text("Borrar")
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
            OutlinedTextField(
                readOnly = true,
                value = isr,
                onValueChange = {isr=it },
                label = { Text("Retencion de ISR") }
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center ){
            OutlinedTextField(
                readOnly = true,
                value = st,
                onValueChange = { st = it },
                label = { Text("Sueldo Neto") }
            )
        }
    }
}

fun Calcular(texto:String):Double{
    var sueldo=texto.toDouble()
    var impuesto : Double = 0.0
    when(sueldo){
        in 0.1 ..746.04 ->impuesto=(sueldo-0.1)*0.0192
        in 746.05 ..6332.05 ->impuesto=((sueldo-746.05)*0.0640)+14.32
        in 6332.06 ..11128.01->impuesto=((sueldo-6332.06)*0.1088)+371.83
        in 11128.02 ..12935.82->impuesto=((sueldo-11128.02)*0.16)+893.63
        in 12935.83 ..15487.71->impuesto=((sueldo-12935.83)*0.1792)+1182.88
        in 15487.72 ..31236.49->impuesto=((sueldo-15487.72)*0.2136)+1640.18
        in 31236.50 ..49233.00->impuesto=((sueldo-31236.50)*0.2352)+5004.12
        in 49233.01 ..93993.90->impuesto=((sueldo-49233.01)*0.3)+9236.89
        in 93993.90 ..125325.20->impuesto=((sueldo-93993.9)*0.32)+22665.17
        in 125325.21 ..375975.61->impuesto=((sueldo-125325.21)*0.34)+32691.18
        else ->impuesto=((sueldo-375975.62)*0.35)+117912.32
    }

    return impuesto
}