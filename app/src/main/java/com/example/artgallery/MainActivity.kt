package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryLayout()
                }
            }
        }
    }
}
@Composable
fun DetailsShow(modifier: Modifier = Modifier,
                   details: Boolean,
                   onDetailsShowChanged: (Boolean) -> Unit,) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.details_show),
            modifier = Modifier.padding(bottom =10.dp))
        Switch(modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .padding(top = 20.dp),
            checked = details,
            onCheckedChange = onDetailsShowChanged
        )
    }
}
@Composable
fun Contents(r : Int)
{
    Column() {
        Text(
            text = when (r) {
                0 -> stringResource(R.string.Demon)
                1 -> stringResource(R.string.Luffy)
                else -> stringResource(R.string.Naruto)
            },
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
        )
        Row {
            Text(
                text = when (r) {
                    0 -> stringResource(R.string.A1)
                    1 -> stringResource(R.string.A2)
                    else -> stringResource(R.string.A3)
                },
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = when (r) {
                    0 -> stringResource(R.string.Y1)
                    1 -> stringResource(R.string.Y2)
                    else -> stringResource(R.string.Y3)
                },
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Composable
fun ArtGalleryLayout()
{
    var result by remember { mutableStateOf(0)}
    var details by remember { mutableStateOf(false)}
    val imageResource=when(result)
    {
        0 -> R.drawable.demon
        1 -> R.drawable.luffy
        else -> R.drawable.naruto
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(modifier= Modifier
            .background(Color.Cyan)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)) {
            Text(
                stringResource(R.string.title),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,

            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        DetailsShow(details = details,
            onDetailsShowChanged = { details = it },
            modifier = Modifier.padding(bottom = 32.dp))
        Box(modifier= Modifier
            .border(width = 2.dp, color = Color.Magenta)
            .wrapContentSize(Alignment.Center)
            .padding(20.dp)
            .size(width = 200.dp, height = 400.dp)) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = result.toString(),
                modifier=Modifier.padding(20.dp)
            )
        }
        if(details)
            Contents(result)
        Row (horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()){

            Button(
                onClick = {
                    result = when (result) {
                        0 ->  2
                        else -> (result -1 ) % 3
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .padding(40.dp)
                    .scale(1.3f).size(width = 120.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Blue
                )

            ) {
                Text(
                    text = stringResource(R.string.Prev)
                )
            }
            Button(
                onClick = {
                    result = (result +1 ) % 3

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .padding(40.dp)
                    .scale(1.3f).size(width = 150.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Blue
                )

            ) {
                Text(
                    text = stringResource(R.string.Next)
                )
            }
        }
        }

    }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtGalleryLayout()
    }
}