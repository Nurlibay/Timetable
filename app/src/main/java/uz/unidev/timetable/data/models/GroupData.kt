package uz.unidev.timetable.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *  Created by Nurlibay Koshkinbaev on 26/10/2022 14:14
 */

@Parcelize
data class GroupData(
    var id: String = "",
    var number: Int = 0,
    var name: String = "",
    var smena: String = ""
): Parcelable