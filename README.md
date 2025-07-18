# Тестовое задание Стажёра

# Обновление 
В проект добавлен Allure.   
Для просмотра отчётов на ПК требуется установить https://allurereport.org/docs/install/


## Требования
Цель: Автоматическое тестирование GUI сайта bspb.ru.  
Стек: Java 21, selenium, junit, gradle  
Тестов должно быть не больше 10 .

## Команды запуска
Команды запуска  проекта  
- .\gradlew test 
- .\gradlew test -Dcucumber.filter.tags="@mobile" - Этой командой запустятся все тесты на cucumber только с указанным тэгом, но так же и все unit тесты

- allure serve - создаст папку allure-results 


Html отчёт тестов.
<img width="1858" height="958" alt="image" src="https://github.com/user-attachments/assets/bb4b739a-c9ee-4694-b2bf-df3a9ff91f71" />
