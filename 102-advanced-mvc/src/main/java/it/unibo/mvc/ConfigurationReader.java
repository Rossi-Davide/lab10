package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class ConfigurationReader {

    private static final String DEFAULT_PATH = "config.yml";
    private String path = DEFAULT_PATH;

    /**
     * Updates the path of the configuration file to read from.
     * @param path the path of the configuration file
     */
    public void setPath(final String path) {
        this.path = Objects.requireNonNull(path, "Empty configuration file name");
    }

    private void setBuilderProperty(final String line, final Configuration.Builder builder) {
        StringTokenizer tokenizer = new StringTokenizer(line, ":");
        final String property = tokenizer.nextToken().trim();
        final String value = tokenizer.nextToken().trim();
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

    /**
     * Reads the configuration file.
     * @return A configuration object filled with the parameters read from file
     * @throws IOException
     * if the resource cannot be read because of permission issues or if it doesn't exist
     */
    public Configuration readConfigurationFile() throws IOException, NumberFormatException{
        final Configuration.Builder builder = new Configuration.Builder();
        try (
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bReader = new BufferedReader(inputStreamReader);
        ) {
            for(String line = bReader.readLine();line != null; line = bReader.readLine()) {
                setBuilderProperty(line, builder);
            }        
        }
        return builder.build();
    }
}
