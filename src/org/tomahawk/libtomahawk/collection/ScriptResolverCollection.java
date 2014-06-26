/* == This file is part of Tomahawk Player - <http://tomahawk-player.org> ===
 *
 *   Copyright 2014, Enno Gottschalk <mrmaffen@googlemail.com>
 *
 *   Tomahawk is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Tomahawk is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Tomahawk. If not, see <http://www.gnu.org/licenses/>.
 */
package org.tomahawk.libtomahawk.collection;

import org.tomahawk.libtomahawk.resolver.Query;
import org.tomahawk.libtomahawk.resolver.Result;
import org.tomahawk.libtomahawk.resolver.ScriptResolver;

/**
 * This class represents a Collection which contains tracks/albums/artists retrieved by a
 * ScriptResolver.
 */
public class ScriptResolverCollection extends Collection {

    private ScriptResolver mScriptResolver;

    public ScriptResolverCollection(ScriptResolver scriptResolver) {
        super(scriptResolver.getId(), scriptResolver.getCollectionName(), true);

        mScriptResolver = scriptResolver;
        initializeCollection();
    }

    /**
     * Initialize this {@link org.tomahawk.libtomahawk.collection.ScriptResolverCollection}.
     */
    protected void initializeCollection() {
        mScriptResolver.artists(getId());
    }

    public void addTrackResult(Result result) {
        Query query = Query.get(result, isLocal());
        addQuery(query);
        sendCollectionUpdatedBroadcast();
    }

    public void addArtistResult(Artist artist) {
        addArtist(artist);
        mScriptResolver.albums(getId(), artist.getName());
    }

    public void addAlbumResult(Album album) {
        addAlbum(album);
        //mScriptResolver.tracks(getId(), album.getArtist().getName(), album.getName());
    }
}
