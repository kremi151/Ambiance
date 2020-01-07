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

package lu.kremi151.ambiance.playlist;

import lu.kremi151.ambiance.Ambiance;
import lu.kremi151.ambiance.util.ExtendedMath;
import lu.kremi151.ambiance.util.Song;

public class PlaylistDatabase extends BasePlaylist{
	
	int index = 0;

	@Override
	public boolean next() {
		if(index >= getSize()){
			index = 0;
		}else{
			index++;
		}
		if(index >= getSize()- 1)return true;
		return false;
	}

	@Override
	public boolean rewind() {
		if(index <= 0){
			index = getSize() - 1;
		}else{
			index--;
		}
		if(index <= 0)return true;
		return false;
	}

	@Override
	public Song getCurrentSong() {
		return Ambiance.getMediaDatabase().getPlayableList()[index];
	}

	@Override
	public void setIndex(int idx) {
		this.index = ExtendedMath.minMax(0, idx, getSize()-1);
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int getSize() {
		return Ambiance.getMediaDatabase().getPlayableList().length;
	}

}
