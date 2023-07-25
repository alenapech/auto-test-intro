import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlackListService {

    private static final Logger logger
            = LoggerFactory.getLogger(BlackListService.class);

    public boolean isInBlackList(String clientId) {
        logger.info("Обращение к сервису черных списков для проверки клиента");
        return false;
    }
}
