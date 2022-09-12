package com.baykal.edumyclient.ui.screen.meetingSection.meetingSession

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.baykal.edumyclient.base.preference.EdumySession
import com.baykal.edumyclient.base.preference.withUser
import com.baykal.edumyclient.data.model.meeting.response.Meeting
import com.facebook.react.modules.core.PermissionListener
import dagger.hilt.android.AndroidEntryPoint
import org.jitsi.meet.sdk.*
import javax.inject.Inject

@AndroidEntryPoint
class MeetingActivity : FragmentActivity(), JitsiMeetActivityInterface {

    @Inject
    lateinit var session: EdumySession

    lateinit var localBroadcastManager: LocalBroadcastManager
    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)

        val item = intent.getSerializableExtra("meeting") as Meeting?

        item?.let { meeting ->
            session.withUser {
                setupReceiver()

                val view = JitsiMeetView(this)
                val subject = meeting.classroom?.lesson
                val user = JitsiMeetUserInfo()
                user.displayName = it.name
                user.email = it.mail

                val options = JitsiMeetConferenceOptions.Builder()
                    .setRoom(meeting.id + meeting.classroom?.id + meeting.duration)
                    .setSubject(subject)
                    .setUserInfo(user)
                    .setAudioMuted(false)
                    .setVideoMuted(false)
                    .setFeatureFlag("pip.enabled",false)
                    .setFeatureFlag("add-people.enabled",false)
                    .setFeatureFlag("invite.enabled",false)
                    .setFeatureFlag("kick-out.enabled",false)
                    .build()
                view.join(options)
                setContentView(view)
            }
        } ?: run {
            finish()
        }
    }

    private fun setupReceiver() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        receiver = object : BroadcastReceiver(this) {
            override fun onReceive(context: Context, intent: Intent) {
                intent.action?.let {
                    when (it) {
                        BroadcastEvent.Type.CONFERENCE_JOINED.action -> {}
                        BroadcastEvent.Type.CONFERENCE_TERMINATED.action -> {
                            finish()
                        }
                        BroadcastEvent.Type.CONFERENCE_WILL_JOIN.action -> {}
                        BroadcastEvent.Type.AUDIO_MUTED_CHANGED.action -> {}
                        BroadcastEvent.Type.PARTICIPANT_JOINED.action -> {}
                        BroadcastEvent.Type.PARTICIPANT_LEFT.action -> {}
                        BroadcastEvent.Type.ENDPOINT_TEXT_MESSAGE_RECEIVED.action -> {}
                        BroadcastEvent.Type.SCREEN_SHARE_TOGGLED.action -> {}
                        BroadcastEvent.Type.PARTICIPANTS_INFO_RETRIEVED.action -> {}
                        BroadcastEvent.Type.CHAT_MESSAGE_RECEIVED.action -> {}
                        BroadcastEvent.Type.CHAT_TOGGLED.action -> {}
                        BroadcastEvent.Type.VIDEO_MUTED_CHANGED.action -> {}
                        BroadcastEvent.Type.READY_TO_CLOSE.action -> {}
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.addAction(BroadcastEvent.Type.CONFERENCE_JOINED.action)
        intentFilter.addAction(BroadcastEvent.Type.CONFERENCE_TERMINATED.action)
        intentFilter.addAction(BroadcastEvent.Type.CONFERENCE_WILL_JOIN.action)
        intentFilter.addAction(BroadcastEvent.Type.AUDIO_MUTED_CHANGED.action)
        intentFilter.addAction(BroadcastEvent.Type.PARTICIPANT_JOINED.action)
        intentFilter.addAction(BroadcastEvent.Type.PARTICIPANT_LEFT.action)
        intentFilter.addAction(BroadcastEvent.Type.SCREEN_SHARE_TOGGLED.action)
        intentFilter.addAction(BroadcastEvent.Type.PARTICIPANTS_INFO_RETRIEVED.action)
        intentFilter.addAction(BroadcastEvent.Type.CHAT_MESSAGE_RECEIVED.action)
        intentFilter.addAction(BroadcastEvent.Type.CHAT_TOGGLED.action)
        intentFilter.addAction(BroadcastEvent.Type.VIDEO_MUTED_CHANGED.action)
        intentFilter.addAction(BroadcastEvent.Type.READY_TO_CLOSE.action)
        localBroadcastManager.registerReceiver(receiver, intentFilter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        JitsiMeetActivityDelegate.onActivityResult(this, requestCode, resultCode, data)
    }

    override fun requestPermissions(permissions: Array<String>, requestCode: Int, listener: PermissionListener?) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        JitsiMeetActivityDelegate.onNewIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        JitsiMeetActivityDelegate.onHostResume(this)
    }

    override fun onPause() {
        super.onPause()
        JitsiMeetActivityDelegate.onHostPause(this)
    }

    override fun onStop() {
        super.onStop()
        JitsiMeetActivityDelegate.onHostDestroy(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        JitsiMeetActivityDelegate.onBackPressed()
    }
}