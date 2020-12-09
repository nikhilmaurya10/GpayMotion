package com.example.gpaymotion.data

data class JsonData(val people: ArrayList<Contact>, val business: ArrayList<Contact>)
data class Contact(val name: String, val url: String)