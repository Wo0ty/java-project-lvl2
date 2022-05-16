[![Actions Status](https://github.com/Wo0ty/java-project-lvl2/workflows/hexlet-check/badge.svg)](https://github.com/Wo0ty/java-project-lvl2/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/a99a88d28ad37a79dbf6/maintainability)](https://codeclimate.com/github/Wo0ty/java-project-lvl2)
![Java CI](https://github.com/Wo0ty/java-project-lvl2/actions/workflows/JavaCI.yml/badge.svg)
[![Test Coverage](https://api.codeclimate.com/v1/badges/753c19c1ac1def630523/test_coverage)](https://codeclimate.com/github/Wo0ty/java-project-lvl2/test_coverage)

### Описание
___
Калькулятор различий — это консольное приложение, определяющее разницу между двумя структурами данных. Это популярная задача, для решения которой существует множество онлайн-сервисов, например: http://www.jsondiff.com/. Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменении в конфигурационных файлах.
### Особенности
___
* Поддерживаемые входные форматы: yaml, json
* Возможные форматы вывода разлчий: stylish, plain, json
### Запуск
___
В терминале папки проекта выполнить команду `make install` & `make run-dist`.
### Вызов командой
___
`~/app [-hV] [-f=format] filePath1 filePath2`  
Пример: `./build/install/app/bin/app -f stylish src/test/resources/file1.json src/test/resources/file2.json`
### Демонстрация работы
___
[![asciicast](https://asciinema.org/a/wKwRWO6ZWbJfSqaM6wKhbLpyS.svg)](https://asciinema.org/a/wKwRWO6ZWbJfSqaM6wKhbLpyS)