package com.example.ehu.weekckech.data.sql

import java.util.*

/**
 * 日ページのリストアイテムのDTO
 *
 * @property [isChecked] アイテムがチェックされたか
 * @property [title] アイテムのタイトル
 * @property [detail] アイテムの詳細
 */
data class DayListItemModel (val isChecked:Boolean, val title:String, val detail:String,
                             var deadline:Date,val notice:CharArray,val weekGroup:String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DayListItemModel

        if (isChecked != other.isChecked) return false
        if (title != other.title) return false
        if (detail != other.detail) return false
        if (deadline != other.deadline) return false
        if (!Arrays.equals(notice, other.notice)) return false
        if (weekGroup != other.weekGroup) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isChecked.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + detail.hashCode()
        result = 31 * result + deadline.hashCode()
        result = 31 * result + Arrays.hashCode(notice)
        result = 31 * result + weekGroup.hashCode()
        return result
    }
}