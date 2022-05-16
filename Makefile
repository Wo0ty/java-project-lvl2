.PHONY: build

clean:
	./gradlew clean

build:
	./gradlew clean build
	make lint

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app -h

lint:
	./gradlew checkstyleMain checkstyleTest
