/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core.events

/**
 * ShowToolbarEvent
 *
 * This class function is responsible to be sent to Subscriber. Activity becomes Subscriber / Observer.
 * Fragment becomes Subject. If Fragment doesn't want toolbar displayed then Fragment send information with this class to activity
 *
 * This class needs Event Bus library because to toggle toolbar in the same activity
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class ShowToolbarEvent(var isShow: Boolean?) {}
