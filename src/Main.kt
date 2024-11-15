import java.io.BufferedReader
import java.io.FileReader

fun main() {
    try {
        val filePath = "file.txt"
        var highestRow = 0 // Храним номер самого высокого ряда
        var smallestSeatInHighestRow = Int.MAX_VALUE
        var lastRow = 0 // Предыдущий обработанный ряд
        var lastSeat = 0 // Предыдущее обработанное место

        // Читаем файл построчно
        BufferedReader(FileReader(filePath)).use { reader ->
            var currentLine: String?
            while (reader.readLine().also { currentLine = it } != null) {
                // Разделяем строку на части: текущий ряд и место
                val parts = currentLine!!.split(" ")
                val currentRow = parts[0].toInt() // Номер текущего ряда
                val currentSeat = parts[1].toInt() // Номер текущего места

                // Проверяем, если места идут подряд с разницей в 3
                if (lastRow == currentRow && lastSeat == currentSeat - 3) {
                    // Если нашли новый ряд с большим номером
                    if (currentRow > highestRow) {
                        highestRow = currentRow
                        smallestSeatInHighestRow = lastSeat + 1
                    } else if (currentRow == highestRow) {
                        //проверяем минимальное место
                        smallestSeatInHighestRow = kotlin.math.min(smallestSeatInHighestRow, lastSeat + 1)
                    }
                }

                lastRow = currentRow
                lastSeat = currentSeat
            }
        }

        // Выводим результат
        println("Самый высокий ряд: $highestRow")
        println("Самое маленькое место в этом ряду: $smallestSeatInHighestRow")
    } catch (e: Exception) {
        println("Ошибка при обработке файла: ${e.message}")
    }
}
