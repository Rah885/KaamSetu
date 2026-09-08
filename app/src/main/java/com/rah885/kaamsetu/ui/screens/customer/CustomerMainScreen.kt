package com.rah885.kaamsetu.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person

private data class CustomerNavItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun CustomerMainScreen() {

    val navItems = listOf(
        CustomerNavItem("Home", Icons.Default.Home),
        CustomerNavItem("Services", Icons.Default.Build),
        CustomerNavItem("Requests", Icons.Default.Assignment),
        CustomerNavItem("Alerts", Icons.Default.Notifications),
        CustomerNavItem("Profile", Icons.Default.Person)
    )

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, item ->

                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(item.title)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            when (selectedIndex) {
                0 -> CustomerHomeContent()
                1 -> CustomerPlaceholderContent("सेवाएँ")
                2 -> CustomerPlaceholderContent("मेरी रिक्वेस्ट")
                3 -> CustomerPlaceholderContent("नोटिफिकेशन")
                4 -> CustomerPlaceholderContent("प्रोफाइल")
            }
        }
    }
}

@Composable
private fun CustomerHomeContent() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "कामसेतु",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "आपके काम का सही साथी",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun CustomerPlaceholderContent(
    title: String
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
   
