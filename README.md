# Shelter Translator

**Minecraft Mod** written in Java, using REST API for translating chat messages via Yandex Cloud services and a VHS relay server ([Shelter Server](https://github.com/tseneven/shelter-server.git)).

The mod automatically translates chat messages on the client side, making communication between players speaking different languages easier. Originally developed for the **Shelter** server.

---

## Features

- Automatic detection of the incoming message language.
- Translation of chat messages to the client’s language.
- Displaying translations only to the client.
- Works with a relay server to manage translations.

---

## Installation & Running

1. **Ensure access to the relay server**  
   - Telegram: [@toroadit](https://t.me/toroadit)

2. **Build the project**  
   - Run Gradle:  
     ```bash
     ./gradlew build
     ```  
   - After building, the mod `.jar` will be located in `build/libs/` as `shelter_translator-<version>.jar`.

3. **Run Minecraft with the mod**  
   - Place the `.jar` file into the `mods` folder of your Minecraft client with Fabric Loader.

---

## Requirements

- Minecraft with Fabric Loader.
- Java 21 or higher.
- Internet connection for Yandex Translate API access.

---

## Support & Feedback

- The mod was created specifically for the **Shelter** server.  
- For questions or suggestions, join our [Discord server](https://discord.gg/NU9gr8vwU).

---

## License

The mod is distributed under the **CC0 1.0 Universal (Public Domain)** license.  
You are free to use, modify, and distribute the mod for any purpose, including commercial use.

---

# Shelter Translator

**Мод для Minecraft**, написанный на Java, использующий REST API для перевода текста с применением сервисов Yandex Cloud и ретрансляцией через VHS сервер (ссылка на ретраслятор)[https://github.com/tseneven/shelter-server.git].  

Мод предназначен для автоматического перевода сообщений чата на клиенте, облегчая общение на разных языках. Изначально разработан для сервера **Shelter**.

---

## Возможности

- Автоопределение языка входящего текста.
- Перевод сообщений на язык клиента.
- Отображение перевода только для клиента.
---

## Установка и запуск
1. **Убедитесь в доступе к серверу-ретранслятору**
   - Телеграмм @toroadit
2. **Соберите проект**  
   - Выполните сборку через Gradle:  
     ```bash
     ./gradlew build
     ```  
   - После сборки основной мод будет находиться в `build/libs/` как `shelter_translator-<version>.jar`.

3. **Запустите Minecraft с модом**  
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

