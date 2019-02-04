package insure.onoff.utilities

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun runOnBackgroundThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}