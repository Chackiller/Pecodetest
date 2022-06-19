package com.favorite.pecodetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.favorite.pecodetest.ui.theme.*
import com.favorite.pecodetest.view.MainActivityViewModel
import com.favorite.pecodetest.view.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    private lateinit var vm: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setContent {
            PecodeTestTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundGrey), verticalArrangement = Arrangement.Bottom
                ) {
                    BottomToolBar(vm)
                }
            }
        }
    }
}

@Composable
fun CreateNotification() {
    Text(
        text = stringResource(id = R.string.create_not),
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(circleGrey)
            .size(166.dp),
    )
}

@Composable
fun BottomToolBar(viewModel: MainActivityViewModel) {
    Box(modifier = Modifier
        .padding(start = 29.dp, end = 29.dp, bottom = 35.dp)
        .height(56.dp),
        contentAlignment = Alignment.CenterStart) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            color = bottomGrey,
            shape = RoundedCornerShape(44.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = viewModel.numberOfFragments.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            MinusButton(viewModel)
            PlusButton(viewModel)
        }

    }
}

@Composable
fun MinusButton(viewModel: MainActivityViewModel) {
    Box(contentAlignment = Alignment.Center) {
        Surface(modifier = Modifier.size(56.dp), shape = CircleShape, color = white, elevation = 10.dp) {

        }
        Surface(modifier = Modifier
            .width(14.dp)
            .height(3.dp), color = bottomGrey) {
        }
    }
}

@Composable
fun PlusButton(viewModel: MainActivityViewModel) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable { viewModel.numberOfFragments++ }) {
        Surface(modifier = Modifier.size(56.dp), shape = CircleShape, color = white, elevation = 10.dp) {

        }
        Surface(modifier = Modifier
            .width(14.dp)
            .height(3.dp), color = bottomGrey) {
        }

        Surface(modifier = Modifier
            .width(3.dp)
            .height(14.dp), color = bottomGrey) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PecodeTestTheme {
        CreateNotification()

    }
}