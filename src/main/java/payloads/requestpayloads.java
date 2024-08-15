package payloads;

import pojo.AddSerialNumber;

public class requestpayloads {

	public AddSerialNumber addSerialNumberPayload(String serialnumber) {
		AddSerialNumber payload = new AddSerialNumber();
		payload.setSerialnumber(serialnumber);
		return payload;
	}
}
