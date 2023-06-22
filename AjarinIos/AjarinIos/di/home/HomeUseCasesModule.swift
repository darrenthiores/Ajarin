import Foundation
import shared

class HomeUseCasesModule {
    init() {
        @Inject var homeRepository: HomeRepository
        
        @Provider var searchMentorByCourse: SearchMentorByCourse = SearchMentorByCourse()
        @Provider var getUnreadCount: GetUnreadCount = GetUnreadCount(repository: homeRepository)
    }
}
