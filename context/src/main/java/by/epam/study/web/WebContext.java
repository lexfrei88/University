package by.epam.study.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Context that contain Map of commands for the one Front Controller Servlet
 *
 * @author Alex Aksionchik 12/5/2017
 * @version 1.0
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
