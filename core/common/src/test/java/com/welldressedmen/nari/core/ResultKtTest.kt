package com.welldressedmen.nari.core

import app.cash.turbine.test
import com.welldressedmen.nari.core.result.Result
import com.welldressedmen.nari.core.result.asResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class ResultKtTest {

    /**
     *  com.welldressedmen.nari.core.result의 Result.kt 파일 테스트
     */

    @Test
    fun Result_cathes_errors() = runTest {
        flow {
            // emit(1)을 통해 데이터 1을 넣어준다. (Result.Success)
            emit(1)
            // throw Exception("Test Done")을 통해 예외를 발생시킨다. (Result.Error)
            throw Exception("Test Done")
        }
            .asResult()
            .test {
                // 위의 flow에서 Loading 상태가 .onStart { emit(Result.Loading) }에 의해 발생하는지 확인
                assertEquals(Result.Loading, awaitItem())
                // 위의 flow에서 emit(1)을 통해 데이터 1을 넣어준 것이 Result.Success(1)로 잘 들어가는지 확인
                assertEquals(Result.Success(1), awaitItem())

                // 위의 flow에서 throw Exception("Test Done")을 통해 예외를 발생시킨 것이 Result.Error로 잘 들어가는지 확인
                when (val errorResult = awaitItem()) {
                    is Result.Error -> assertEquals(
                        "Test Done",
                        errorResult.exception?.message,
                    )
                    // Error가 아닌 경우는 IllegalStateException을 발생시킨다.
                    Result.Loading,
                    is Result.Success,
                    -> throw IllegalStateException(
                        "The flow should have emitted an Error Result",
                    )
                }

                // awaitComplete()를 통해 flow가 끝났는지 확인
                awaitComplete()
            }
    }
}