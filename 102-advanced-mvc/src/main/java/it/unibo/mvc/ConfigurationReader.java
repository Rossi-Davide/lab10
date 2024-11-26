package it.unibo.mvc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class ConfigurationReader {

    private static final String DEFAULT_PATH = "src/main/resources/config.yml";

    private File readFile = new File(DEFAULT_PATH);

    public void setFile(final File file) {
        if(!file.exists()) {
            throw new IllegalArgumentException("Cannot read from a non existing file");
        }
        
    }

    private void setBuilderProperty(final String line, final Configuration.Builder builder) {

        StringTokenizer tokenizer = new StringTokenizer(line, ":");

        final String property = tokenizer.nextToken();
        final String value = tokenizer.nextToken();

        

    }

    public Configuration getConfiguration() throws IOException{
        try (
            FileInputStream fileInputStream = new FileInputStream(readFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bReader = new BufferedReader(inputStreamReader);
        ) {
            final Configuration.Builder builder = new Configuration.Builder();

            setBuilderProperty(bReader.readLine(), builder);
            setBuilderProperty(bReader.readLine(), builder);
            setBuilderProperty(bReader.readLine(), builder);

            return builder.build();
        }
    }
}
