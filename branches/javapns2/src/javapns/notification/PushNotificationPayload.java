package javapns.notification;

import org.json.*;

/**
 * A payload compatible with the Apple Push Notification Service.
 * 
 * @author Maxime Peron
 * @author Sylvain Pedneault
 */
public class PushNotificationPayload extends Payload {

	public static Payload alert(String message) {
		PushNotificationPayload payload = new PushNotificationPayload();
		try {
			payload.addAlert(message);
		} catch (JSONException e) {
		}
		return payload;
	}


	public static Payload badge(int badge) {
		PushNotificationPayload payload = new PushNotificationPayload();
		try {
			payload.addBadge(badge);
		} catch (JSONException e) {
		}
		return payload;
	}


	public static Payload sound(String sound) {
		PushNotificationPayload payload = new PushNotificationPayload();
		try {
			payload.addSound(sound);
		} catch (JSONException e) {
		}
		return payload;
	}


	public static Payload combined(String message, int badge, String sound) {
		PushNotificationPayload payload = new PushNotificationPayload();
		try {
			if (message != null) payload.addAlert(message);
			if (badge >= 0) payload.addBadge(badge);
			if (sound != null) payload.addSound(sound);
		} catch (JSONException e) {
		}
		return payload;
	}

	/* The application Dictionnary */
	private JSONObject apsDictionary;


	/**
	 * Constructor, instantiate the two JSONObjects
	 */
	public PushNotificationPayload() {
		super();
		this.apsDictionary = new JSONObject();
		try {
			JSONObject payload = getPayload();
			if (!payload.has("aps")) payload.put("aps", this.apsDictionary);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	public PushNotificationPayload(String alert, int badge, String sound) throws JSONException {
		this();
		addAlert(alert);
		addBadge(badge);
		if (sound != null) {
			addSound(sound);
		}
	}


	/**
	 * Add a badge
	 * @param badge
	 * @throws JSONException
	 */
	public void addBadge(int badge) throws JSONException {
		logger.debug("Adding badge [" + badge + "]");
		this.apsDictionary.putOpt("badge", badge);
	}


	/**
	 * Add a sound
	 * @param sound
	 * @throws JSONException
	 */
	public void addSound(String sound) throws JSONException {
		logger.debug("Adding sound [" + sound + "]");
		this.apsDictionary.putOpt("sound", sound);
	}


	/**
	 * Add an alert message
	 * @param alert
	 * @throws JSONException
	 */
	public void addAlert(String alert) throws JSONException {
		logger.debug("Adding alert [" + alert + "]");
		this.apsDictionary.put("alert", alert);
	}


	/**
	 * Add a custom alert message
	 * @param alert
	 * @throws JSONException
	 */
	public void addCustomAlert(PayLoadCustomAlert alert) throws JSONException {
		logger.debug("Adding custom Alert");
		this.apsDictionary.put("alert", alert);
	}




}