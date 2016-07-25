package com.example.lockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	private DevicePolicyManager dpm;
	private ComponentName component;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dpm = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
		component = new ComponentName(this, MyReceiver.class);
		if (dpm.isAdminActive(component)) {
			dpm.lockNow();
		} else {
			openAdmin();
		}
		finish();
	}

	public void openAdmin() {
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, component);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Only after opening administrator can be used");
		startActivity(intent);
	}
}
