/**
 * Copyright © 2010-2014 Nokia
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package framework.json2java;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.writer.SingleStreamCodeWriter;
import org.jsonschema2pojo.SchemaMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Example {

    public static void main(String[] args) throws IOException, URISyntaxException {

        // BEGIN EXAMPLE

        JCodeModel codeModel = generate("address.json", "Address", "com.example");
        output(codeModel);

        codeModel = generate("array.json", "Array", "com.example");
        output(codeModel);

        codeModel = generate("enum.json", "SomeEnum", "com.example");
        output(codeModel);

        // END EXAMPLE

//        final Product product = new Product();
//        product.setId("1");
//        product.setTitleSortName("name");
//        product.setAdditionalProperty("key", "value");
//        product.setProductType(new ArrayList<ProductTypes>() {{
//            ProductTypes productTypes = new ProductTypes();
//            productTypes.setId("1-1");
//            productTypes.setType("type");
//            productTypes.setAdditionalProperty("key", "value");
//            add(productTypes);
//        }});
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String string = objectMapper.writeValueAsString(product);
//
//        System.out.println(string);
    }

    private static JCodeModel generate(String fileName, String className, String packageName) throws URISyntaxException, IOException {
        JCodeModel codeModel = new JCodeModel();
        URI uri = Example.class.getResource(fileName).toURI();
        new SchemaMapper().generate(codeModel, className, packageName, uri.toURL());
        return codeModel;
    }

    private static void output(JCodeModel codeModel) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CodeWriter codeWriter = new SingleStreamCodeWriter(outputStream);
        codeModel.build(codeWriter);
        codeWriter.close();
        outputStream.close();
        String string = outputStream.toString("utf-8");
        System.out.println(string);
    }

}
