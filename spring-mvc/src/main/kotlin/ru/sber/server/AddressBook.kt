package ru.sber.server

import java.util.concurrent.ConcurrentHashMap

class AddressBook() {
    val map = ConcurrentHashMap<String, Note>()
}

data class Note(val name: String, val address: String, val number: String)