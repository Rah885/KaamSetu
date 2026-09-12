package com.rah885.kaamsetu.ui.screens.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class PaymentData(
    val id: Long = System.currentTimeMillis(),
    val requestId: Long,
    val service: String,
    val workerName: String,
    val amount: String,
    val dateTime: String,
    val status: String = "भुगतान सफल"
)

@Composable
fun PaymentScreen(
    request: ServiceRequestData,
    onPaymentSuccess: (PaymentData) -> Unit,
    onBack: () -> Unit
) {

    BackHandler {
        onBack()
    }

    var selectedMethod by remember {
        mutableStateOf("UPI")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "💳 भुगतान",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = "🔧 सर्विस: ${request.service}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                if (request.workerName.isNotBlank()) {

                    Text(
                        text = "👤 कामगार: ${request.workerName}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )
                }

                Text(
                    text = "💰 भुगतान राशि: ₹${request.price}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "📍 ${request.location}"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "भुगतान का तरीका चुनें",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        PaymentMethodButton(
            title = "📱 UPI",
            selected = selectedMethod == "UPI",
            onClick = {
                selectedMethod = "UPI"
            }
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        PaymentMethodButton(
            title = "📷 QR Code",
            selected = selectedMethod == "QR",
            onClick = {
                selectedMethod = "QR"
            }
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        PaymentMethodButton(
            title = "💳 Card",
            selected = selectedMethod == "CARD",
            onClick = {
                selectedMethod = "CARD"
            }
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        PaymentMethodButton(
            title = "🏦 Net Banking",
            selected = selectedMethod == "NETBANKING",
            onClick = {
                selectedMethod = "NETBANKING"
            }
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "चयनित भुगतान तरीका: $selectedMethod",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {

                val payment = PaymentData(
                    requestId = request.id,
                    service = request.service,
                    workerName = request.workerName,
                    amount = request.price,
                    dateTime = request.dateTime,
                    status = "भुगतान सफल"
                )

                onPaymentSuccess(payment)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("💳 भुगतान करें")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "ℹ️ अभी भुगतान डेमो मोड में है। चुने गए payment method के आधार पर अगला payment step बाद में जोड़ा जाएगा।",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun PaymentMethodButton(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    if (selected) {

        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(title)
        }

    } else {

        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(title)
        }
    }
}
