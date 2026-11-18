/*
 * Copyright 2016-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.rest.webmvc.support;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests for {@link ExceptionMessage}.
 */
class ExceptionMessageUnitTests {

	@Test // GH-2571
	void doesNotRenderCauseChainIntoJson() throws Exception {

		Exception exception = new RuntimeException("Outer", new IllegalStateException("Inner detail"));

		String json = new ObjectMapper().writeValueAsString(new ExceptionMessage(exception));

		assertThat(json).contains("Outer").doesNotContain("cause").doesNotContain("Inner detail");
	}
}
