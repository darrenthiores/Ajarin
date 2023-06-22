import Foundation
import shared

class HomeDatabaseModule {
    init() {
        @Provider var driver = DatabaseDriverFactory().create()
        @Provider var chatDatabase = DatabaseFactory().createDatabase(driver: driver)
        @Provider var homeChatDao: HomeChatDao = SqlDelightHomeChatDao(db: chatDatabase)
        @Provider var homeLocalDataSource: HomeLocalDataSource = HomeLocalDataSource(
            chatDao: homeChatDao
        )
    }
}
