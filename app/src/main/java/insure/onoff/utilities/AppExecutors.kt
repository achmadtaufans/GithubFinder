/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.utilities

import java.util.concurrent.Executors

/**
 * AppExecutors
 *
 * This class is responsible to contains execution function. Example : provide background/worker thread
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

//To run operation on different thread (name : background/worker thread) from UI thread. So it will not interfere main thread (alias UI thread)
fun runOnBackgroundThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}
