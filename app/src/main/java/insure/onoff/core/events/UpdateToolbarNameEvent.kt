/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.core.events

/**
 * UpdateToolbarNameEvent
 *
 * This class function is responsible to be sent to Subscriber. Activity becomes Subscriber / Observer.
 * Fragment becomes Subject. If Fragment doesn't want toolbar name displayed. Then Fragment gives information to activity
 *
 * This class needs Event Bus library because to change page title in the same activity
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */
class UpdateToolbarNameEvent(var titleToolbar: String?) { }
