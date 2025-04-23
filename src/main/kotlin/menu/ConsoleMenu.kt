package menu

import java.util.Scanner

class ConsoleMenu(private val title: String) {
    private val items = mutableListOf<Pair<String, () -> Boolean>>()
    private val menuScanner = Scanner(System.`in`)

    fun addItem(name: String, action: () -> Boolean) {
        items.add(name to action)
    }

    fun runOnce(): Boolean {
        println("\n--- $title ---")
        items.forEachIndexed { index, (name, _) ->
            println("$index. $name")
        }
        print("Выберите пункт: ")

        while (true) {
            val input = menuScanner.nextLine()
            val choice = input.toIntOrNull()

            when {
                choice == null -> {
                    println("Ошибка: Пожалуйста, введите число.")
                    print("Выберите пункт: ")
                }

                choice !in 0 until items.size -> {
                    println("Ошибка: Такого пункта меню нет. Введите число от 0 до ${items.size - 1}.")
                    print("Выберите пункт: ")
                }

                else -> {
                    val (_, action) = items[choice]
                    return action()
                }
            }
        }
    }
}