package ch.heigvd.res.labio.impl.filters;

import ch.heigvd.res.labio.impl.Utils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());


    public FileNumberingFilterWriter(Writer out) {
        super(out);
        lineCounter = 1;
    }

    static long lineCounter = 1;

    @Override
    public void write(String str, int off, int len) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

        if (str.equals("")) {
            return;
        }

        String tempStr = "";
        String[] oneLineAndRest = Utils.getNextLine(str);

        if (lineCounter == 1) {
            tempStr += lineCounter;
            tempStr += "\t";
            ++lineCounter;
        }

        while (!oneLineAndRest[0].equals("")){
            tempStr += oneLineAndRest[0];
            tempStr += lineCounter;
            tempStr += "\t";
            ++lineCounter;

            oneLineAndRest = Utils.getNextLine(oneLineAndRest[1]);
        }
        
        tempStr += oneLineAndRest[1];

        out.write(tempStr);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(int c) throws IOException {
        throw new UnsupportedOperationException("The student has not implemented this method yet.");
        //char result = (char)c;
        //String str =String.valueOf(result);

    }

}
