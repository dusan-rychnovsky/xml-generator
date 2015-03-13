package cz.dusanrychnovsky.xmlgenerator;

import cz.dusanrychnovsky.xmlgenerator.schema.graph.SchemaGraph;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.generators.DocumentGenerator;
import cz.dusanrychnovsky.xmlgenerator.schema.graph.generators.RandomGenerator;
import org.apache.commons.cli.*;
import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.io.File.separator;
import static org.jdom2.output.Format.getPrettyFormat;

public class Generate {

    private static final String SCHEMA_FILE_PARAM = "schema";
    private static final String OUTPUT_DIR_PARAM = "output";
    private static final String COUNT_PARAM = "count";

    private final SchemaParser parser = new DTDParser();
    private final XMLOutputter outputter = new XMLOutputter(getPrettyFormat());

    private static final CommandLineParser cmdParser = new GnuParser();

    private final static Options options = new Options();
    static {
        options.addOption(
            OptionBuilder
                .isRequired()
                .withArgName("file-path")
                .hasArg()
                .withDescription("the schema file")
                .create(SCHEMA_FILE_PARAM)
        );
        options.addOption(
            OptionBuilder
                .isRequired()
                .withArgName("dir-path")
                .hasArg()
                .withDescription("the output dir")
                .create(OUTPUT_DIR_PARAM)
        );
        options.addOption(
            OptionBuilder
                .isRequired()
                .withArgName("number")
                .hasArg()
                .withDescription("the number of documents to be generated")
                .create(COUNT_PARAM)
        );
    }

    public static void main(String[] args) throws IOException, ParseException {

        CommandLine cmd;
        try {
            cmd = cmdParser.parse(options, args);
        }
        catch (org.apache.commons.cli.ParseException ex) {
            System.out.println("ERROR: " + ex.getMessage());
            return;
        }

        File schemaFile = new File(getValue(cmd, SCHEMA_FILE_PARAM));
        File outputDir = new File(getValue(cmd, OUTPUT_DIR_PARAM));
        int count = Integer.parseInt(getValue(cmd, COUNT_PARAM));

        new Generate().run(schemaFile, outputDir, count);
    }

    private static String getValue(CommandLine cmd, String paramName) {

        String value = cmd.getOptionValue(paramName);
        if (value == null) {
            throw new IllegalArgumentException(
                "Mandatory cmd-line argument not present [" + paramName + "]."
            );
        }

        return value;
    }

    public void run(File schemaFile, File outputDir, int count)
        throws IOException, ParseException {

        outputDir.mkdirs();

        SchemaGraph schemaGraph = parser.parse(schemaFile);
        DocumentGenerator docGenerator = new RandomGenerator(schemaGraph);

        for (int i = 0; i < count; i++) {

            Document doc = docGenerator.next();

            File xmlFile = new File(outputDir.getPath() + separator + getFilename(i));
            try (FileOutputStream out = new FileOutputStream(xmlFile)) {
                outputter.output(doc, out);
            }
        }
    }

    private String getFilename(int i) {
        return i + ".xml";
    }
}
