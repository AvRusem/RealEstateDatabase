# Проект "База данных недвижимости"

Этот проект представляет собой пример Java CLI приложения, созданного с использованием Gradle. Он включает в себя основные зависимости и конфигурации для начала работы.

## Структура проекта

- `app/`
  - `.env.example` - Пример файла `.env`.
  - `bin/` - Директория для исполняемых файлов.
  - `build/` - Директория для сборки проекта.
  - `build.gradle.kts` - Основной файл конфигурации Gradle для проекта.
  - `resources/` - Директория для ресурсов проекта.
  - `src/` - Исходный код проекта.

## Основные классы и методы

### `model` (Модели объектов недвижимости)  
Этот пакет содержит основные сущности недвижимости.

- **`Entity`** – базовый класс для всех объектов недвижимости.  
  - `private String address` – адрес недвижимости.  
  - `private double area` – площадь недвижимости.  
  - Геттеры и сеттеры.

- **`FlatRoom`**
  - `private int floor` – этаж квартиры.  
  - `private int apartmentNumber` – номер квартиры.

- **`House`**
  - `private int floors` – количество этажей.  
  - `private boolean hasGarage` – наличие гаража.

- **`Office`** 
  - `private String companyOwner` – название компании-владельца.

### `repository` (Хранилище данных)  
Этот пакет отвечает за работу с файлами и хранение информации.

- **`Repository<T>`** – generic-интерфейс, описывающий базовые CRUD-операции:  
  - `void create(T entity);` – добавление новой сущности.  
  - `List<T> getAll();` – получение списка всех сущностей.  
  - `void update(int index, T entity);` – обновление сущности.  
  - `void delete(int index);` – удаление сущности.

- **`AbstractFileRepository<T>`** – абстрактный класс для работы с файлами.  

- **`FlatRoomRepository` / `HouseRepository` / `OfficeRepository`**  
  - Наследуются от `AbstractFileRepository<T>`.  
  - Реализуют методы `serialize` и `deserialize`.

### `io` (Взаимодействие с пользователем)  
Этот пакет отвечает за ввод/вывод в консоли.

- **`AbstractDialog<T>`** – абстрактный класс для обработки команд пользователя.  
  - `public void run()` – запускает консольное меню.  
  - `protected abstract String format(T object);` – форматирует объект для вывода.  
  - `protected abstract T read();` – считывает объект из ввода.  

- **`FlatRoomDialog` / `HouseDialog` / `OfficeDialog`**  
  - Наследуются от `AbstractDialog<T>`.  
  - Реализуют методы `format` и `read`.  

### Основной запуск  
- **`Application`** – точка входа в программу.  
  - Создаёт объект `ApplicationContext`.  
  - Показывает пользователю главное меню.  

- **`ApplicationContext`** – создаёт и хранит ссылки на репозитории и диалоги.  



### `app/build.gradle.kts`
Этот файл содержит конфигурацию Gradle для проекта.


## Работа с переменными окружения

1. Создайте файл настроек переменных окружения, например, `env.sh`.  
2. Перенесите в него содержимое из предоставленного примера или добавьте необходимые переменные вручную.  
3. Укажите переменные в формате:

   ```bash
   export REPOSITORY=your_repository
   ```

4. Сохраните файл и загрузите переменные в текущую сессию с помощью команды:

    ```bash
    source env.sh  
    ```

## Запуск проекта
Для сборки и запуска проекта используйте команду ```gradlew run```

