package com.example.jobsapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jobsapp.JobRepository
import com.example.jobsapp.Viewmodel.JobViewModel
import com.example.jobsapp.Viewmodel.JobViewModelFactory
import com.example.jobsapp.NavItem
import com.example.jobsapp.R
import com.example.jobsapp.RetrofitInstance


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()
    val currentRoute = currentDestination?.destination?.route

    val navItemList = listOf(
        NavItem("Jobs", R.drawable.work_unfilled),
        NavItem("Bookmarks", R.drawable.bookmark_unfilled),
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    val showBottomBar = currentRoute == "main_screen"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .height(64.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItemList.forEachIndexed { index, navItem ->
                        val isSelected = selectedIndex == index
                        val backgroundColor = if (isSelected) Color(0xFF39DE8D) else Color.Transparent
                        val contentColor = if (isSelected) Color.Black else Color.Gray

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(backgroundColor)
                                .clickable {
                                    selectedIndex = index
                                    navController.navigate("main_screen") {
                                        popUpTo("main_screen") { inclusive = true }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(id = navItem.selectedIconRes),
                                    contentDescription = navItem.label,
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = ColorFilter.tint(contentColor)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = navItem.label,
                                    color = contentColor,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.abeezee))
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            navController = navController
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavHostController
) {
    val context = LocalContext.current
    val repository = remember { JobRepository(RetrofitInstance.api) }
    val factory = remember { JobViewModelFactory(repository) }
    val jobsViewModel: JobViewModel = viewModel(factory = factory)

    NavHost(
        navController = navController,
        startDestination = "main_screen",
        modifier = modifier
    ) {
        composable("main_screen") {
            when (selectedIndex) {
                0 -> JobsScreen(viewModel = jobsViewModel, navController = navController)
                1 -> BookmarksScreen(navController=navController)
            }
        }
        composable("job_detail/{jobId}") { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId") ?: return@composable
            JobDetailScreen(jobId = jobId, viewModel = jobsViewModel, navController = navController)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
