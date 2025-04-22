package menu

import data.Archive
import data.archivesList
import java.util.Scanner

private val inputScanner = Scanner(System.`in`)

fun showArchiveSelectionScreen() {
    while (true) {
        val archiveMenu = ConsoleMenu("Архивы")

        archiveMenu.addItem("Создать архив") {
            createArchive()
            true
        }

        archivesList.forEach { archive ->
            archiveMenu.addItem(archive.name) {
                showNoteSelectionScreen(archive)
                true
            }
        }

        archiveMenu.addItem("Выход") {
            println("Выход из программы...")
            false
        }

        val continueLoop = archiveMenu.runOnce()
        if (!continueLoop) {
            break
        }
    }
    println("Программа завершена.")
}

private fun createArchive() {
    println("\n--- Создание нового архива ---")
    var archiveName: String?
    do {
        print("Введите имя архива (не может быть пустым): ")
        archiveName = inputScanner.nextLine()?.trim()
        if (archiveName.isNullOrBlank()) {
            println("Ошибка: Имя архива не может быть пустым.")
        }
    } while (archiveName.isNullOrBlank())

    val newArchive = Archive(archiveName)
    archivesList.add(newArchive)
    println("Архив '$archiveName' успешно создан.")
}