package data

// Заметка с именем и текстовым содержимым.
data class Note(val name: String, val text: String)

// Архив (коллекция) заметок.
// Заметки хранятся в изменяемом списке (mutable list), чтобы можно было добавлять новые.
data class Archive(val name: String, val notes: MutableList<Note> = mutableListOf())

// Глобальное хранилище в памяти для всех архивов.
// Этот список содержит все объекты Archive, созданные во время работы приложения.
val archivesList = mutableListOf<Archive>()
