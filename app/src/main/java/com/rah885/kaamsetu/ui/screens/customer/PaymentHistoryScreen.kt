package com.rah885.kaamsetu.ui.screens.customer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun PaymentHistoryScreen(
    payments: List<PaymentData>,
    submittedRequests: List<ServiceRequestData> = emptyList(),
    onPaymentClick: (ServiceRequestData) -> Unit = {}
) {

    var selectedPaymentId by remember {
        mutableStateOf<Long?>(null)
    }

    val selectedPayment =
        payments.firstOrNull {
            it.id == selectedPaymentId
        }

    if (selectedPayment != null) {

        BackHandler {
            selectedPaymentId = null
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "💳 Payment Details",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {

                    Text(
                        text = "🔧 सर्विस: ${selectedPayment.service}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "👤 कामगार: ${
                            selectedPayment.workerName.ifBlank {
                                "जानकारी उपलब्ध नहीं"
                            }
                        }",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "💰 भुगतान राशि: ₹${selectedPayment.amount}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "📅 तारीख / समय: ${selectedPayment.dateTime}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "🟢 स्थिति: ${selectedPayment.status}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "🆔 Payment ID: ${selectedPayment.id}",
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "📋 Request ID: ${selectedPayment.requestId}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            OutlinedButton(
                onClick = {
                    selectedPaymentId = null
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("← Payment History पर वापस")
            }
        }

        return
    }

    val paymentRequests = submittedRequests.filter {
        it.status == "कीमत स्वीकार की गई"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "💳 Payments",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "भुगतान और आपकी Payment History",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        /*
         * =========================
         * PAYMENT REQUESTS
         * =========================
         */

        if (paymentRequests.isNotEmpty()) {

            Text(
                text = "💰 भुगतान करने के लिए",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = paymentRequests,
                    key = { request ->
                        request.id
                    }
                ) { request ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            Text(
                                text = "🔧 ${request.service}",
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "👤 कामगार: ${
                                    request.workerName.ifBlank {
                                        "कामगार"
                                    }
                                }"
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

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

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Text(
                                text = "✅ कीमत स्वीकार की गई",
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Button(
                                onClick = {
                                    onPaymentClick(request)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("💳 भुगतान करें")
                            }
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }

        /*
         * =========================
         * PAYMENT HISTORY
         * =========================
         */

        Text(
            text = "📋 Payment History",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        if (payments.isEmpty()) {

            Text(
                text = "अभी कोई payment history नहीं है।",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "जब आपका कोई भुगतान सफल होगा, उसकी पूरी जानकारी यहाँ दिखाई देगी।",
                style = MaterialTheme.typography.bodyMedium
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = payments,
                    key = { payment ->
                        payment.id
                    }
                ) { payment ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedPaymentId = payment.id
                            }
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            Text(
                                text = "🔧 ${payment.service}",
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            if (payment.workerName.isNotBlank()) {

                                Text(
                                    text = "👤 कामगार: ${payment.workerName}"
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )
                            }

                            Text(
                                text = "💰 राशि: ₹${payment.amount}",
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = "📅 ${payment.dateTime}"
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "🟢 ${payment.status}",
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = "Payment ID: ${payment.id}",
                                style = MaterialTheme.typography.bodySmall
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "👉 पूरी जानकारी देखने के लिए यहाँ टैप करें",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
