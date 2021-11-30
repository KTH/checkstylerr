/*
 * Copyright (c) 2002-2018 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.pagecache;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import org.neo4j.io.fs.FileUtils;
import org.neo4j.io.pagecache.PageCache;
import org.neo4j.io.pagecache.PagedFile;

class PageLoaderFactory
{
    private final ExecutorService executor;
    private final PageCache pageCache;

    PageLoaderFactory( ExecutorService executor, PageCache pageCache )
    {
        this.executor = executor;
        this.pageCache = pageCache;
    }

    PageLoader getLoader( PagedFile file ) throws IOException
    {
        if ( FileUtils.highIODevice( file.file().toPath(), false ) )
        {
            return new ParallelPageLoader( file, executor, pageCache );
        }
        return new SingleCursorPageLoader( file );
    }
}
