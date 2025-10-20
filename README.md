# Shelter Translator

**Minecraft Mod** written in Java, using REST API to translate chat messages via Yandex Cloud services.  

The mod is designed to automatically translate chat messages on the client, making communication easier between players speaking different languages. Originally developed for the **Shelter** server.

---

## Features

- Automatic detection of the incoming message language.
- Translation of messages to the client's language.
- Displaying translations only to the client.

---

## Installation and Running

1. **Create an API Key in Yandex Cloud**  
   Go to [this link](https://yandex.cloud/ru/docs/resource-manager/api-ref/Folder/get) and create a service account with permissions to use Yandex Translate API.

2. **Create a `.env` file**  
   - Place it in the `run` folder (or project root during development).  
   - The file should contain:  
     ```env
     API_KEY=your_api_key
     FOLDER_ID=your_folder_id
     ```

3. **Build the project**  
   - Run the Gradle build:  
     ```bash
     ./gradlew build
     ```  
   - After building, the main mod will be located in `build/libs/` as `shelter_translator-<version>.jar`.

4. **Run Minecraft with the mod**  
   - Place the built `.jar` in the `mods` folder of your Minecraft client with Fabric.

---

## Requirements

- Minecraft with Fabric Loader.
- Java 21 or higher.
- Internet connection for Yandex Translate API access.

---

## Support and Feedback

- The mod was created specifically for the **Shelter** server.  
- For questions and suggestions, join our [Discord server](https://discord.gg/NU9gr8vwU).

---

## License

The mod is distributed under the **CC0 1.0 Universal (Public Domain)** license.  
You are free to use, modify, and distribute the mod for any purpose, including commercial purposes.

---

# Shelter Translator

**Мод для Minecraft**, написанный на Java, использующий REST API для перевода текста с применением сервисов Yandex Cloud.  

Мод предназначен для автоматического перевода сообщений чата на клиенте, облегчая общение на разных языках. Изначально разработан для сервера **Shelter**.

---

## Возможности

- Автоопределение языка входящего текста.
- Перевод сообщений на язык клиента.
- Отображение перевода только для клиента.
---

## Установка и запуск

1. **Создайте API ключ в Yandex Cloud**  
   Для этого перейдите по [ссылке](https://yandex.cloud/ru/docs/resource-manager/api-ref/Folder/get) и создайте сервисный аккаунт с правами на использование Yandex Translate API.

2. **Создайте файл `.env`**  
   - Поместите его в папку `run` (или в корень проекта при разработке).  
   - Файл должен содержать следующие строки:  
     ```env
     API_KEY=ваш_ключ
     FOLDER_ID=ваш_folder_id
     ```

3. **Соберите проект**  
   - Выполните сборку через Gradle:  
     ```bash
     ./gradlew build
     ```  
   - После сборки основной мод будет находиться в `build/libs/` как `shelter_translator-<version>.jar`.

4. **Запустите Minecraft с модом**  
   - Поместите собранный `.jar` в папку `mods` вашего клиента Minecraft с Fabric.

---

## Требования

- Minecraft с Fabric Loader.
- Java 21 или выше.
- Доступ к интернету для работы Yandex Translate API.

---

## Поддержка и обратная связь

- Мод создавался специально для сервера **Shelter**.  
- Для вопросов и предложений подключайтесь к нашему [Discord-серверу](https://discord.gg/NU9gr8vwU).

---

## Лицензия

Мод распространяется под лицензией **CC0 1.0 Universal (Public Domain)**.  
Вы можете свободно использовать, модифицировать и распространять мод в любых целях, включая коммерческие.
<img width="166" height="804" alt="image" src="https://github.com/user-attachments/assets/cc492f16-4afe-4e46-ae94-f5e328846446" />

