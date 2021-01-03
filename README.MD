## Pit of Anguish - Game

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Usage](#usage)
* [Setup](#setup)

## General info
This project is made as part of exercises with JavaFX. Pit of Anguish is simple game where You must survive as long as possible and get best score. Game is based on Android game that I once played when bored.

## Technologies
Project is created with:
* JavaFX version: 11.0.2
* Jetbrains Annotations version: 20.1
* Gson version: 2.8.5

## Usage
From the main menu You can type Your nickname, look at the leaderboards and exit the game. After You start the game You can move Your character (knight) by dragging it to the next tile. Movement is set only one tile at the time in these directions: up, down, left or right. 
Possible interactions:
* When Your character is dropped on gems - gold score is increased,
* When Your characters is dropped on a monster - program will check if You have attack power, if not Your health is decreased,
* When Your character is dropped on a food - health is increased,
* When Your character is dropped on a weapon - attack power is increased,
* When Your HP score is dropped to 0 - game will save Your score and nickname to leaderboard and game will end,
* When Your character is dropped at incorrect coordinates - character will return to his pre-dragged position.

## Setup
To run this project, use SDK like IntelliJ IDEA"
```
Gradle -> Tasks -> application -> run
```