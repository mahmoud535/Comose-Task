package com.example.composetask.data.local

import com.example.composetask.R
import com.example.composetask.domain.model.HomeDataItems

object ListData {
    val listItems = listOf(
        HomeDataItems(
            personImage = R.drawable.person3,
            postImage = R.drawable.img2,
            personName = "Fady",
            date = "12:30 PM",
        ),
        HomeDataItems(
            personImage = R.drawable.person2,
            postImage = R.drawable.img1,
            personName = "Mina",
            date = "Yesterday at 5:00 PM",
        ),
        HomeDataItems(
            personImage = R.drawable.person1,
            postImage = R.drawable.img3,
            personName = "Sarah",
            date = "Today at 9:00 AM",
        )

    )

}