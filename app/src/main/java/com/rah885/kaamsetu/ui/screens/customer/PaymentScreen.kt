package com.rah885.kaamsetu.ui.screens.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            text = "भुगतान पूरा करने के लिए नीचे दिए गए बटन पर क्लिक करें।",
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
            text = "ℹ️ यह अभी डेमो भुगतान है। असली payment gateway अगले चरण में जोड़ा जाएगा।",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
