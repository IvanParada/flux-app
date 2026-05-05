package com.nsqws.flux.features.payment.presentation


import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nsqws.flux.features.payment.PaymentState


@Composable
fun PaymentScreen(
    state: PaymentState
) {
    val typography = MaterialTheme.typography

    Scaffold { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = maxWidth * 0.05f)
            ){
                Text("PAYMENTS SCREEN")
            }
        }
    }
}