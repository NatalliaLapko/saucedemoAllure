#language:ru
#encoding:utf-8

  @test @all
  Функционал: Проверка стоимости товаров
    Структура сценария: Проверка стоимости  <itemName>
      Допустим открыта страница "https://www.saucedemo.com/"
      И осуществлён вход на страницу "https://www.saucedemo.com/inventory.html" с помощью логина "standard_user" и пароля "secret_sauce"
      И на странице отображён список товаров
      И выполнено нажатие на ссылку "<itemName>"
      Тогда цена товара равна "<itemPrice>"

      Примеры:
      |itemName                         |itemPrice|
      |Sauce Labs Backpack              |$29.99   |
      |Sauce Labs Bike Light            |$9.99    |
      |Sauce Labs Bolt T-Shirt          |$15.99   |
      |Sauce Labs Fleece Jacket         |$49.99   |
      |Sauce Labs Onesie                |$7.99     |
      |Test.allTheThings() T-Shirt (Red)|$15.99   |






