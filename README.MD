# EngDictionary app
Android application searching for english translations via https://dictionary.skyeng.ru/doc/api/external api
App has 2 screens. One with a search interface with search resulsts and another one with specific search result details

## Building
Run this and find android APK in app/build/outputs/apk

```sh
./gradlew buildDebug
```

| Dependency | Reason |
| ------ | ------ |
| RxJava | Async framework with powerful streams |
| Dagger | Android Dependency injection library |
| Moxy | Library that helps to handle configurations changes |
| Retrofit | Http client for api calls |
| Moshi | Kotlin-first json converter |
| ViewbindingPropertyDelegate | Small and handy library for viewbinding |
| Glide | Image loading framework |
| Mockito-Kotlin | Kotlin extensions library for mockito |

![ScreenShot](https://github.com/qoswa/EngDictionary/blob/master/screenshots/first_screen.png)
![ScreenShot](https://github.com/qoswa/EngDictionary/blob/master/screenshots/second_screen.png)