package com.adapter.service.common;

import com.adapter.service.common.IAlarmServiceCallback;

interface IAlarmService {
	void setup();
	void registerAlarmCallback(IAlarmServiceCallback cb);
}
