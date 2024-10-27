package com.example.composetask.data.local

import com.example.composetask.R
import com.example.composetask.domain.model.Category
import com.example.composetask.domain.model.ServiceItem

object ListServiceItems {
    val serviceItems = listOf(
        ServiceItem(
            id = 1,
            name = "Luxury Apartment",
            imageRes = R.drawable.img4,
            category = Category(id = 201, categoryName = "Apartments")
        ),
        ServiceItem(
            id = 2,
            name = "Modern Villa",
            imageRes = R.drawable.img5,
            category = Category(id = 202, categoryName = "Villas")
        ),
        ServiceItem(
            id = 3,
            name = "Cozy Studio",
            imageRes = R.drawable.img3,
            category = Category(id = 201, categoryName = "Apartments")
        ),
        ServiceItem(
            id = 4,
            name = "Beachfront Villa",
            imageRes = R.drawable.img4,
            category = Category(id = 202, categoryName = "Villas")
        ),
        ServiceItem(
            id = 5,
            name = "City Center Apartment",
            imageRes = R.drawable.img5,
            category = Category(id = 201, categoryName = "Apartments")
        ),
        ServiceItem(
            id = 6,
            name = "Contemporary Villa",
            imageRes = R.drawable.img3,
            category = Category(id = 202, categoryName = "Villas")
        ),
        ServiceItem(
            id = 7,
            name = "Penthouse Suite",
            imageRes = R.drawable.img4,
            category = Category(id = 201, categoryName = "Apartments")
        ),
        ServiceItem(
            id = 8,
            name = "Mediterranean Villa",
            imageRes = R.drawable.img5,
            category = Category(id = 202, categoryName = "Villas")
        ),
        ServiceItem(
            id = 9,
            name = "Affordable Apartment",
            imageRes = R.drawable.img3,
            category = Category(id = 201, categoryName = "Apartments")
        ),
        ServiceItem(
            id = 10,
            name = "Rustic Villa",
            imageRes = R.drawable.img4,
            category = Category(id = 202, categoryName = "Villas")
        ),
        ServiceItem(
            id = 11,
            name = "Downtown Studio",
            imageRes = R.drawable.img5,
            category = Category(id = 201, categoryName = "Apartments")
        ),
        ServiceItem(
            id = 12,
            name = "Luxury Coastal Villa",
            imageRes = R.drawable.img3,
            category = Category(id = 202, categoryName = "Villas")
        )
    )
}
