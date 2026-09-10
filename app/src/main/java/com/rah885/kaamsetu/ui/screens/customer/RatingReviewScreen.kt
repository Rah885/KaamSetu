package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RatingReviewScreen(
    workerName: String,
    onSubmit: (rating: Int, review: String) -> Unit,
    onBack: () -> Unit
) {

    var selectedRating by remember {
        mutableIntStateOf(0)
    }

    var review by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {

        Text(
            text = "⭐ रेटिंग और रिव्यू",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "कामगार: $workerName",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Text(
            text = "कामगार को कितने स्टार देना चाहते हैं?",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            for (star in 1..5) {

                Button(
                    onClick = {
                        selectedRating = star
                    }
                ) {
                    Text(
                        text = if (star <= selectedRating) {
                            "★"
                        } else {
                            "☆"
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedTextField(
            value = review,
            onValueChange = {
                if (it.length <= 300) {
                    review = it
                }
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("रिव्यू")
            },
            placeholder = {
                Text("कामगार के काम के बारे में बताएं")
            },
            minLines = 4
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = {
                onSubmit(
                    selectedRating,
                    review.trim()
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedRating in 1..5
        ) {
            Text("⭐ रिव्यू सबमिट करें")
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("वापस जाएं")
        }
    }
}
