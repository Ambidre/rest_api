# Проект по автоматизации тестирования API для веб-приложений

## Покрытый функционал
> Разработаны автотесты на <code>API</code>.
- :white_check_mark: Тесты для [reqres.in](https://reqres.in/) демонстрирующие работу с запросами GET и POST
- :white_check_mark: Тесты для [demowebshop](http://demowebshop.tricentis.com) демонстрируют использование подготовительных шагов REST для проверки контента. Они используют спецификации, пользовательские шаблоны и модель ответа. Также есть примеры использования куки для тестирования API.
- :white_check_mark: Тесты для [demoqa.com](https://demoqa.com) демонстрирующие примеры теситрования с логами, с некоторыми логами и со всеми логами, использование Listener
## Технологический стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Allure TestOps" src="images/logo/Allure_TestOps.svg">
<img width="6%" title="Rest Assured" src="images/logo/Rest-Assured.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
<img width="6%" title="Jira" src="images/logo/Jira.svg">
</p>

> В данном проекте автотесты написаны на <code>Java</code> с использованием библиотеки <code>REST Assured</code>
>
> <code>JUnit 5</code> используется для модульного тестирования
>
> <code>Gradle</code> используется для автоматизированной сборки проекта
>
> <code>Jenkins</code> выполняет запуск тестов
>
> <code>Allure Report</code> формирует отчет о запуске тестов
>
> Автотесты интегрируются с тест-менеджмент системой <code>Allure TestOps</code> и таск-трекер системой <code>Jira</code>
>
> В <code>Telegram</code> отправляются уведомления о пройденном прогоне с помощью библиотеки [allure-notifications](https://github.com/qa-guru/allure-notifications)

## Запуск тестов из терминала
### Локальный запуск тестов

```
gradle clean test
```

## <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Для запусков автотестов используется [Jenkins](https://jenkins.autotests.cloud/job/09-Ambidre-rest_api/)

![Jenkins](images/screens/jenkins.png)

## <img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Анализ результатов запусков в Jenkins через Allure Reports

### Главная страница Allure-отчета

![Jenkins_Allure_Reports](images/screens/allure_dashbord.png)

### Информация о тестовом прогоне в графическом виде

![Jenkins_Allure_Reports](images/screens/allure_graphs.png)

### Группировка тестов по проверяемому функционалу

![Jenkins_Allure_Reports](images/screens/allure_detailes.png)

## <img width="4%" title="Allure TestOps" src="images/logo/Allure_TestOps.svg"> Интеграция тестов c тест-менеджмент системой [Allure TestOps](https://allure.autotests.cloud/launch/10403)

### Основной дашборд

![Allure Overview Dashboard](images/screens/allure_overview_dashboard.png)

### Дашборд для отображения успешности и длительности тестов

![Allure Overview Dashboard](images/screens/allure_duration_and_success_rate_dashboard.png)

### Дашборд по стендам

![Allure Overview Dashboard](images/screens/allure_stands_dashboard.png)

### Дашборд по членам команды

![Allure Overview Dashboard](images/screens/allure_team_dashboard.png)

### Запуски тестов

![Allure Overview Dashboard](images/screens/allure_launches.png)

### Результаты запуска тестов

![Allure_Launches](images/screens/testops_detailes.png)

### Сгруппированные тест-кейсы по проверяемому функционалу

![Allure TestOps](images/screens/allure_testcases.png)

## <img width="4%" title="Jira" src="images/logo/Jira.svg"> Интеграция тестов c таск-трекер системой [Jira](https://jira.autotests.cloud/browse/HOMEWORK-)

> Интеграция с <code>Jira</code> позволяет добавлять в задачи тест-кейсы, запуски и их результаты.
<p align="center">
<img title="Jira Issues" src="images/screens/jira_issues.png">
</p>

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота
> Реализована отправка уведомлений о прогоне с помощью бота в <code>Telegram</code>.
<p align="center">
<img title="Telegram Notifications" src="images/screens/telegram_notifications.png">
</p>
