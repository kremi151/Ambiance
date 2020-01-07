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

package lu.kremi151.ambiance.util;

import lu.kremi151.ambiance.Ambiance;
import lu.kremi151.ambiance.Main;
import lu.kremi151.ambiance.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AmbianceNavigationAdapter extends BaseAdapter{
	
	Main m;
	
	public AmbianceNavigationAdapter(Main m){
		this.m = m;
	}

	@Override
	public int getCount() {
		return Ambiance.isPlayerActive() ? 5 : 4;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 1l;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int pos = position;
		if(position == 0 && Ambiance.isPlayerActive()){
			View v = m.getLayoutInflater().inflate(R.layout.list_current_playing, null);
			TextView title = (TextView) v.findViewById(R.id.textView1);
			TextView artist = (TextView) v.findViewById(R.id.textView2);
			ImageView cover = (ImageView) v.findViewById(R.id.imageView1);
			return v;
		}else if(!Ambiance.isPlayerActive()){
			pos++;
		}
		View e = m.getLayoutInflater().inflate(R.layout.list_entry, null);
		TextView label = (TextView) e.findViewById(R.id.textView1);
		switch(pos){
		case 1:
			label.setText(R.string.tracks);
			return e;
		case 2:
			label.setText(R.string.albums);
			return e;
		case 3:
			label.setText(R.string.artists);
			return e;
		case 4:
			label.setText(R.string.playlists);
			return e;
		}
		return null;
	}

}
