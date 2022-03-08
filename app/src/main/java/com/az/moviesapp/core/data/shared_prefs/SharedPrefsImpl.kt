package com.enayakw.app.core.data.shared_prefs

import android.content.Context
import java.util.concurrent.atomic.AtomicReferenceArray

class SharedPrefsImpl(context: Context) : SharedPrefs {

    companion object {
        private const val PREFERENCES_NAME = "enaya_preferences"
        private const val AUTH_TOKEN = "auth_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val USER_EMAIL = "user_email"
        private const val USER_NAME = "user_name"
        private const val USER_IMAGE = "user_image"
        private const val USER_ID = "user_id"
        private const val IS_TENANT = "is_tenant"
        private const val FACEBOOK = "facebook"
        private const val INSTAGRAM = "instagram"
        private const val TWITTER = "twitter"
        private const val YOUTUBE = "youtube"
        private const val FACEBOOK_IMAGE = "facebook_image"
        private const val INSTAGRAM_IMAGE = "instagram_image"
        private const val TWITTER_IMAGE = "twitter_image"
        private const val YOUTUBE_IMAGE = "youtube_image"
        private const val FCM_TOKEN = "fcm_token"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun saveIsTenant(value: Boolean?) {
        value?.let { preferences.edit().putBoolean(IS_TENANT, it).apply() }
    }

    override fun getIsTenant(): Boolean? {
        if(preferences.contains(IS_TENANT)) {
            return preferences.getBoolean(IS_TENANT, false)
        }
        return null
    }

    override fun saveAuthToken(value: String) {
        preferences.edit().putString(AUTH_TOKEN, "Bearer "+value).apply()
    }

    override fun getAuthToken(): String? {
        return preferences.getString(AUTH_TOKEN, null)
    }

    override fun saveRefreshToken(value: String) {
        preferences.edit().putString(REFRESH_TOKEN,value).apply()
    }

    override fun getRefreshToken(): String? {
        return preferences.getString(REFRESH_TOKEN, null)
    }

    override fun saveUserId(value: Int) {
        preferences.edit().putInt(USER_ID, value).apply()
    }

    override fun getUserId(): Int? {
        val value = preferences.getInt(USER_ID, -1)
        if(value != -1){
            return value
        }
        return null
    }

    override fun saveUserEmail(value: String) {
        preferences.edit().putString(USER_EMAIL, value).apply()
    }

    override fun getUserEmail(): String? {
        return preferences.getString(USER_EMAIL, null)
    }

    override fun saveUserName(value: String) {
        preferences.edit().putString(USER_NAME, value).apply()
    }

    override fun getUserName(): String? {
        return preferences.getString(USER_NAME, null)
    }

    override fun saveUserImage(value: String) {
        preferences.edit().putString(USER_IMAGE, value).apply()
    }

    override fun getUserImage(): String? {
        return preferences.getString(USER_IMAGE, null)
    }


    override fun wipeUserData() {
        preferences.edit().putString(AUTH_TOKEN, null).apply()
        preferences.edit().putString(REFRESH_TOKEN, null).apply()
        preferences.edit().putString(USER_EMAIL, null).apply()
        preferences.edit().putString(USER_NAME, null).apply()
        preferences.edit().putInt(USER_ID, -1).apply()
        preferences.edit().putBoolean(IS_TENANT, false).apply()
        preferences.edit().putString(USER_IMAGE, null).apply()

    }

    override fun setFacebook(value: String) {
        value?.let { preferences.edit().putString(FACEBOOK,it).apply() }
    }

    override fun getFacebook(): String? {
        return preferences.getString(FACEBOOK,"")
    }

    override fun setInstagram(value: String) {
        value?.let { preferences.edit().putString(INSTAGRAM,it).apply() }
    }

    override fun getInstagram(): String? {
        return preferences.getString(INSTAGRAM,"")
    }

    override fun setTwitter(value: String) {
        value?.let { preferences.edit().putString(TWITTER,it).apply() }
    }

    override fun getTwitter(): String? {
        return preferences.getString(TWITTER,"")
    }

    override fun setYoutbe(value: String) {
        value?.let { preferences.edit().putString(YOUTUBE,it).apply() }
    }

    override fun getYoutbe(): String? {
        return preferences.getString(YOUTUBE,"")
    }

    override fun setFacebookImage(value: String) {
        value?.let { preferences.edit().putString(FACEBOOK_IMAGE,it).apply() }
    }

    override fun getFacebookImage(): String? {
        return preferences.getString(FACEBOOK_IMAGE,"")
    }

    override fun setInstagramImage(value: String) {
        value?.let { preferences.edit().putString(INSTAGRAM_IMAGE,it).apply() }
    }

    override fun getInstagramImage(): String? {
        return preferences.getString(INSTAGRAM_IMAGE,"")
    }

    override fun setTwitterImage(value: String) {
        value?.let { preferences.edit().putString(TWITTER_IMAGE,it).apply() }
    }

    override fun getTwitterImage(): String? {
        return preferences.getString(TWITTER_IMAGE,"")
    }

    override fun setYoutbeImage(value: String) {
        value?.let { preferences.edit().putString(YOUTUBE_IMAGE,it).apply() }
    }

    override fun getYoutbeImage(): String? {
        return preferences.getString(YOUTUBE_IMAGE,"")
    }

    override fun saveFcmToken(value: String) {
        value?.let { preferences.edit().putString(FCM_TOKEN,value).apply() }
    }

    override fun getFcmToken(): String {
        return preferences.getString(FCM_TOKEN,"").toString()
    }

    override fun wipeSocialMediaData() {
        preferences.edit().putString(FACEBOOK_IMAGE,"").apply()
        preferences.edit().putString(YOUTUBE_IMAGE,"").apply()
        preferences.edit().putString(TWITTER_IMAGE,"").apply()
        preferences.edit().putString(INSTAGRAM_IMAGE,"").apply()

        preferences.edit().putString(YOUTUBE,"").apply()
        preferences.edit().putString(TWITTER,"").apply()
        preferences.edit().putString(INSTAGRAM,"").apply()
        preferences.edit().putString(FACEBOOK,"").apply()
    }


}