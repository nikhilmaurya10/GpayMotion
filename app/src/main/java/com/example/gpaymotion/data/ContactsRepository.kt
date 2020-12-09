package com.example.gpaymotion.data

import com.example.gpaymotion.models.BaseModel
import com.example.gpaymotion.models.ContactCardData
import com.google.gson.Gson
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class ContactsRepository: KoinComponent {
    private val json = "{\"people\":[{\"name\":\"Random 1\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 2\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 3\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 4\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 5\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 6\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 7\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 8\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 88\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 86\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 34\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 43\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 3452\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"},{\"name\":\"Random 234\",\"url\":\"https://st3.depositphotos.com/1767687/16607/v/600/depositphotos_166074422-stock-illustration-default-avatar-profile-icon-grey.jpg\"}],\"business\":[{\"name\":\"Google\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Facebook\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"YouTube\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"SoundCloud\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Instagram\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Alpha Romeo\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Abarth\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"BMW\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Leicester City\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Arsenal\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Instagram\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Alpha Romeo\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"Abarth\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"},{\"name\":\"BMW\",\"url\":\"https://www.designbust.com/download/1016/png/google_logo_png_transparent512.png\"}]}"

   //TODO: Move these functions to bg thread so we don't have to worry about it in viewmodel
    fun getPeopleData() : List<ContactCardData>{
        val list: ArrayList<ContactCardData> = arrayListOf()
        val jsonData = Gson().fromJson<JsonData>(json, JsonData::class.java)
        list.addAll(jsonData.people.map { ContactCardData(it.name, it.url) })
        return list
    }

    fun getBusinesses(): List<ContactCardData>{
        val list: ArrayList<ContactCardData> = arrayListOf()
        val jsonData = Gson().fromJson<JsonData>(json, JsonData::class.java)
        list.addAll(jsonData.business.map { ContactCardData(it.name, it.url) })
        return list
    }
}