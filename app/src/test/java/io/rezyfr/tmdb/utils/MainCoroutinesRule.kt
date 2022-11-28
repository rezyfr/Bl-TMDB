package io.rezyfr.tmdb.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestRule, TestCoroutineScope by TestCoroutineScope() {

    private val testCoroutinesDispatcher = TestCoroutineDispatcher()

    override fun apply(base: Statement, description: Description): Statement = object : Statement() {
        override fun evaluate() {
            Dispatchers.setMain(testCoroutinesDispatcher)
            base.evaluate()
            Dispatchers.resetMain()
            cleanupTestCoroutines()
        }
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
        testCoroutinesDispatcher.runBlockingTest { block() }
}