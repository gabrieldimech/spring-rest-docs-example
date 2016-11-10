/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.example.spring;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring.request.GoodbyeRequest;
import org.example.spring.request.HelloRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


// Sample test class for spring mvc app using Spring Rest Docs to generate API documentation.
// One important thing to note here is that the client is able to send different objects to the same endpoint (greeting)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTestDocumentation {

    @Rule
    public RestDocumentation restDocumentation =
            new RestDocumentation("target/generated-snippets");
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    private RestDocumentationResultHandler document;

    @Before
    public void setUp() {
        this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation).uris()
                        .withScheme("http")
                        .withHost("offsidegaming.com")
                        .withPort(9000))
                .alwaysDo(this.document)

                .build();
    }

    //the hello request has a single field that is defined in the abstract class from which HelloRequest extends.
   /* @Test
    public void getGreeting() throws Exception {
        HelloRequest req = new HelloRequest();
        req.setName("Will");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/greeting").content(objectMapper.writeValueAsString(req))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("hello Will")))
                .andDo(this.document.document(
                        links(
                        ),
                        responseFields(
                                fieldWithPath("message").description("An array of <<resources-tag,Tag resources>>")
                                )));
    }*/

    //goodbye request has an additional field - 'fullname'. the 'name' field nfrom the abstract class can be
    // sent too however this is not used in the implementation.
    /*@Test
    public void getGoodbye() throws Exception {
        GoodbyeRequest req = new GoodbyeRequest();
        req.setFullname("Will Grigg");
        req.setName("Will");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/greeting").content(objectMapper.writeValueAsString(req))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("goodbye Will Grigg")))
                .andDo(this.document.document(
                        links(
                        ),
                        responseFields(
                                fieldWithPath("message").description("An array of <<resources-tag,Tag resources>>"))));
    }*/
}
