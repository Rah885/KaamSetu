package com.rah885.kaamsetu.ui.screens.customer

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class RequestItem(
    val service: String,
    val description: String,
    val location: String,
    val dateTime: String,
    val status: String,
    val workerName: String = "",
    val price: String = "",
    val requestId: Long = 0L
)

@Composable
fun MyRequestsScreen(
    submittedRequests: List<ServiceRequestData> = emptyList(),
    onRequestUpdated: (ServiceRequestData) -> Unit = {},
    onPaymentClick: (ServiceRequestData) -> Unit = {}
) {

    val oldRequests = listOf(
        RequestItem(
            service = "इलेक्ट्रिशियन",
            description = "पंखा खराब है, चेक करवाना है",
            location = "नेहरू नगर",
            dateTime = "आज, शाम 5 बजे",
            status = "कामगार खोजा जा रहा है"
        ),
        RequestItem(
            service = "प्लंबर",
            description = "नल से पानी लीक हो रहा है",
            location = "जुनवानी",
            dateTime = "कल, सुबह 10 बजे",
            status = "कामगार उपलब्ध"
        ),
        RequestItem(
            service = "मैकेनिक",
            description = "बाइक स्टार्ट नहीं हो रही",
            location = "स्मृति नगर",
            dateTime = "12 सितंबर, दोपहर 2 बजे",
            status = "रिक्वेस्ट भेजी गई"
        )
    )

    val allRequests = oldRequests + submittedRequests.map { request ->

        RequestItem(
            service = request.service,
            description = request.description,
            location = request.location,
            dateTime = request.dateTime,
            status = request.status,
            workerName = request.workerName,
            price = request.price,
            requestId = request.id
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "मेरी रिक्वेस्ट",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "आपकी भेजी हुई सर्विस रिक्वेस्ट",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = allRequests
            ) { request ->

                RequestCard(
                    request = request,
                    submittedRequest = submittedRequests.firstOrNull {
                        it.id == request.requestId
                    },
                    onRequestUpdated = onRequestUpdated,
                    onPaymentClick = onPaymentClick
                )
            }
        }
    }
}

@Composable
private fun RequestCard(
    request: RequestItem,
    submittedRequest: ServiceRequestData?,
    onRequestUpdated: (ServiceRequestData) -> Unit,
    onPaymentClick: (ServiceRequestData) -> Unit
) {

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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "🔧 ${request.service}",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "📋 ${request.status}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
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

            if (request.price.isNotBlank()) {

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "💰 कामगार की कीमत: ₹${request.price}",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (
                request.status == "कीमत बताई गई" &&
                submittedRequest != null
            ) {

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "क्या आप इस कीमत पर काम करवाना चाहते हैं?",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = {

                        onRequestUpdated(
                            submittedRequest.copy(
                                status = "कीमत स्वीकार की गई"
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("✅ कीमत स्वीकार करें")
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Button(
                    onClick = {

                        onRequestUpdated(
                            submittedRequest.copy(
                                status = "कीमत अस्वीकार की गई"
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("❌ कीमत अस्वीकार करें")
                }
            }

            if (request.status == "कीमत स्वीकार की गई") {

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "💳 कीमत स्वीकार हो गई।",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "अब भुगतान किया जा सकता है।",
                    modifier = Modifier.padding(top = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                if (submittedRequest != null) {

                    Button(
                        onClick = {
                            onPaymentClick(submittedRequest)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("💳 भुगतान करें")
                    }
                }
            }

            if (request.status == "भुगतान सफल") {

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "✅ भुगतान सफल हो गया।",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "💰 भुगतान राशि: ₹${request.price}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
