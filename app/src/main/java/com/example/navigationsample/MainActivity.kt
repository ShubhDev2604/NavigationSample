package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = "firstscreen"){
        //This is not the FirstScreen we have this is the route firstscreen we have developed after this comment.
        composable("firstscreen"){
            FirstScreen{age,name-> //what you are passing
                navController.navigate("secondscreen/$age/$name")
                //This is the code which is letting us navigate
                //This will make sure what to happen when the the firstscreen runs
                //This is the code that the lambda function is executing in FirstScreen
            }
        }
        composable(route = "secondscreen/{age}/{name}"){
            val age = it.arguments?.getString("age")?.toInt() ?:0
            //val age = it.arguments?.getInt("age")?.toInt() ?:0
            val name = it.arguments?.getString("name")?:"no name"
            SecondScreen(age,name){
                weight->
                navController.navigate("thirdscreen/$weight")
            }

        }
        composable("thirdscreen/{weight}"){
            val weight = it.arguments?.getString("weight")?:"no weight"
            ThirdScreen(weight){
                navController.navigate("firstscreen")
            }
        }
    }
}
//route is like the address of the composable we want to display as a particular screen
//in startDestination, we write the composable we want to start the app with
//whenever button is clicked, the lambda function is called, and we use the navController.navigate to head to the screen we have written.
//compasable is defined in two ways, first is the one we write @Composable and second is using route
