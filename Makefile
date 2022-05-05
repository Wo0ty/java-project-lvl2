# запуск исполняемого файла
run-dist:
	./build/install/app/bin/app
# очистка результатов предыдущей сборки и запуск новой
install:
	./gradlew clean install
build:	# сборка проекта
	./gradlew clean build