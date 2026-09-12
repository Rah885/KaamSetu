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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rah885.kaamsetu.data.database.AppDataEntity
import com.rah885.kaamsetu.data.database.KaamSetuDatabase

private const val CUSTOMER_ID_KEY = "customer_account_id"

@Composable
fun CustomerAccountScreen(
    customerId: String,
    name: String,
    mobile: String,
    onBack: () -> Unit
) {

    BackHandler {
        onBack()
    }

    val context = LocalContext.current

    val database = remember {
        KaamSetuDatabase.getInstance(context)
    }

    val dao = remember {
        database.appDataDao()
    }

    var permanentCustomerId by remember {
        mutableStateOf(customerId)
    }

    var accountLoaded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        val savedAccountId = dao.get(CUSTOMER_ID_KEY)

        if (savedAccountId != null &&
            savedAccountId.value.isNotBlank()
        ) {

            permanentCustomerId = savedAccountId.value

        } else {

            val newCustomerId =
                if (customerId.isNotBlank()) {
                    customerId
                } else {
                    "KS-C-${System.currentTimeMillis()}"
                }

            permanentCustomerId = newCustomerId

            dao.save(
                AppDataEntity(
                    key = CUSTOMER_ID_KEY,
                    value = newCustomerId
                )
            )
        }

        accountLoaded = true
    }

    if (!accountLoaded) {
        return
    }

    val profileComplete =
        name.isNotBlank() &&
                mobile.length == 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "👤 Customer Account",
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
                    text = "🆔 Customer ID",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = permanentCustomerId,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text = "👤 नाम: ${
                        name.ifBlank {
                            "अभी सेट नहीं है"
                        }
                    }",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "📱 मोबाइल: ${
                        mobile.ifBlank {
                            "अभी सेट नहीं है"
                        }
                    }",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text = "🏷️ Account Type: Customer",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
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
                    text = "📋 Account Status",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = if (profileComplete) {
                        "✅ Profile information complete"
                    } else {
                        "⚠️ Profile information incomplete"
                    },
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "💳 Payment Account: Setup pending",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "💰 Commission: अभी लागू नहीं है",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "Payment account और real payment setup बाद में gateway integration के समय जोड़ा जाएगा।",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("← वापस प्रोफाइल पर")
        }
    }
}
