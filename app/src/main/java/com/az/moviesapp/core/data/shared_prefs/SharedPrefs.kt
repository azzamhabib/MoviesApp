package com.enayakw.app.core.data.shared_prefs

interface SharedPrefs {

    fun saveIsTenant(value: Boolean?)
    fun getIsTenant(): Boolean?
    fun saveAuthToken(value: String)
    fun getAuthToken(): String?
    fun saveRefreshToken(value: String)
    fun getRefreshToken(): String?
    fun saveUserId(value: Int)
    fun getUserId(): Int?
    fun saveUserEmail(value: String)
    fun getUserEmail(): String?
    fun saveUserName(value: String)
    fun getUserName(): String?
    fun saveUserImage(value: String)
    fun getUserImage(): String?
    fun wipeUserData()
    fun setFacebook(value : String)
    fun getFacebook() : String?
    fun setInstagram(value : String)
    fun getInstagram() : String?
    fun setTwitter(value : String)
    fun getTwitter() : String?
    fun setYoutbe(value : String)
    fun getYoutbe() : String?
    fun setFacebookImage(value : String)
    fun getFacebookImage() : String?
    fun setInstagramImage(value : String)
    fun getInstagramImage() : String?
    fun setTwitterImage(value : String)
    fun getTwitterImage() : String?
    fun setYoutbeImage(value : String)
    fun getYoutbeImage() : String?
    fun saveFcmToken(value: String)
    fun getFcmToken(): String
    fun wipeSocialMediaData()



}