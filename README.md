<H2> Log Service </H2>

Данный сервис имеет следующие методы:

1. Создает файл лога, и в течении минуты все записывает в него. Каждую минуту создается новый файл ( если есть источник
   для записи логов) - `writerFilesAutoScan(String message, String dir, String name, long lastTimeUpdate)`
2. Создает и записывает в файл, за период времени - от последней проверки расшаренной папки до текущего времени
   -  `writerFilesManualScan(String message, String dir, String name, long lastTimeUpdate)`
3. Создает и записывает в файл за текущие сутки - ` writerFilesLogOneDay(String message, String dir)`
4. Можно создавать кастомный лог - `writerFilesManual(String dir, String name, String message)`
5. Метод удаления файла - `deleteFile(String dir, String name)`
6. Метод который возращает файл дескриптер, что бы можно было "швыряться" файлом лога в пользователя
   - `prepareLogFileInDir`
7. Метод цветного текста в консоль - `colorTextToConsole(ConsoleTextColor color, String text)`

Параметры:

```
    BLACK 
    RED
    GREEN
    YELLOW
    BLUE
    PURPLE
    CYAN
    WHITE
    -----------------------
    RESET
```

 ------------------------

Координаты модуля: `ru.spacecorp.getmanenko.logservice:logservice-global:0.5 -SNAPSHOT`