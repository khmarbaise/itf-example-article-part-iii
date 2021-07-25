package com.soebes.itf.maven.plugin.failure;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.soebes.itf.jupiter.extension.MavenCLIOptions;
import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenOption;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;
import static com.soebes.itf.jupiter.extension.MavenCLIOptions.LOG_FILE;
import static com.soebes.itf.jupiter.extension.MavenCLIOptions.QUIET;
import static com.soebes.itf.jupiter.extension.MavenCLIOptions.SHOW_VERSION;

@MavenJupiterExtension
class FailureIT {

  @MavenTest
  @DisplayName("The basic configuration should result in a successful build.")
  void basic_configuration(MavenExecutionResult project) {
    assertThat(project).isSuccessful();
  }

  @MavenTest
  @MavenOption(MavenCLIOptions.DEBUG)
  void basic_configuration_with_debug(MavenExecutionResult result) {
    assertThat(result)
        .isSuccessful()
        .out()
        .info()
        .containsSubsequence(
            "--- maven-enforcer-plugin:3.0.0-M1:enforce (enforce-maven) @ basic_configuration_with_debug ---",
            "--- jacoco-maven-plugin:0.8.5:prepare-agent (default) @ basic_configuration_with_debug ---",
            "--- maven-resources-plugin:3.1.0:resources (default-resources) @ basic_configuration_with_debug ---",
            "--- maven-compiler-plugin:3.8.1:compile (default-compile) @ basic_configuration_with_debug ---",
            "--- maven-resources-plugin:3.1.0:testResources (default-testResources) @ basic_configuration_with_debug ---",
            "--- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ basic_configuration_with_debug ---",
            "--- maven-surefire-plugin:3.0.0-M4:test (default-test) @ basic_configuration_with_debug ---",
            "--- maven-jar-plugin:3.2.0:jar (default-jar) @ basic_configuration_with_debug ---",
            "--- maven-site-plugin:3.9.1:attach-descriptor (attach-descriptor) @ basic_configuration_with_debug ---"
        );
    assertThat(result)
        .isSuccessful()
        .out()
        .warn()
        .containsSubsequence(
            "Neither executionException nor failureException has been set.",
            "JAR will be empty - no content was marked for inclusion!");

    assertThat(result)
        .isSuccessful()
        .out()
        .debug()
        .containsSubsequence(
            "Created new class realm maven.api",
            "Project: com.soebes.itf.maven.plugin.its:basic_configuration_with_debug:jar:1.0",
            "Goal:          org.apache.maven.plugins:maven-resources-plugin:3.1.0:resources (default-resources)"
        );

  }

  @MavenTest
  @MavenOption(MavenCLIOptions.BATCH_MODE)
  @MavenOption(MavenCLIOptions.ERRORS)
  @MavenOption(MavenCLIOptions.DEBUG)
  void basic_configuration_with_debug_and_others(MavenExecutionResult result) {
    assertThat(result)
        .isSuccessful()
        .out()
        .info()
        .containsSubsequence(
            "--- maven-enforcer-plugin:3.0.0-M1:enforce (enforce-maven) @ basic_configuration_with_debug ---",
            "--- jacoco-maven-plugin:0.8.5:prepare-agent (default) @ basic_configuration_with_debug ---",
            "--- maven-resources-plugin:3.1.0:resources (default-resources) @ basic_configuration_with_debug ---",
            "--- maven-compiler-plugin:3.8.1:compile (default-compile) @ basic_configuration_with_debug ---",
            "--- maven-resources-plugin:3.1.0:testResources (default-testResources) @ basic_configuration_with_debug ---",
            "--- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ basic_configuration_with_debug ---",
            "--- maven-surefire-plugin:3.0.0-M4:test (default-test) @ basic_configuration_with_debug ---",
            "--- maven-jar-plugin:3.2.0:jar (default-jar) @ basic_configuration_with_debug ---",
            "--- maven-site-plugin:3.9.1:attach-descriptor (attach-descriptor) @ basic_configuration_with_debug ---"
        );
    assertThat(result)
        .isSuccessful()
        .out()
        .warn()
        .containsSubsequence(
            "Neither executionException nor failureException has been set.",
            "JAR will be empty - no content was marked for inclusion!");

    assertThat(result)
        .isSuccessful()
        .out()
        .debug()
        .containsSubsequence(
            "Created new class realm maven.api",
            "Project: com.soebes.itf.maven.plugin.its:basic_configuration_with_debug:jar:1.0",
            "Goal:          org.apache.maven.plugins:maven-resources-plugin:3.1.0:resources (default-resources)"
        );

  }

  @MavenTest
  @MavenOption(QUIET)
  @MavenOption(SHOW_VERSION)
  @MavenOption(value = LOG_FILE, parameter = "test.log")
  void option_quiet_logfile(MavenExecutionResult result) throws IOException {
    assertThat(result)
        .isSuccessful()
        .out()
        .info()
        .isEmpty();

    assertThat(result)
        .isSuccessful()
        .out()
        .warn().isEmpty();

    assertThat(result)
        .isSuccessful()
        .out()
        .debug().isEmpty();

    File baseDir = result.getMavenProjectResult().getTargetProjectDirectory();
    /*
       Ony the following lines will be written to the resulting test.log:
        Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
        Maven home: /...
        Java version: 11.0.8, vendor: AdoptOpenJDK, runtime: ...
        Default locale: en_GB, platform encoding: UTF-8
        OS name: "mac os x", version: "10.14.6", arch: "x86_64", family: "mac"
     */
    assertThat(Files.lines(Paths.get(baseDir.getPath(), "test.log"))).hasSize(5);
  }

}
