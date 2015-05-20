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

import com.sun.codemodel.JCodeModel;
import framework.json2java.generated.Product;
import framework.json2java.generated.ProductTypes;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsonschema2pojo.SchemaMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Example {

    public static void main(String[] args) throws IOException, URISyntaxException {

        // BEGIN EXAMPLE

//        JCodeModel codeModel = generate("address.json", "Address", "com.example");
//        output(codeModel);
//
//        codeModel = generate("array.json", "Array", "com.example");
//        output(codeModel);
//
//        codeModel = generate("enum.json", "SomeEnum", "com.example");
//        output(codeModel);

        // END EXAMPLE

        final Product product = new Product();
        product.setId("1");
        product.setTitleSortName("name");
        product.setAdditionalProperty("key", "value");
        product.setProductType(new ArrayList<ProductTypes>() {{
            ProductTypes productTypes = new ProductTypes();
            productTypes.setId("1-1");
            productTypes.setType("type");
            productTypes.setAdditionalProperty("key", "value");
            add(productTypes);
        }});

        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(product);

        System.out.println(string);
    }

    private static JCodeModel generate(String fileName, String className, String packageName) throws URISyntaxException, IOException {
        JCodeModel codeModel = new JCodeModel();
        URI uri = Example.class.getResource(fileName).toURI();
        new SchemaMapper().generate(codeModel, className, packageName, uri.toURL());
        return codeModel;
    }

    private static void output(JCodeModel codeModel) throws IOException {
        File output = new File("output");
        boolean mkdirs = output.mkdirs();
        codeModel.build(output);
    }

}
