package com.example.ehu.weekckech.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.ehu.weekckech.data.sql.TaskDataModel


class TaskNotification(val context: Context) {

    private val CHANNEL_ID = "task_notification_id"
    private val NOTIFICATION_TEXT_ID = 1
    private val icon_res = com.example.ehu.weekckech.R.drawable.ic_nofitication

    //    private val CHANNEL_ID = "casareal_chanel"
    // カテゴリー名（通知設定画面に表示される情報）
    private val name = "通知のタイトル的情報を設定"
    // 通知の詳細情報（通知設定画面に表示される情報）
    private val notifyDescription = "この通知の詳細情報を設定します"

    fun notifyTaskBeforeLimit(model: TaskDataModel, beforeLimit: String) {
        // before
        val beforeTitle = "締め切りの${model.notificationTime}前です。"
        val beforeSubText = ""
        val beforeContentText = ""

        // TODO:引数の設計を考える
//        notifyTask(beforeTitle, beforeSubText, beforeContentText)
    }

    fun notifyTaskNowLimit(model: TaskDataModel) {
        if (model.limitTime == null) {
            throw IllegalArgumentException("TaskDataModel#limitTime must not be null")
        }

        // now
        val nowTitle = "締め切り時間が過ぎました！"
        val nowSubText by lazy { "${model.weekGroup}曜日/${model.limitTime}締切" }
        val nowContentText by lazy { model.detail }

        notifyTask(nowTitle, nowSubText, nowContentText)
    }

    fun notifyTaskDailyReport(isComplete: Boolean) {
        // daily
        val dailyReportTitle = "日報です。"
        val dailyReportContentTextForComplete = "タスクをすべて完了しました。"
        val dailyReportContentTextForNotComplete = "一部のタスクを完了しませんでした。"

        if (isComplete) {
            notifyTask(dailyReportTitle, null, dailyReportContentTextForComplete)
        } else {
            notifyTask(dailyReportTitle, null, dailyReportContentTextForNotComplete)
        }
    }

    private fun notifyTask(title: String, subText: String?, contentText: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Channelの取得と生成
        if (manager.getNotificationChannel(CHANNEL_ID) == null) {
            val mChannel = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH)
            mChannel.apply {
                description = notifyDescription
            }
            manager.createNotificationChannel(mChannel)
        }

        manager.notify(NOTIFICATION_TEXT_ID, getNotificationBuilder(title, subText, contentText))
    }

    private fun getNotificationBuilder(title: String, subText: String?, contentText: String): Notification {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(icon_res)
            setContentTitle(title)
            setSubText(subText)
            setContentText(contentText)
        }

        return builder.build()
    }
}