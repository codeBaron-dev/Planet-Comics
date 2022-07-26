package com.codebaron.planetcomics.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comics_table")
data class ComicDTO(
    @ColumnInfo(name = "alt") var alt: String,
    @ColumnInfo(name = "day") var day: String,
    @ColumnInfo(name = "img") var img: String,
    @ColumnInfo(name = "link") var link: String,
    @ColumnInfo(name = "month") var month: String,
    @ColumnInfo(name = "news") var news: String,
    @PrimaryKey @ColumnInfo(name = "num") var num: Int,
    @ColumnInfo(name = "safe_title") var safe_title: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "transcript") var transcript: String,
    @ColumnInfo(name = "year") var year: String
)

val dummyComicDTOList = listOf(
    ComicDTO(
        alt = "I got banned from the county fair for handing out Helium-2 balloons. Apparently the instant massive plasma explosions violated some local ordinance or something.",
        day = "22",
        img = "https://imgs.xkcd.com/comics/physics_cost_saving_tips.png",
        link = "",
        month = "7",
        news = "",
        num = 2649,
        safe_title = "Physics Cost-Saving Tips",
        title = "Physics Cost-Saving Tips",
        transcript = "",
        year = "2022"
    ),
    ComicDTO(
        alt = "I got banned from the county fair for handing out Helium-2 balloons. Apparently the instant massive plasma explosions violated some local ordinance or something.",
        day = "22",
        img = "https://imgs.xkcd.com/comics/physics_cost_saving_tips.png",
        link = "",
        month = "7",
        news = "",
        num = 2649,
        safe_title = "Physics Cost-Saving Tips",
        title = "Physics Cost-Saving Tips",
        transcript = "",
        year = "2022"
    ),
    ComicDTO(
        alt = "I got banned from the county fair for handing out Helium-2 balloons. Apparently the instant massive plasma explosions violated some local ordinance or something.",
        day = "22",
        img = "https://imgs.xkcd.com/comics/physics_cost_saving_tips.png",
        link = "",
        month = "7",
        news = "",
        num = 2649,
        safe_title = "Physics Cost-Saving Tips",
        title = "Physics Cost-Saving Tips",
        transcript = "",
        year = "2022"
    ),
    ComicDTO(
        alt = "I got banned from the county fair for handing out Helium-2 balloons. Apparently the instant massive plasma explosions violated some local ordinance or something.",
        day = "22",
        img = "https://imgs.xkcd.com/comics/physics_cost_saving_tips.png",
        link = "",
        month = "7",
        news = "",
        num = 2649,
        safe_title = "Physics Cost-Saving Tips",
        title = "Physics Cost-Saving Tips",
        transcript = "",
        year = "2022"
    )
)