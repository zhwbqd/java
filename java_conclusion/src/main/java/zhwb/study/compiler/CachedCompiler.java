/*
 * Copyright 2014 Higher Frequency Trading
 *
 * http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package zhwb.study.compiler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static zhwb.study.compiler.CompilerUtils.writeBytes;
import static zhwb.study.compiler.CompilerUtils.writeText;


@SuppressWarnings("StaticNonFinalField")
public class CachedCompiler {
    private static final Map<ClassLoader, Map<String, Class>> loadedClassesMap = new WeakHashMap<ClassLoader, Map<String, Class>>();
    private static final Logger LOGGER = LoggerFactory.getLogger(CachedCompiler.class);

    private final File sourceDir;
    private final File classDir;

    private final List<JavaFileObject> javaFileObjects = new ArrayList<JavaFileObject>();

    public CachedCompiler(File sourceDir, File classDir) {
        this.sourceDir = sourceDir;
        this.classDir = classDir;
    }

    public static void close() {
        try {
            CompilerUtils.s_fileManager.close();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public Class loadFromJava(String className, String javaCode) throws ClassNotFoundException {
        return loadFromJava(getClass().getClassLoader(), className, javaCode);
    }


    public Map<String, byte[]> compileFromJava(String className, String javaCode) {
        Iterable<? extends JavaFileObject> compilationUnits;
        if (sourceDir != null) {
            String filename = className.replaceAll("\\.", '\\' + File.separator) + ".java";
            File file = new File(sourceDir, filename);
            writeText(file, javaCode);
            compilationUnits = CompilerUtils.s_standardJavaFileManager.getJavaFileObjects(file);

        } else {
            javaFileObjects.add(new JavaSourceFromString(className, javaCode));
            compilationUnits = javaFileObjects;
        }
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

        // reuse the same file manager to allow caching of jar files
        Boolean status = CompilerUtils.s_compiler.getTask(null, CompilerUtils.s_fileManager, diagnostics, null, null, compilationUnits).call();
        if (!status) {//If compilation error occurs
            /*Iterate through each compilation problem and print it*/
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtils.LF);
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                sb.append(String.format("Error on line %d in %s", diagnostic.getLineNumber(), diagnostic))
                        .append(StringUtils.LF);
            }
            throw new IllegalStateException(String.format("compile error, %s", sb.toString()));
        }
        return CompilerUtils.s_fileManager.getAllBuffers();
    }

    public Class loadFromJava(ClassLoader classLoader, String className, String javaCode) throws ClassNotFoundException {
        Class clazz = null;
        Map<String, Class> loadedClasses;
        synchronized (loadedClassesMap) {
            loadedClasses = loadedClassesMap.get(classLoader);
            if (loadedClasses == null)
                loadedClassesMap.put(classLoader, loadedClasses = new LinkedHashMap<String, Class>());
            else
                clazz = loadedClasses.get(className);
        }
        if (clazz != null)
            return clazz;
        for (Map.Entry<String, byte[]> entry : compileFromJava(className, javaCode).entrySet()) {
            String className2 = entry.getKey();
            synchronized (loadedClassesMap) {
                if (loadedClasses.containsKey(className2))
                    continue;
            }
            byte[] bytes = entry.getValue();
            if (classDir != null) {
                String filename = className2.replaceAll("\\.", '\\' + File.separator) + ".class";
                boolean changed = writeBytes(new File(classDir, filename), bytes);
                if (changed) {
                    LOGGER.info("Updated {} in {}", className2, classDir);
                }
            }
            Class clazz2 = CompilerUtils.defineClass(classLoader, className2, bytes);
            synchronized (loadedClassesMap) {
                loadedClasses.put(className2, clazz2);
            }
        }

        synchronized (loadedClassesMap) {
            loadedClasses.put(className, clazz = classLoader.loadClass(className));
        }
        return clazz;
    }
}
