package com.example.sports.data

import com.example.sports.R
import com.example.sports.model.Sport

/**
 * Sports data
 */
object LocalSportsDataProvider{
    val defaultSport = getSportsData()[0]

    fun getSportsData(): List<Sport> {
        return listOf(
            Sport(
                id = 1,
                titleResourceId = R.string.baseball,
                subtitleResourceId = R.string.baseball_subtitle,
                playerCount = 9,
                olympic = true,
                imageResourceId = R.drawable.ic_baseball_square,
                sportsImageBanner = R.drawable.ic_baseball_banner,
                sportDetails = R.string.baseball_detail
            ),
            Sport(
                id = 2,
                titleResourceId = R.string.badminton,
                subtitleResourceId = R.string.badminton_subtitle,
                playerCount = 1,
                olympic = true,
                imageResourceId = R.drawable.ic_badminton_square,
                sportsImageBanner = R.drawable.ic_badminton_banner,
                sportDetails = R.string.badminton_detail
            ),
            Sport(
                id = 3,
                titleResourceId = R.string.basketball,
                subtitleResourceId = R.string.basketball_subtitle,
                playerCount = 5,
                olympic = true,
                imageResourceId = R.drawable.ic_basketball_square,
                sportsImageBanner = R.drawable.ic_basketball_banner,
                sportDetails = R.string.basketball_detail
            ),
            Sport(
                id = 4,
                titleResourceId = R.string.bowling,
                subtitleResourceId = R.string.bowling_subtitle,
                playerCount = 1,
                olympic = false,
                imageResourceId = R.drawable.ic_bowling_square,
                sportsImageBanner = R.drawable.ic_bowling_banner,
                sportDetails = R.string.bowling_detail
            ),
            Sport(
                id = 5,
                titleResourceId = R.string.cycling,
                subtitleResourceId = R.string.cycling_subtitle,
                playerCount = 1,
                olympic = true,
                imageResourceId = R.drawable.ic_cycling_square,
                sportsImageBanner = R.drawable.ic_cycling_banner,
                sportDetails = R.string.cycling_detail
            ),
            Sport(
                id = 6,
                titleResourceId = R.string.golf,
                subtitleResourceId = R.string.golf_subtitle,
                playerCount = 1,
                olympic = false,
                imageResourceId = R.drawable.ic_golf_square,
                sportsImageBanner = R.drawable.ic_golf_banner,
                sportDetails = R.string.golf_detail
            ),
            Sport(
                id = 7,
                titleResourceId = R.string.running,
                subtitleResourceId = R.string.running_subtitle,
                playerCount = 1,
                olympic = true,
                imageResourceId = R.drawable.ic_running_square,
                sportsImageBanner = R.drawable.ic_running_banner,
                sportDetails = R.string.running_detail
            ),
            Sport(
                id = 8,
                titleResourceId = R.string.soccer,
                subtitleResourceId = R.string.soccer_subtitle,
                playerCount = 11,
                olympic = true,
                imageResourceId = R.drawable.ic_soccer_square,
                sportsImageBanner = R.drawable.ic_soccer_banner,
                sportDetails = R.string.soccer_detail
            ),
            Sport(
                id = 9,
                titleResourceId = R.string.swimming,
                subtitleResourceId = R.string.swimming_subtitle,
                playerCount = 1,
                olympic = true,
                imageResourceId = R.drawable.ic_swimming_square,
                sportsImageBanner = R.drawable.ic_swimming_banner,
                sportDetails = R.string.swimming_detail
            ),
            Sport(
                id = 10,
                titleResourceId = R.string.table_tennis,
                subtitleResourceId = R.string.table_tennis_subtitle,
                playerCount = 1,
                olympic = true,
                imageResourceId = R.drawable.ic_table_tennis_square,
                sportsImageBanner = R.drawable.ic_table_tennis_banner,
                sportDetails = R.string.table_tennis_detail
            ),
            Sport(
                id = 11,
                titleResourceId = R.string.tennis,
                subtitleResourceId = R.string.tennis_subtitle,
                playerCount = 1,
                olympic = true,
                imageResourceId = R.drawable.ic_tennis_square,
                sportsImageBanner = R.drawable.ic_tennis_banner,
                sportDetails = R.string.tennis_detail
            )
        )
    }
}
