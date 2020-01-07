/*
 * Copyright 2014 Michel Kremer (kremi151)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lu.kremi151.ambiance;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ScrollView;
import android.widget.TextView;

public class CrashReport extends Activity {
	
	public static final String _REPORT = "report";
	
	ScrollView sv;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sv = new ScrollView(this);
		tv = new TextView(this);
		tv.setTextColor(getResources().getColor(R.color.font));
		sv.addView(tv);
		tv.setText(getIntent().getStringExtra(_REPORT));
		setContentView(sv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.crash_report, menu);
		return true;
	}
	
	public static String getReportAsString(Throwable t){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString(); // stack trace as a string
	}

}
