/*
 * Copyright 2015-2017 OpenCB
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

package org.opencb.opencga.analysis.alignment;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.ga4gh.models.ReadAlignment;
import org.opencb.biodata.models.alignment.RegionCoverage;
import org.opencb.biodata.models.core.Region;
import org.opencb.commons.datastore.core.DataResult;
import org.opencb.commons.datastore.core.ObjectMap;
import org.opencb.commons.datastore.core.Query;
import org.opencb.commons.datastore.core.QueryOptions;
import org.opencb.commons.utils.FileUtils;
import org.opencb.opencga.analysis.StorageManager;
import org.opencb.opencga.analysis.models.FileInfo;
import org.opencb.opencga.analysis.models.StudyInfo;
import org.opencb.opencga.analysis.wrappers.DeeptoolsWrapperAnalysis;
import org.opencb.opencga.analysis.wrappers.SamtoolsWrapperAnalysis;
import org.opencb.opencga.catalog.db.api.FileDBAdaptor;
import org.opencb.opencga.catalog.exceptions.CatalogException;
import org.opencb.opencga.catalog.managers.CatalogManager;
import org.opencb.opencga.catalog.utils.Constants;
import org.opencb.opencga.catalog.utils.ParamUtils;
import org.opencb.opencga.core.exception.ToolException;
import org.opencb.opencga.core.models.File;
import org.opencb.opencga.core.models.Study;
import org.opencb.opencga.core.results.OpenCGAResult;
import org.opencb.opencga.storage.core.StorageEngineFactory;
import org.opencb.opencga.storage.core.alignment.AlignmentStorageEngine;
import org.opencb.opencga.storage.core.alignment.iterators.AlignmentIterator;
import org.opencb.opencga.storage.core.alignment.local.LocalAlignmentStorageEngine;
import org.opencb.opencga.storage.core.exceptions.StorageEngineException;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.opencb.opencga.core.api.ParamConstants.*;
import static org.opencb.opencga.storage.core.alignment.AlignmentStorageEngine.ALIGNMENT_STATS_VARIABLE_SET;

/**
 * Created by pfurio on 31/10/16.
 */
public class AlignmentStorageManager extends StorageManager {

    private AlignmentStorageEngine alignmentStorageEngine;

    private static final Map<String, String> statsMap = new HashMap<>();

    public AlignmentStorageManager(CatalogManager catalogManager, StorageEngineFactory storageEngineFactory) {
        super(catalogManager, storageEngineFactory);

        // TODO: Create this alignmentStorageEngine by reflection
        this.alignmentStorageEngine = new LocalAlignmentStorageEngine();

        initStatsMap();
    }


    public void index(String studyIdStr, String fileIdStr, Path outDir, ObjectMap options, String sessionId) throws Exception {
        options = ParamUtils.defaultObject(options, ObjectMap::new);
        StopWatch watch = new StopWatch();

        StudyInfo studyInfo = getStudyInfo(studyIdStr, fileIdStr, sessionId);
        checkAlignmentBioformat(studyInfo.getFileInfos());
        FileInfo fileInfo = studyInfo.getFileInfo();
//        ObjectMap fileAndStudyId = getFileAndStudyId(studyIdStr, fileIdStr, sessionId);
//        long studyId = fileAndStudyId.getLong("studyId");
//        long fileId = fileAndStudyId.getLong("fileId");
//        Path filePath = getFilePath(fileId, sessionId);
//        Path workspace = getWorkspace(studyId, sessionId);

        Path linkedBamFilePath = Files.createSymbolicLink(outDir.resolve(fileInfo.getName()), fileInfo.getPhysicalFilePath());

        List<URI> fileUris = Arrays.asList(linkedBamFilePath.toUri());

        // TODO: Check if index is already created and link bai file
        logger.info("Creating index...");
        watch.start();
        try {
            alignmentStorageEngine.index(fileUris, outDir.toUri(), false, options.getBoolean("transform"), options.getBoolean("load"));
        } finally {
            // Remove symbolic link
            Files.delete(linkedBamFilePath);
        }
        watch.stop();
        logger.info("Indexing took {} seconds", watch.getTime() / 1000.0);

//        // Create the stats and store them in catalog
//        logger.info("Calculating the stats...");
//        watch.reset();
//        watch.start();
//        DataResult<AlignmentGlobalStats> stats = alignmentStorageEngine.getDBAdaptor().stats(fileInfo.getPhysicalFilePath(), outDir);
//
//        if (stats != null && stats.getNumResults() == 1) {
//            // Store the stats in catalog
//            ObjectWriter objectWriter = new ObjectMapper().typedWriter(AlignmentGlobalStats.class);
//            ObjectMap globalStats = new ObjectMap(GLOBAL_STATS, objectWriter.writeValueAsString(stats.first()));
//            FileUpdateParams fileUpdateParams = new FileUpdateParams().setStats(globalStats);
//            catalogManager.getFileManager().update(studyIdStr, fileInfo.getPath(), fileUpdateParams,
//                    new QueryOptions(), sessionId);
//
//            // Remove the stats file
//            Path statsFile = outDir.resolve(fileInfo.getName() + ".stats");
//            if (statsFile.toFile().exists()) {
//                Files.delete(statsFile);
//            }
//        }
//        watch.stop();
//        logger.info("Stats calculation took {} seconds", watch.getTime() / 1000.0);
//
//        // Create the coverage
//        logger.info("Calculating the coverage...");
//        watch.reset();
//        watch.start();
////        alignmentStorageEngine.getDBAdaptor().coverage(fileInfo.getPath(), studyInfo.getWorkspace());
//        watch.stop();
//        logger.info("Coverage calculation took {} seconds", watch.getTime() / 1000.0);
    }

    public DataResult<ReadAlignment> query(String studyIdStr, String fileIdStr, Query query, QueryOptions options, String sessionId)
            throws CatalogException, IOException, StorageEngineException {
        query = ParamUtils.defaultObject(query, Query::new);
        options = ParamUtils.defaultObject(options, QueryOptions::new);

        StudyInfo studyInfo = getStudyInfo(studyIdStr, fileIdStr, sessionId);
        checkAlignmentBioformat(studyInfo.getFileInfos());

        return alignmentStorageEngine.getDBAdaptor().get(studyInfo.getFileInfo().getPhysicalFilePath(), query, options);
    }

    public AlignmentIterator<ReadAlignment> iterator(String studyId, String fileId, Query query, QueryOptions options,
                                                     String sessionId) throws CatalogException, IOException, StorageEngineException {
        return iterator(studyId, fileId, query, options, sessionId, ReadAlignment.class);
    }

    public <T> AlignmentIterator<T> iterator(String studyIdStr, String fileIdStr, Query query, QueryOptions options, String sessionId,
                                             Class<T> clazz) throws CatalogException, IOException, StorageEngineException {
        query = ParamUtils.defaultObject(query, Query::new);
        options = ParamUtils.defaultObject(options, QueryOptions::new);

        StudyInfo studyInfo = getStudyInfo(studyIdStr, fileIdStr, sessionId);
        checkAlignmentBioformat(studyInfo.getFileInfos());
//        ObjectMap fileAndStudyId = getFileAndStudyId(studyIdStr, fileIdStr, sessionId);
//        long fileId = fileAndStudyId.getLong("fileId");
//        Path filePath = getFilePath(fileId, sessionId);

        return alignmentStorageEngine.getDBAdaptor().iterator(studyInfo.getFileInfo().getPhysicalFilePath(), query, options, clazz);
//        return alignmentDBAdaptor.iterator((Path) fileInfo.get("filePath"), query, options, clazz);
    }


    //-------------------------------------------------------------------------
    // STATS: run, info and query
    //-------------------------------------------------------------------------

    public void statsRun(String study, String inputFile, String outdir, String token) throws ToolException {
        ObjectMap params = new ObjectMap();
        params.put(SamtoolsWrapperAnalysis.INDEX_STATS_PARAM, true);

        SamtoolsWrapperAnalysis samtools = new SamtoolsWrapperAnalysis();
        samtools.setUp(null, catalogManager, storageEngineFactory, params, Paths.get(outdir), token);

        samtools.setStudy(study);
        samtools.setCommand("stats")
                .setInputFile(inputFile);

        samtools.start();
    }

    //-------------------------------------------------------------------------

    public DataResult<String> statsInfo(String study, String inputFile, String token) throws ToolException, StorageEngineException,
            CatalogException {
        OpenCGAResult<File> fileResult;
        fileResult = catalogManager.getFileManager().get(study, inputFile, QueryOptions.empty(), token);

        if (fileResult.getNumMatches() == 1) {
            return alignmentStorageEngine.getDBAdaptor().statsInfo(Paths.get(fileResult.getResults().get(0).getUri().getPath()));
        } else {
            throw new ToolException("Error accessing to the file: " + inputFile);
        }
    }

    //-------------------------------------------------------------------------

    public DataResult<File> statsQuery(String study, Query query, QueryOptions queryOptions, String token) throws CatalogException {
        Query searchQuery = new Query();
        List<String> filters = new ArrayList<>();
        query.keySet().forEach(k -> {
            if (statsMap.containsKey(k)) {
                filters.add(ALIGNMENT_STATS_VARIABLE_SET + ":" + statsMap.get(k) + query.get(k));
            }
        });
        searchQuery.put(Constants.ANNOTATION, StringUtils.join(filters, ";"));

        return catalogManager.getFileManager().search(study, searchQuery, queryOptions, token);
    }

    //-------------------------------------------------------------------------
    // COVERAGE: run, query and log2Ratio
    //-------------------------------------------------------------------------

    public void coverageRun(String study, String inputFile, int windowSize, String outdir, String token) throws ToolException {
        ObjectMap params = new ObjectMap();
        params.put("of", "bigwig");
        params.put("bs", windowSize);

        DeeptoolsWrapperAnalysis deeptools = new DeeptoolsWrapperAnalysis();

        deeptools.setUp(null, catalogManager, storageEngineFactory, params, Paths.get(outdir), token);

        deeptools.setStudy(study);
        deeptools.setCommand("bamCoverage")
                .setBamFile(inputFile)
                .setCoverageFile(outdir + "/" + new java.io.File(inputFile).getName() + ".bw");

        deeptools.start();
    }

    //-------------------------------------------------------------------------

    public DataResult<RegionCoverage> coverageQuery(String studyIdStr, String fileIdStr, Region region, int minCoverage, int maxCoverage,
                                                    int windowSize, String sessionId) throws Exception {
        File file = extractAlignmentOrCoverageFile(studyIdStr, fileIdStr, sessionId);
        return alignmentStorageEngine.getDBAdaptor().coverageQuery(Paths.get(file.getUri()), region, minCoverage, maxCoverage, windowSize);
    }

    //-------------------------------------------------------------------------
    // Counts
    //-------------------------------------------------------------------------

    public DataResult<Long> getTotalCounts(String studyIdStr, String fileIdStr, String sessionId) throws Exception {
        File file = extractAlignmentOrCoverageFile(studyIdStr, fileIdStr, sessionId);
        return alignmentStorageEngine.getDBAdaptor().getTotalCounts(Paths.get(file.getUri()));
    }


    public DataResult<Long> count(String studyIdStr, String fileIdStr, Query query, QueryOptions options, String sessionId)
            throws CatalogException, IOException, StorageEngineException {
        query = ParamUtils.defaultObject(query, Query::new);
        options = ParamUtils.defaultObject(options, QueryOptions::new);

        StudyInfo studyInfo = getStudyInfo(studyIdStr, fileIdStr, sessionId);
//        ObjectMap fileAndStudyId = getFileAndStudyId(studyIdStr, fileIdStr, sessionId);
//        long fileId = fileAndStudyId.getLong("fileId");
//        Path filePath = getFilePath(fileId, sessionId);

        return alignmentStorageEngine.getDBAdaptor().count(studyInfo.getFileInfo().getPhysicalFilePath(), query, options);
    }

    //-------------------------------------------------------------------------
    // PRIVATE METHODS
    //-------------------------------------------------------------------------

    private File extractAlignmentOrCoverageFile(String studyIdStr, String fileIdStr, String sessionId) throws CatalogException {
        DataResult<File> fileDataResult = catalogManager.getFileManager().get(studyIdStr, fileIdStr,
                new QueryOptions(QueryOptions.INCLUDE, Arrays.asList(FileDBAdaptor.QueryParams.URI.key(),
                        FileDBAdaptor.QueryParams.BIOFORMAT.key(), FileDBAdaptor.QueryParams.FORMAT.key())), sessionId);
        if (fileDataResult.getNumResults() == 0) {
            throw new CatalogException("File " + fileIdStr + " not found");
        }

        File.Bioformat bioformat = fileDataResult.first().getBioformat();
        if (bioformat != File.Bioformat.ALIGNMENT && bioformat != File.Bioformat.COVERAGE) {
            throw new CatalogException("File " + fileDataResult.first().getName() + " not supported. "
                    + "Expecting an alignment or coverage file.");
        }
        return fileDataResult.first();
    }

    private void checkAlignmentBioformat(List<FileInfo> fileInfo) throws CatalogException {
        for (FileInfo file : fileInfo) {
            if (!file.getBioformat().equals(File.Bioformat.ALIGNMENT)) {
                throw new CatalogException("File " + file.getName() + " not supported. Expecting an alignment file.");
            }
        }
    }

    @Override
    public void testConnection() throws StorageEngineException {
    }

    @Deprecated
    private Path getFilePath(long fileId, String sessionId) throws CatalogException, IOException {
        QueryOptions fileOptions = new QueryOptions(QueryOptions.INCLUDE,
                Arrays.asList(FileDBAdaptor.QueryParams.URI.key(), FileDBAdaptor.QueryParams.NAME.key()));
        DataResult<File> fileDataResult = catalogManager.getFileManager().get(fileId, fileOptions, sessionId);

        if (fileDataResult.getNumResults() != 1) {
            logger.error("Critical error: File {} not found in catalog.", fileId);
            throw new CatalogException("Critical error: File " + fileId + " not found in catalog");
        }

        Path path = Paths.get(fileDataResult.first().getUri().getRawPath());
        FileUtils.checkFile(path);

        return path;
    }

    @Deprecated
    private Path getWorkspace(long studyId, String sessionId) throws CatalogException, IOException {
        // Obtain the study uri
        QueryOptions studyOptions = new QueryOptions(QueryOptions.INCLUDE, FileDBAdaptor.QueryParams.URI.key());
        DataResult<Study> studyDataResult = catalogManager.getStudyManager().get(String.valueOf((Long) studyId), studyOptions, sessionId);
        if (studyDataResult .getNumResults() != 1) {
            logger.error("Critical error: Study {} not found in catalog.", studyId);
            throw new CatalogException("Critical error: Study " + studyId + " not found in catalog");
        }

        Path workspace = Paths.get(studyDataResult.first().getUri().getRawPath()).resolve(".opencga").resolve("alignments");
        if (!workspace.toFile().exists()) {
            Files.createDirectories(workspace);
        }

        return workspace;
    }

    private void initStatsMap() {
        statsMap.put(RAW_TOTAL_SEQUENCES, "raw_total_sequences");
        statsMap.put(FILTERED_SEQUENCES, "filtered_sequences");
        statsMap.put(READS_MAPPED, "reads_mapped");
        statsMap.put(READS_MAPPED_AND_PAIRED, "reads_mapped_and_paired");
        statsMap.put(READS_UNMAPPED, "reads_unmapped");
        statsMap.put(READS_PROPERLY_PAIRED, "reads_properly_paired");
        statsMap.put(READS_PAIRED, "reads_paired");
        statsMap.put(READS_DUPLICATED, "reads_duplicated");
        statsMap.put(READS_MQ0, "reads_MQ0");
        statsMap.put(READS_QC_FAILED, "reads_QC_failed");
        statsMap.put(NON_PRIMARY_ALIGNMENTS, "non_primary_alignments");
        statsMap.put(MISMATCHES, "mismatches");
        statsMap.put(ERROR_RATE, "error_rate");
        statsMap.put(AVERAGE_LENGTH, "average_length");
        statsMap.put(AVERAGE_FIRST_FRAGMENT_LENGTH, "average_first_fragment_length");
        statsMap.put(AVERAGE_LAST_FRAGMENT_LENGTH, "average_last_fragment_length");
        statsMap.put(AVERAGE_QUALITY, "average_quality");
        statsMap.put(INSERT_SIZE_AVERAGE, "insert_size_average");
        statsMap.put(INSERT_SIZE_STANDARD_DEVIATION, "insert_size_standard_deviation");
        statsMap.put(PAIRS_WITH_OTHER_ORIENTATION, "pairs_with_other_orientation");
        statsMap.put(PAIRS_ON_DIFFERENT_CHROMOSOMES, "pairs_on_different_chromosomes");
        statsMap.put(PERCENTAGE_OF_PROPERLY_PAIRED_READS, "percentage_of_properly_paired_reads");
    }
}