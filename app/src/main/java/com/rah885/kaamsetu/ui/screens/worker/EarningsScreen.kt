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

private data class EarningItem(
    val service: String,
    val customerName: String,
    val amount: String,
    val date: String,
    val status: String
)

@Composable
fun EarningsScreen() {

    val earnings = listOf(
        EarningItem(
            service = "इलेक्ट्रिशियन",
            customerName = "राहुल कुमार",
            amount = "₹800",
            date = "आज",
            status = "भुगतान प्राप्त"
        ),
        EarningItem(
            service = "प्लंबर",
            customerName = "अमित वर्मा",
            amount = "₹600",
            date = "कल",
            status = "भुगतान प्राप्त"
        ),
        EarningItem(
            service = "मैकेनिक",
            customerName = "संजय साहू",
            amount = "₹1,100",
            date = "5 सितंबर",
            status = "भुगतान प्राप्त"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "मेरी कमाई",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "अपने काम और कमाई का हिसाब देखें",
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            EarningsSummaryCard(
                title = "आज की कमाई",
                amount = "₹800",
                icon = "💰",
                modifier = Modifier.weight(1f)
            )

            EarningsSummaryCard(
                title = "इस महीने",
                amount = "₹8,500",
                icon = "📈",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            EarningsSummaryCard(
                title = "कुल कमाई",
                amount = "₹25,000",
                icon = "💵",
                modifier = Modifier.weight(1f)
            )

            EarningsSummaryCard(
                title = "पूरे काम",
                amount = "18",
                icon = "✅",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "कमाई का विवरण",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(earnings) { earning ->

                EarningCard(
                    earning = earning
                )
            }
        }
    }
}

@Composable
private fun EarningsSummaryCard(
    title: String,
    amount: String,
    icon: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = icon,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = amount,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = title,
                modifier = Modifier.padding(top = 4.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun EarningCard(
    earning: EarningItem
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
                    text = "🔧 ${earning.service}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = earning.amount,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "👤 ${earning.customerName}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "📅 ${earning.date}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "✅ ${earning.status}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
