.PHONY: build

run-dist:
	./build/install/app/bin/app
install:
	./gradlew clean install
build:
	./gradlew clean build
lint:
	./gradlew checkstyleMain
