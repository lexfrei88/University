package by.epam.study.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Aksionchik 12/5/2017
 * @version 0.1
 */
public class WebContext {

    private static final Logger logger = LogManager.getLogger(WebContext.class);

    private Map<CommandKey, Command> commandMap = new HashMap<>();

    public WebContext(Map<CommandKey, Command> commandMap) {
        this.commandMap = commandMap;
    }

    public Command getCommand(CommandKey key) {
        logger.debug("Get servlet command for name: {}", key);
        return commandMap.get(key);
    }

}
