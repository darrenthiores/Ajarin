import Foundation
import shared

class AppModule {
    init() {
        @Provider var driver = DatabaseDriverFactory().create()
        @Provider var chatDatabase = DatabaseFactory().createDatabase(driver: driver)
        @Provider var dispatchers: DispatchersProvider = StandardDispatchers()
    }
}
