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

    private static final String DEFAULT_PATH = "resources/config.yml";
    private String path = DEFAULT_PATH;

    public void setPath(final String path) {
        this.path = Objects.requireNonNull(path, "Empty configuration file name");
    }

    private void setBuilderProperty(final String line, final Configuration.Builder builder) {
        StringTokenizer tokenizer = new StringTokenizer(line, ":");
        final String property = tokenizer.nextToken();
        final String value = tokenizer.nextToken();
        switch (property) {
            case "minimum":
                builder.setMin(Integer.parseInt(value));
                break;
            case "maximum":
                builder.setMax(Integer.parseInt(value));
                break;
            case "attempts":
                builder.setAttempts(Integer.parseInt(value));
                break;
        }        

    }

    public Configuration readConfigurationFile() throws IOException{
        final Configuration.Builder builder = new Configuration.Builder();
        try (
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bReader = new BufferedReader(inputStreamReader);
        ) {
            setBuilderProperty(bReader.readLine(), builder);
            setBuilderProperty(bReader.readLine(), builder);
            setBuilderProperty(bReader.readLine(), builder);
        }
        return builder.build();
    }
}
