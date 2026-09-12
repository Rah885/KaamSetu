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

    var showPaymentDetails by remember {
        mutableStateOf(false)
    }

    var paymentCompleted by remember {
        mutableStateOf(false)
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

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "🕐 ${request.dateTime}"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        /*
         * Payment successful होने के बाद
         * पूरा success result दिखाएँ।
         */
        if (paymentCompleted) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Text(
                        text = "✅ भुगतान सफल",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "आपका भुगतान सफलतापूर्वक दर्ज हो गया है।",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "💰 राशि: ₹${request.price}"
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "🔧 सर्विस: ${request.service}"
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "👤 कामगार: ${request.workerName}"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← वापस जाएँ")
            }

        } else if (showPaymentDetails) {

            /*
             * Pay Now दबाने के बाद
             * confirmation screen।
             */
            Text(
                text = "💳 भुगतान की पुष्टि",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(12.dp)
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
                        text = "आप इस भुगतान की पुष्टि करने वाले हैं।",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "🔧 सर्विस: ${request.service}"
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "👤 कामगार: ${request.workerName}"
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "💰 कुल भुगतान: ₹${request.price}",
                        style = MaterialTheme.typography.titleLarge
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
                modifier = Modifier.height(20.dp)
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

                    paymentCompleted = true

                    onPaymentSuccess(payment)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("✅ भुगतान की पुष्टि करें")
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            OutlinedButton(
                onClick = {
                    showPaymentDetails = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← वापस")
            }

        } else {

            Text(
                text = "भुगतान करने के लिए नीचे दिए गए बटन पर क्लिक करें।",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    showPaymentDetails = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("💳 Pay Now")
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "ℹ️ अगला चरण: payment method और real UPI/QR payment जोड़ा जाएगा।",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
