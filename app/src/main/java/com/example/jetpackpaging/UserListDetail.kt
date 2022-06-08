package com.example.jetpackpaging

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.jetpackpaging.model.UserData
import com.example.jetpackpaging.ui.theme.Gray
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.flow.Flow

@Composable
fun UserListDetail(user: Flow<PagingData<UserData>>) {
    val userListItem: LazyPagingItems<UserData> = user.collectAsLazyPagingItems()
    val isVisible = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn {
            items(userListItem) {
                it?.let {
                    UserLists(it)
                    isVisible.value = false
                }
            }
        }
        if (isVisible.value) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun UserLists(userData: UserData) {
    Card(
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 5.dp)
            .fillMaxWidth().background(Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = 12.dp
    ) {
        Row (
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                val image = rememberCoilPainter(
                    request = userData.avatar,
                    fadeIn = true
                )
                Image(
                    painter = image, contentDescription = "Image",
                    modifier = Modifier.clip(
                        CircleShape,
                    ),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Name: ${userData.first_name} ${userData.last_name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = "Email: ${userData.email}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}