.PHONY: build

run-dist:
	./gradlew run
install:
	./gradlew clean install
	./gradlew install
build:
	./gradlew clean build
	make lint
lint:
	./gradlew checkstyleMain
