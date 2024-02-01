# :core:common module

### @Qualifier 란?

- @Qualifier 는 의존성 주입 시, 동일한 타입이 여러개 존재할 때, 어떤 것을 주입할지 선택하는 용도로 사용한다.

```kotlin
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopesModule {
    @Provides
    @Singleton
    @ApplicationScope
    fun providesCoroutineScope(
        @Dispatcher(Default) dispatcher: CoroutineDispatcher,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}
```

- 위의 코드에서 @ApplicationScope 는 ApplicationScope 라는 이름의 Qualifier 를 정의하는 코드이다.
- @ApplicationScope 를 사용하는 이유는, CoroutineScope 를 주입할 때, 어떤 CoroutineScope 를 주입할지 선택하기 위해서이다.
- @ApplicationScope 를 사용하지 않고, CoroutineScope 를 주입하면, 에러가 발생한다.

### Testing

- Result.kt의 Success, Loading, Error 클래스를 테스트

```kotlin
fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
    .onStart { emit(Result.Loading) }
    .catch { emit(Result.Error(it)) }
```

- asResult() 함수는 Flow<T> 를 Flow<Result<T>> 로 변환하는 함수이다.
- onstart() 함수는 Flow 가 시작될 때, Result.Loading 을 emit 한다.
- catch() 함수는 Flow 가 에러가 발생할 때, Result.Error 를 emit 한다.
- asResult() 함수를 통해서 ResultKtTest.kt 에서 테스트를 진행한다.

