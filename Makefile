.PHONY: build

clean:
	./gradlew clean

build:
	./gradlew clean build
		make lint

install:
	./gradlew clean install

run-dist:
	./gradlew run

lint:
	./gradlew checkstyleMain checkstyleTest
