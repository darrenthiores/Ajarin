import Foundation
import shared

class HomeRepositoryModule {
    init() {
        @Inject var localDataSource: HomeLocalDataSource
        
        @Provider var homeRepository: HomeRepository = HomeRepositoryImpl(
            localDataSource: localDataSource
        )
    }
}
