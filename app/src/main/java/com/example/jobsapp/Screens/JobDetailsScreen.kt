package com.example.jobsapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jobsapp.Viewmodel.JobViewModel
import com.example.jobsapp.R

@Composable
fun JobDetailScreen(jobId: String, viewModel: JobViewModel, navController: NavController) {
    val jobDetails =
        viewModel.getJobById(jobId)
    val textStyle = TextStyle(
        fontSize = 16.sp, color = Color.Black,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily(Font(R.font.abeezee)),
        textAlign = TextAlign.Start
    )
    var isSelected by remember { mutableStateOf(false) }

    val iconRes = if (isSelected) R.drawable.bookmarks else R.drawable.bookmark_unfilled
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color =  Color(0xFFFCF9F9))
                .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.height(15.dp))

            jobDetails?.let {
                Row {
                    Text(
                        "${it.company_name}", style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.abeezee)),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "Toggle Icon",
                        modifier = Modifier
                            .size(25.dp)
                            .clickable { isSelected = !isSelected },
                    )

                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "${it.title}", style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.abeezee)),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        tint = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("${it.primary_details?.Place}", style = textStyle)

                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.salary),
                        contentDescription = "Example Image",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("${it.primary_details?.Salary}", style = textStyle)

                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "location",
                        tint = Color.Black,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("${it.whatsapp_no}", style = textStyle)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text("Job Description", style = textStyle)
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = """
        We are seeking dedicated and motivated individuals to join our team. 
        The role involves assisting with daily operations, coordinating with internal teams, 
        and contributing to the overall growth of the organization.

        Responsibilities:
        • Perform assigned tasks efficiently and on time  
        • Collaborate with team members and management  
        • Maintain accurate records and documentation  
        • Ensure smooth communication and workflow  

        Qualifications:
        • Strong communication and interpersonal skills  
        • Ability to work independently and in a team  
        • Basic understanding of job responsibilities (training will be provided)  
        • Willingness to learn and grow with the company  
    """.trimIndent(),
                    style = TextStyle(
                        fontSize = 14.sp, color = Color.Black,
                        fontWeight = FontWeight.ExtraLight,
                        fontFamily = FontFamily(Font(R.font.abeezee)),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        // You can add intent or action here like open WhatsApp, apply form, etc.
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp), shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF39DE8D))

                ) {
                    Text("Apply ", style = textStyle)
                }


                // Add other job details here
            }
        }
    }

}
