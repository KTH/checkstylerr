/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.demo.counter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.hbase.config.HBaseConfig;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseUriFactory;
import fr.inria.atlanmod.neoemf.demo.util.Helpers;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.time.Stopwatch;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.IOException;

/**
 * A simple example showing how to access an existing HBase-based {@link PersistentResource} and traverse its content to
 * count the number of elements it contains.
 */
public class HBaseCounter {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();

        URI uri = new HBaseUriFactory().createRemoteUri("localhost", 2181, "sample.hbase");

        ImmutableConfig config = new HBaseConfig();

        Stopwatch stopwatch = Stopwatch.createStarted();

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(uri)) {
            resource.load(config.toMap());

            long size = Helpers.countElements(resource);
            Log.info("Resource {0} contains {1} elements", resource.toString(), size);
        }

        stopwatch.stop();
        Log.info("Query computed in {0} ms", stopwatch.elapsed().toMillis());
    }
}
