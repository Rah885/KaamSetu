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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class JobItem(
    val customerName: String,
    val service: String,
    val description: String,
    val location: String,
    val dateTime: String,
    val status: String
)

@Composable
fun MyJobsScreen() {

    val jobs = listOf(
        JobItem(
            customerName = "राहुल कुमार",
            service = "इलेक्ट्रिशियन",
            description = "पंखे की वायरिंग और खराबी चेक करनी है",
            location = "नेहरू नगर",
            dateTime = "आज, शाम 5 बजे",
            status = "चल रहा है"
        ),
        JobItem(
            customerName = "अमित वर्मा",
            service = "प्लंबर",
            description = "बाथरूम के नल की लीकेज ठीक करनी है",
            location = "जुनवानी",
            dateTime = "कल, सुबह 10 बजे",
            status = "स्वीकार किया गया"
        ),
        JobItem(
            customerName = "संजय साहू",
            service = "मैकेनिक",
            description = "बाइक की सर्विस और इंजन चेक करना है",
            location = "स्मृति नगर",
            dateTime = "12 सितंबर, दोपहर 2 बजे",
            status = "पूरा हुआ"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "मेरे काम",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "आपके स्वीकार किए गए और पूरे किए गए काम",
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

            items(jobs) { job ->

                JobCard(
                    job = job
                )
            }
        }
    }
}

@Composable
private fun JobCard(
    job: JobItem
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "👤 ${job.customerName}",
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "📋 ${job.status}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "🔧 ${job.service}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = job.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "📍 ${job.location}"
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "🕐 ${job.dateTime}"
            )
        }
    }
}
