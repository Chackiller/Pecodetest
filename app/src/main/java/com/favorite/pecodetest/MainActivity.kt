package com.favorite.pecodetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalConfiguration
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
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

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
                        .background(backgroundGrey),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .size(LocalConfiguration.current.screenHeightDp.dp - 56.dp - 35.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CreateNotification(vm)
                    }

                    BottomToolBar(vm)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CreateNotification(viewModel: MainActivityViewModel) {
    val pagerState = rememberPagerState(viewModel.numberOfFragments.value)
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) { page ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            Surface(
                color = circleGrey,
                modifier = Modifier
                    .size(166.dp)
                    .align(Alignment.Center),
                elevation = 15.dp,
                shape = CircleShape
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(id = R.string.create_not),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(circleGrey)
                            .width(IntrinsicSize.Max)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomToolBar(viewModel: MainActivityViewModel) {
    Box(
        modifier = Modifier
            .padding(start = 29.dp, end = 29.dp, bottom = 35.dp)
            .height(56.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            color = bottomGrey,
            shape = RoundedCornerShape(44.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = viewModel.numberOfFragments.value.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            MinusButton(viewModel)
            PlusButton(viewModel)
        }

    }
}

@Composable
fun MinusButton(viewModel: MainActivityViewModel) {
    viewModel.visible = viewModel.numberOfFragments.value > 0
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .alpha(if (viewModel.visible) 1f else 0f)
            .clickable {
                if (viewModel.visible) viewModel.numberOfFragments.value--
            }) {

        Surface(
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
            color = white,
            elevation = 6.dp
        ) {

        }
        Surface(
            modifier = Modifier
                .width(14.dp)
                .height(3.dp),
            color = bottomGrey
        ) {
        }
    }
}

@Composable
fun PlusButton(viewModel: MainActivityViewModel) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { viewModel.numberOfFragments.value++ }) {
        Surface(
            modifier = Modifier
                .size(56.dp),
            shape = CircleShape,
            color = white,
            elevation = 6.dp
        ) {

        }
        Surface(
            modifier = Modifier
                .width(14.dp)
                .height(3.dp), color = bottomGrey
        ) {
        }

        Surface(
            modifier = Modifier
                .width(3.dp)
                .height(14.dp), color = bottomGrey
        ) {

        }
    }
}