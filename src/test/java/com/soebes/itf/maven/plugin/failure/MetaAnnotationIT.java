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

import com.soebes.itf.jupiter.extension.MavenJupiterExtension;
import com.soebes.itf.jupiter.extension.MavenOption;
import com.soebes.itf.jupiter.extension.MavenTest;
import com.soebes.itf.jupiter.maven.MavenExecutionResult;

import static com.soebes.itf.extension.assertj.MavenITAssertions.assertThat;
import static com.soebes.itf.jupiter.extension.MavenCLIOptions.DEBUG;
import static com.soebes.itf.jupiter.extension.MavenCLIOptions.ERRORS;
import static com.soebes.itf.jupiter.extension.MavenCLIOptions.FAIL_AT_END;
import static com.soebes.itf.jupiter.extension.MavenCLIOptions.NON_RECURSIVE;

@MavenJupiterExtension
@MavenTestOptions
class MetaAnnotationIT {

  @MavenTest
  void case_one(MavenExecutionResult project) {
    assertThat(project).isSuccessful();
  }

  @MavenTest
  void case_two(MavenExecutionResult project) {
    assertThat(project).isSuccessful();
  }

  @MavenTest
  void case_three(MavenExecutionResult result) {
    assertThat(result).isSuccessful();
  }

  @MavenTest
  @MavenOption(DEBUG)
  void case_four(MavenExecutionResult result) {
    assertThat(result).isSuccessful();
  }

}
