package com.example.jobsapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jobsapp.ApiData.Result
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jobsapp.Viewmodel.JobViewModel
import com.example.jobsapp.R

@Composable
fun JobsScreen(viewModel: JobViewModel = viewModel(), navController: NavHostController) {
    val jobsData = viewModel.jobsData.value
    val isLoading = viewModel.isLoading

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Header with the app title and menu icon
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "JobsApp",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily(Font(R.font.abeezee)),
                    style = TextStyle(fontSize = 24.sp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu options",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Introductory text
            Text(
                "JobsApp, Unlock the door to your next job adventure.",
                color = Color(0xFF39DE8D),
                fontFamily = FontFamily(Font(R.font.abeezee)),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Latest Jobs",
                fontFamily = FontFamily(Font(R.font.abeezee)),
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // If loading, show a CircularProgressIndicator
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(jobsData) { job ->
                        JobCard(job = job, navController = navController)
                    }
                }
            }
        }
    }

    // Fetch the jobs when the composable enters the composition
    LaunchedEffect(Unit) {
        viewModel.fetchJobs()
    }
}


@Composable
fun JobCard(job: Result, navController: NavHostController) {
    val textStyle = TextStyle(
        fontSize = 12.sp, color = Color.Black,
        fontFamily = FontFamily(Font(R.font.abeezee)),
        textAlign = TextAlign.Start
    )

    Card(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                // Navigate to the Job Detail screen with the job details
                navController.navigate("job_detail/${job.id}")
            }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Title: ${job.title}",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.abeezee)),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                )
            )
//            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Location: ${job.primary_details?.Place}", style = textStyle)
            Text(text = "Salary:  ${job.primary_details?.Salary}", style = textStyle)
            Text(text = "Phone Number: ${job.whatsapp_no}", style = textStyle)
        }
    }
}

