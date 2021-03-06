package io.agora.rtc.react

import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import io.agora.rtc.RtcChannel
import io.agora.rtc.RtcEngine
import io.agora.rtc.base.RtcTextureView

class RCTAgoraRtcTextureViewManager : SimpleViewManager<RtcTextureView>() {
    companion object {
        const val REACT_CLASS = "RCTAgoraRtcTextureView"
    }

    private var reactContext: ThemedReactContext? = null

    override fun createViewInstance(reactContext: ThemedReactContext): RtcTextureView {
        this.reactContext = reactContext
        return RtcTextureView(reactContext)
    }

    override fun getName(): String {
        return REACT_CLASS
    }

    @ReactProp(name = "channelId")
    fun setChannelId(view: RtcTextureView, channelId: String) {
        getEngine()?.let {
            view.setChannel(it, getChannel(channelId))
        }
    }

    @ReactProp(name = "mirror")
    fun setMirror(view: RtcTextureView, mirror: Boolean) {
        getEngine()?.let { view.setMirror(it, mirror) }
    }

    @ReactProp(name = "uid")
    fun setUid(view: RtcTextureView, uid: Int) {
        getEngine()?.let { view.setUid(it, uid) }
    }

    private fun getEngine(): RtcEngine? {
        return reactContext?.getNativeModule(RCTAgoraRtcEngineModule::class.java)?.engine()
    }

    private fun getChannel(channelId: String): RtcChannel? {
        return reactContext?.getNativeModule(RCTAgoraRtcChannelModule::class.java)?.channel(channelId)
    }
}
