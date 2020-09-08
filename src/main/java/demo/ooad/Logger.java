package demo.ooad;

import demo.ooad.filter.LoggerFilter;
import demo.ooad.filter.XmlConfigFilterFactory;
import demo.ooad.saver.FileLoggerSaver;
import demo.ooad.saver.LoggerSaver;

public class Logger {
    // [GRASP] Creator -> [GoF] Factory Method -> [GoF] Abstract Factory
    private LoggerFilter filter = new XmlConfigFilterFactory().create();
    private LoggerSaver saver; //Field DI

    public Logger() {
        this(new FileLoggerSaver("default.file"));
    }

    // Constructor DI
    public Logger(LoggerSaver saver) {
        this.saver = saver;
    }

    // Setter DI
    public void setSaver(LoggerSaver saver) {
        this.saver = saver;
    }

    /**
     * @param message
     */
    public void log(LoggerMessage message) {
        if (filter.filter(message)) {
//            if (saver instanceof FileLoggerSaver) {
                final FileLoggerSaver fileSaver = (FileLoggerSaver) saver;
//            }
            saver.save(message);
        }
    }
}
