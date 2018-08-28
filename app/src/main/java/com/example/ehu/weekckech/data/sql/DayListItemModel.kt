package com.example.ehu.weekckech.data.sql

import java.util.*

/**
 * 日ページのリストアイテムのDTO
 *
 * @property [isChecked] アイテムがチェックされたか
 * @property [title] アイテムのタイトル
 * @property [detail] アイテムの詳細
 */
data class DayListItemModel (val isChecked:Boolean , val title:String, val detail:String,val weekGroup:String){

}