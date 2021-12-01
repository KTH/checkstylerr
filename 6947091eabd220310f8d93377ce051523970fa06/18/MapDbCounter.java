/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.demo.counter;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.mapdb.option.MapDbOptions;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbUri;
import fr.inria.atlanmod.neoemf.demo.util.Helpers;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * A simple example showing how to access an existing MapDB-based {@link PersistentResource} and traverse its content to
 * count the number of elements it contains.
 */
public class MapDbCounter {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();

        Instant start = Instant.now();

        URI uri = MapDbUri.builder().fromFile("models/sample.mapdb");

        Map<String, Object> options = MapDbOptions.noOption();

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(uri)) {
            resource.load(options);

            long size = Helpers.countElements(resource);
            Log.info("Resource {0} contains {1} elements", resource.toString(), size);
        }

        Instant end = Instant.now();
        Log.info("Query computed in {0} ms", Duration.between(start, end).getSeconds());
    }
}
