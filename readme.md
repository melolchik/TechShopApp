# Архитектура проекта (Android / Kotlin / Compose)

## Общая структура модулей

    app
    core
		database - БД
		datastore - Хранение настроек
		network - работа с сетью
		ui - темы, базовые элементы
	data
		datastore	- репозиторий настроек
		products	- репозиторий для работы с бд и загрузкой товаров
	domain
		datastore	- модель настроек, интерфейс репозитория, общие usecase
		products	- модель товаров, интерфейс репозитория, общие usecase
    feature
		splash		- presentation слой: Compose, ViewModel
		products	- presentation слой: Compose, ViewModel
		favorites	- presentation слой: Compose, ViewModel
		details		- presentation слой: Compose, ViewModel
		settings	- presentation слой: Compose, ViewModel
		
	 di реализован через hilt в каждом модуле
	 
	 navigation - два графа AppNavGraph и MainNavGraph


## Поток данных

Архитектура построена следующим образом:

    Room / DataSource
           ↓
    Repository
           ↓
    UseCase
           ↓
    ViewModel (StateFlow)
           ↓
    Jetpack Compose UI

------------------------------------------------------------------------

## Основные технологии

-   **Kotlin**
-   **Jetpack Compose**
-   **Coroutines + Flow**
-   **Hilt (DI)**
-   **Navigation Compose**
-   **Room**
-   **MVVM + Clean Architecture**

