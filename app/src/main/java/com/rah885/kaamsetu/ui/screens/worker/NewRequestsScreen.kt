package com.rah885.kaamsetu.ui.screens.worker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rah885.kaamsetu.ui.screens.customer.ServiceRequestData

private const val MIN_PRICE = 100
private const val MAX_PRICE = 5000

@Composable
fun NewRequestsScreen(
    serviceRequests: List<ServiceRequestData>,
    onRequestUpdated: (ServiceRequestData) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "नई सर्विस रिक्वेस्ट",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "रिक्वेस्ट स्वीकार करें और ग्राहक को उचित कीमत बताएं।",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (serviceRequests.isEmpty()) {

            Text(
                text = "अभी कोई नई सर्विस रिक्वेस्ट नहीं है।",
                style = MaterialTheme.typography.bodyLarge
            )

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = serviceRequests,
                    key = { request -> request.id }
                ) { request ->

                    RequestCard(
                        request = request,
                        onRequestUpdated = onRequestUpdated
                    )
                }
            }
        }
    }
}

@Composable
private fun RequestCard(
    request: ServiceRequestData,
    onRequestUpdated: (ServiceRequestData) -> Unit
) {

    var priceInput by rememberSaveable(request.id) {
        mutableStateOf("")
    }

    var priceError by rememberSaveable(request.id) {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "👤 ${request.customerName}",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "📋 ${request.service}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = request.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "📍 ${request.location}"
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "🕐 ${request.dateTime}"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "📌 स्थिति: ${request.status}",
                style = MaterialTheme.typography.bodyMedium
            )

            if (request.price.isNotBlank()) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "💰 तय कीमत: ₹${request.price}",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            when (request.status) {

                "रिक्वेस्ट भेजी गई" -> {

                    Button(
                        onClick = {

                            onRequestUpdated(
                                request.copy(
                                    status = "काम स्वीकार किया गया"
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("काम स्वीकार करें")
                    }
                }

                "काम स्वीकार किया गया",
                "कीमत अस्वीकार की गई" -> {

                    Text(
                        text = "💰 ग्राहक को अपनी कीमत बताएं",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "कीमत ₹$MIN_PRICE से ₹$MAX_PRICE के बीच रखें।",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    OutlinedTextField(
                        value = priceInput,
                        onValueChange = {
                            if (
                                it.length <= 5 &&
                                it.all { char -> char.isDigit() }
                            ) {
                                priceInput = it
                                priceError = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("काम की कीमत (₹)")
                        },
                        placeholder = {
                            Text("जैसे 500")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true
                    )

                    if (priceError.isNotBlank()) {

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text = priceError,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Button(
                        onClick = {

                            val price = priceInput.toIntOrNull()

                            when {
                                price == null -> {
                                    priceError = "कृपया कीमत दर्ज करें।"
                                }

                                price < MIN_PRICE -> {
                                    priceError =
                                        "कम से कम ₹$MIN_PRICE कीमत रखें।"
                                }

                                price > MAX_PRICE -> {
                                    priceError =
                                        "अधिकतम ₹$MAX_PRICE कीमत रख सकते हैं।"
                                }

                                else -> {

                                    onRequestUpdated(
                                        request.copy(
                                            price = price.toString(),
                                            status = "कीमत बताई गई"
                                        )
                                    )

                                    priceInput = ""
                                    priceError = ""
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("💰 कीमत भेजें")
                    }
                }

                "कीमत बताई गई" -> {

                    Text(
                        text = "💰 ग्राहक के जवाब का इंतजार है।",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "बताई गई कीमत: ₹${request.price}"
                    )
                }

                "कीमत स्वीकार की गई" -> {

                    Text(
                        text = "✅ ग्राहक ने कीमत स्वीकार कर ली है।",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "💰 तय कीमत: ₹${request.price}"
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "💳 ग्राहक का भुगतान पूरा होने का इंतजार है।"
                    )
                }

                "भुगतान सफल" -> {

                    Text(
                        text = "💳 ग्राहक का भुगतान सफल हो गया है।",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "अब आप काम शुरू कर सकते हैं।"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {

                            onRequestUpdated(
                                request.copy(
                                    status = "काम शुरू किया"
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("▶️ काम शुरू करें")
                    }
                }

                "काम शुरू किया" -> {

                    Text(
                        text = "🟡 काम शुरू हो गया है।",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "कामगार ने काम शुरू कर दिया है।"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {

                            onRequestUpdated(
                                request.copy(
                                    status = "काम चल रहा है"
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("🔧 काम चल रहा है")
                    }
                }

                "काम चल रहा है" -> {

                    Text(
                        text = "🔧 काम चल रहा है।",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "काम पूरा होने के बाद नीचे बटन दबाएं।"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {

                            onRequestUpdated(
                                request.copy(
                                    status = "काम पूरा हुआ"
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("✅ काम पूरा करें")
                    }
                }

                "काम पूरा हुआ" -> {

                    Text(
                        text = "✅ काम पूरा हो गया है।",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "अब ग्राहक रेटिंग और रिव्यू दे सकता है।"
                    )
                }

                else -> {

                    Text(
                        text = "ℹ️ ${request.status}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
