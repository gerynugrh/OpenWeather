![image](https://thumbs.gfycat.com/BeautifulNervousGalapagosalbatross-size_restricted.gif)

### API

API merupakan singkatan dari _application programming interface_, atau dalam bahasa manusia dapat diartikan sebagai penjembatan dalam sisi pemrograman aplikasi. Agar lebih jelas, kita dapat mengartikan API sebagai segala hal yang memberikan abstraksi pada suatu proses. 

Semisal kita ingin mendapatkan data sebuah pengguna pada Twitter kita dapat menggunakan API dari Twitter. Ketika kita menggunakan API kita tidak pernah tahu apa yang terjadi pada server Twitter. Kita hanya perlu mengetahui data apa yang harus kita kirim untuk mendapatkan data yang kita inginkan.

### OpenWeather API

OpenWeather API merupakan sebuah API yang diluncurkan oleh OpenWeather untuk memudahkan developer dalam mendapatkan data terkini mengenai cuaca. OpenWeather API merupakan API yang masuk ke dalam kategori **Restfull API** karena untuk mengaksesnya kita menggunakan request HTTP dan datanya dikirim dengan format JSON.

### Package Structure

Pada pembuatan proyek ini struktur package dibuat seperti berikut

```
|-- build.gradle
|-- README.md
|-- settings.gradle
|-- src
    |-- main
        |-- java
            |-- app
                |-- location
                |-- search
                |-- weather
                    |-- current
                    |-- daily
        |-- resources
    |-- test 
```

Struktur dasar dari proyek ini mengikuti guideline dari Maven sendiri, namun untuk struktur package dalam folder Java ada beberapa konsiderasi yang diperlukan. Konsiderasi pertama adalah saya tidak mengetahui seluas apa cakupan proyek ini.

Oleh karena itu file Java dikelompokkan ke dalam package app, model, app.services, app.view yang pasti ada pada proyek manapun. Dengan ini dalam proses pembuatan aplikasi, tidak akan ada package yang ditambah maupun dikurangi.

### JDepend

| Attr    | app.location | app.search | app.weather | app.weather.current | app.weather.daily |
| ------- | :----------: | :--------: | :---------: | :-----------------: | :---------------: |
| TC      | 2            | 5          | 6           | 6                   | 9                 |
| Ca      | 4            | 0          | 0           | 1                   | 1                 |
| Ce      | 1            | 8          | 13          | 3                   | 3                 |
| A       | 0            | 0.2        | 0.17        | 0                   | 0                 |
| I       | 0.2          | 1          | 1           | 0.75                | 0.75              |
| D       | 0.8          | 0          | 0           | 0.25                | 0.25              |

### Instalation
```bash
gradlew run
```

#### Implemented
* Search
* Current Weather
* Daily Forecast
#### Ongoing
* Set default location
### Cancelled
NONE

### Attribution
[Weather Icon Pack - bqlqn](https://www.flaticon.com/packs/weather-138)
