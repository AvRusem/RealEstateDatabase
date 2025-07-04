# База данных недвижимости

## Постановка задачи

Вам нужно разработать учётную систему недвижимости. В этой системе есть возможность просматривать, заносить, изменять и удалять различные виды недвижимости. База данных должна храниться в файлах, для каждого вида недвижимости — свой. Взаимодействие с пользователем должно осуществляться через консольный ввод/вывод.

## Требования к реализации

Проект должен включать:

- набор классов, которые реализуют требуемую логику;
- обработку взаимодействия с пользователем через консольный ввод/вывод;
- обработку ошибок (неверный ввод пользователя) — должно быть реализовано через бросание и поимку exception;
- документацию: README с описанием проекта, инструкциями по запуску.

Используйте Git для совместной работы. Не забудьте проверить работоспособность кода и предусмотреть различные сценарии поведения программы!

В рамках программы необходимо предусмотреть работу с объектами следующего типа:

- Квартира. Свойства: адрес, площадь помещения, этаж, номер квартиры.
- Офис. Свойства: адрес, площадь помещения, компания-владелец.
- Дом. Свойства: адрес, площадь помещения, количество этажей, есть ли гараж (True/False).

## Взаимодействие с пользователем

При старте приложения пользователь выбирает один из 3 каталогов:

- каталог офисов,
- каталог квартир,
- каталог домов.

В каждом каталоге доступны следующие команды:

- Показать все сущности.
- Добавить новую сущность.
- Отредактировать сущность. Сначала вводится позиция сущности в каталоге (порядковый номер), после — новые значения для всех полей сущности.
- Удалить сущность по позиции в каталоге (порядковому номеру).

## Хранение данных

Данные по каждой сущности должны храниться в разных файлах, но в одной директории. Имя директории разрешено захардкодить в код или, что более правильно, передать через environment variable.

## Пример работающей программы

```bash
[1] - Houses
[2] - FlatRooms
[3] - Offices
[0] - Exit
1
[House]
[1] - Show all
[2] - Create
[3] - Update
[4] - Delete
[0] - Exit
1
[0] - Address: Rybinovaya 23, Area: 243.00, Floors: 1, Garage: true
[House]
[1] - Show all
[2] - Create
[3] - Update
[4] - Delete
[0] - Exit
2
Enter address: Gedroytza 43
Enter area: 342
Enter floors: 2
Has garage?: false
[House]
[1] - Show all
[2] - Create
[3] - Update
[4] - Delete
[0] - Exit
1
[0] - Address: Rybinovaya 23, Area: 243.00, Floors: 1, Garage: true
[1] - Address: Gedroytza 43, Area: 342.00, Floors: 2, Garage: false
[House]
[1] - Show all
[2] - Create
[3] - Update
[4] - Delete
[0] - Exit
```

### Технические пояснения к классам

#### Иерархия сущностей

Создайте базовый класс `Entity`, который будет описывать общие поля каждого класса недвижимости. С помощью наследования создайте классы `FlatRoom`, `House`, `Office`, которые будут описывать конкретный вид недвижимости.

Не забывайте про инкапсуляцию, конструктор и геттеры-сеттеры.

#### Хранение данных (Repository)

##### Repository

Определите интерфейс Repository. Он должен описывать следующие операции над произвольными сущностями (сделайте его generic):

- создание сущности;
- получение всех сущностей (возвращает `List<T>`);
- удаление сущности по её позиции в списке;
- изменение сущности по её позиции в списке.

##### AbstractFileRepository

Когда вы напишете общую реализацию хранения элементов в файле, то нужно будет добавить возможность «писать» в файл любой объект или «читать» любой объект из файла.

Хороший вариант — создать абстрактные методы, один из которых преобразует объект в строку (запись строк в файл), а другой — создаст из строки объект (прочтение строк из файла):

```java
    protected abstract T deserialize(String content);
    protected abstract String serialize(T object);
```

Используя эти методы, можно реализовать внутри `AbstractFileRepository` все методы интерфейса `Repository`.

Имя файла, с которым вы будете работать, передавайте через конструктор. Если файла не существует, то создайте его.

Предусмотрите обработку исключений, которые могут возникать при работе с файлами. Хороший вариант — ловить их и выкидывать другое исключение с понятным описанием того, что произошло. Договоритесь с разработчиком класса `AbstractDialog` о типе исключений, чтобы он мог их отлавливать и отображать пользователю информацию об ошибке.

##### HouseRepository, OfficeRepository, FlatRoomRepository

Унаследуйтесь от `AbstractFileRepository`. Наследование будет выглядеть следующим образом:

```java
class FlatRoomRepository extends AbstractFileRepository<FlatRoom> {
   // ...
}
```

Реализуйте методы `serialize` и `deserialize`. Один из вариантов сериализации/десериализации — записывать значения полей, разделённые нулевым символом: `"\0"`.

#### Логика взаимодействия с пользователем

##### AbstractDialog

Общую логику для всех типов недвижимости стоит вынести в абстрактный generic-класс `AbstractDialog<T>`. Чтобы взаимодействовать с хранилищем, необходимо хранить в поле объект класса `Repository<T>`.

Вам нужно будет реализовать общую логику по взаимодействию с разными типами недвижимости. Чтобы выводить на экран конкретный класс или читать его с помощью стандартного ввода, можно создать абстрактные методы.

Скелет класса `AbstractDialog<T>` может выглядеть следующим образом:

```java
public abstract class AbstractDialog<T> {

    // Все операции делаем через репозиторий
    private final Repository<T> repository;

    protected AbstractDialog(Repository<T> repository) {
        this.repository = repository;
    }

    public void run() {
        // Цикл, в котором спрашиваем команду у пользователя и выполняем его действие 
    }

    protected abstract String getEntityName();
    
    // Получаем строку для вывода объекта на экран
    protected abstract String format(T object);
    // Читаем объект из стандартного ввода
    protected abstract T read();

}
```

##### HouseDialog, OfficeDialog, FlatRoomDialog

Реализуйте эти классы, унаследовавшись от `AbstractDialog`. Наследование будет выглядеть следующим образом:

```java
class FlatRoomDialog extends AbstractDialog<FlatRoom> {
   // ...
}
```

Реализуйте методы `format` и `read`.

#### Application и ApplicationContext

Теперь о методе `main`. Стоит создать объекты уже описанных ранее классов. Хранить их нужно в классе `ApplicationContext` в виде полей, предоставив getter.

В методе `main` нужно спросить у пользователя, с каким видом недвижимости он собирается работать, после чего выбрать нужный класс `***Dialog` (например, для квартир это будет `FlatRoomDialog`).

После нужно запустить метод из класса `***Dialog`, предоставив ему управлять вводом/выводом для конкретного типа недвижимости.

# Система оценивания

## Критерии оценки

### Функционал программы: 0–3 балла

- 3 — программа работает, все граничные случаи учтены.
- 2 — основной функционал работает, есть неучтённые флоу, которые могут приводить к падениям программы.
- 1 — программа не запускается, есть отдельно работающие классы.
- 0 — функционал не реализован.

### Иерархия и наследование: 0–3 балла

- 3 — реализовано три иерархии (model, repository, dialog). Использованы абстрактные классы и абстрактные методы. Дублирующая логика вынесена в базовый класс.
- 2 — иерархия в целом реализована верно, но есть небольшие недочёты, дублирующая логика и так далее.
- 1 — иерархия реализована только для моделей, нет абстрактных методов в классах.
- 0 — наследования нет.

### Generics: 0–2 балла

- 2 — использованы дженерики в `AbstractDialog`, `AbstractFileRepository`. Нет явных ошибок и ворнингов с ручным приведением типа.
- 1 — дженерики использованы, но есть недочёты.
- 0 — дженерики не использованы.

### Общее качество кода: 0–2 балла

По баллу за каждый реализованный пункт.

- Реализована инкапсуляция — приватные поля, геттеры-сеттеры.
- Код лаконичен, нет грубых костылей.

  

Итого: 0–10 баллов.
