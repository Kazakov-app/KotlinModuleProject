package menu

import data.Archive
import data.Note
import java.util.Scanner

private val screenScanner = Scanner(System.`in`)

internal fun viewNoteScreen(archive: Archive, noteIndex: Int) {

    val note = archive.notes[noteIndex]

    println("\n--- Просмотр заметки ---")
    println("Название: ${note.name}")
    println("-----------------------")
    println("Текущий текст:")
    println(note.text)
    println("-----------------------")

    print("Хотите отредактировать текст этой заметки? (да/нет): ")
    val answer = screenScanner.nextLine()?.trim().equals("да", ignoreCase = true)

    if (answer) {
        var newText: String?
        do {
            println("Введите новый текст заметки (не может быть пустым):")
            newText = screenScanner.nextLine()?.trim()
            if (newText.isNullOrBlank()) {
                println("Ошибка: Текст заметки не может быть пустым.")
            }
        } while (newText.isNullOrBlank())

        val updatedNote = Note(name = note.name, text = newText)

        archive.notes[noteIndex] = updatedNote
        println("Текст заметки '${note.name}' успешно обновлен.")

    } else {
        println("Текст заметки не изменен.")
    }

    print("\nНажмите Enter для возврата к списку заметок...")
    screenScanner.nextLine()
}