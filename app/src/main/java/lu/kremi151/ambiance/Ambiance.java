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

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;

import lu.kremi151.ambiance.util.MediaDatabase;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

public class Ambiance extends Application {
	
	private static MediaDatabase mdb;
	private static Context c;

	@Override
	public void onCreate(){
		super.onCreate();
		Thread.currentThread().setUncaughtExceptionHandler(eh);
		mdb = new MediaDatabase(this);
		c = this;
	}
	
	public static Context getContext(){
		return c;
	}

	public static MediaDatabase getMediaDatabase(){
		return mdb;
	}
	
	public static File getHomeFolder(){
		File home_dir = new File(Environment.getExternalStorageDirectory(), "Ambiance");
		home_dir.mkdirs();
		return home_dir;
	}
	
	public static File getAlbumArtFolder(){
		File r = new File(getHomeFolder(), "albumArt");
		r.mkdirs();
		return r;
	}
	
	public static File getArtistArtFolder(){
		File r = new File(getHomeFolder(), "artistArt");
		r.mkdirs();
		return r;
	}
	
	public static boolean isPlayerActive(){
		return true;
	}
	
	private UncaughtExceptionHandler eh = new UncaughtExceptionHandler(){

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			Intent i = new Intent(c, CrashReport.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.putExtra(CrashReport._REPORT, CrashReport.getReportAsString(ex));
			c.startActivity(i);
		}
		
	};
}
