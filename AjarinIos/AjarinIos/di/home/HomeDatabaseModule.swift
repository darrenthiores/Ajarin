import Foundation
import shared

class HomeDatabaseModule {
    init() {
        @Inject var chatDatabase: ChatDatabase
        @Provider var homeChatDao: HomeChatDao = SqlDelightHomeChatDao(db: chatDatabase)
        @Provider var homeLocalDataSource: HomeLocalDataSource = HomeLocalDataSource(
            chatDao: homeChatDao
        )
    }
}
