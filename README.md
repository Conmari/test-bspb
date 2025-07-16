# Тестовое задание Стажёра

## Требования
Цель: Автоматическое тестирование GUI сайта bspb.ru.  
Стек: Java 21, selenium, junit, gradle  
Тестов должно быть не больше 10 .

## Информация 
Впроекте реализовано 3 проверки.  
1) Отображение кнопки "Войти" на главной странице.
2) Переход по ссылке на страницу с контактами.
3) Переход на страницу авторизации через "ВОЙТИ -> Интернет банк ФЛ"

## Запуск проверок
Команды запуска  проекта  
- .\gradlew test 
- .\gradlew test -Dcucumber.filter.tags="@mobile" - Этой командой запустятся все тесты на cucumber только с указанным тэгом, но так же и все unit тесты



Html отчёт тестов cucumber по пути  "C:\work\project\java\test-bspb\build\reports\tests\test\index.html".
<img width="874" height="497" alt="image" src="https://github.com/user-attachments/assets/6809d674-a977-48a9-aad9-012a8a41c2cb" />


