package menu

import data.Archive
import data.Note
import java.util.Scanner

private val inputScanner = Scanner(System.`in`)

internal fun showNoteSelectionScreen(archive: Archive) {
    while (true) {
        val noteMenu = ConsoleMenu("Архив: ${archive.name} - Список заметок")
        noteMenu.addItem("Создать заметку") {
            createNote(archive)
            true
        }

        archive.notes.forEachIndexed { index, note ->
            noteMenu.addItem(note.name) {
                viewNoteScreen(archive, index)
                true
            }
        }

        noteMenu.addItem("Назад") {
            false
        }

        val continueNoteLoop = noteMenu.runOnce()
        if (!continueNoteLoop) {
            break
        }
    }
}

private fun createNote(archive: Archive) {
    println("\n--- Создание новой заметки в архиве '${archive.name}' ---")
    var noteName: String?
    do {
        print("Введите название заметки (не может быть пустым): ")
        noteName = inputScanner.nextLine()?.trim()
        if (noteName.isNullOrBlank()) {
            println("Ошибка: Название заметки не может быть пустым.")
        }
    } while (noteName.isNullOrBlank())

    var noteText: String?
    do {
        print("Введите текст заметки (не может быть пустым): ")
        noteText = inputScanner.nextLine()?.trim()
        if (noteText.isNullOrBlank()) {
            println("Ошибка: Текст заметки не может быть пустым.")
        }
    } while (noteText.isNullOrBlank())

    val newNote = Note(noteName, noteText)
    archive.notes.add(newNote)
    println("Заметка '$noteName' успешно добавлена в архив '${archive.name}'.")
}